#include <iostream>
#include <mpi.h>
#include <cmath>
#include <cfloat>
#include <cstring>

using namespace std;

const static int N = 320;
const static double a = 10e5;
const static double epsilon = 10e-8;

static const double D_X = 2;
static const double D_Y = 2;
static const double D_Z = 2;

static const double X_0 = -1;
static const double Y_0 = -1;
static const double Z_0 = -1;

int ProcNum = 0;
int ProcRank = 0;

double H_X = D_X / (double)(N - 1);
double H_Y = D_Y / (double)(N - 1);
double H_Z = D_Z / (double)(N - 1);

double H_X2 = H_X * H_X;
double H_Y2 = H_Y * H_Y;
double H_Z2 = H_Z * H_Z;

double phi(double x, double y, double z) {
    return x * x + y * y + z * z;
}

double ro(double x, double y, double z) {
    return 6 - a * phi(x, y, z);
}

double X(int i) {
    return (X_0 + i * H_X);
}

double Y(int j) {
    return (Y_0 + j * H_Y);
}

double Z(int k) {
    return (Z_0 + k * H_Z);
}

int index(int i, int j, int k) {
    return N * N * i + N * j + k;
}

void initializePhi(int LayerHeight, double* currentLayer) {
    for (int i = 0; i < LayerHeight + 2; i++) {
        int RelativeZ = i + ((ProcRank * LayerHeight) - 1);
        double z = Z(RelativeZ);

        for (int j = 0; j < N; j++) {
            double x = X(j);

            for (int k = 0; k < N; k++) {
                double y = Y(k);

                if (k != 0 && k != N - 1 &&
                    j != 0 && j != N - 1 &&
                    z != Z_0 && z != Z_0 + D_Z) {
                    currentLayer[index(i, j, k)] = 0;
                }
                else {
                    currentLayer[index(i, j, k)] = phi(x, y, z);
                }

            }
        }
    }
}


double CalculateDelta(double* Omega) {
    double deltaMax = 0;
    double x, y, z;
    int finish = N / ProcNum;
    for (int i = 0; i < finish; i++) {
        int RelativeZ = i + (ProcRank * finish);
        z = Z(RelativeZ);
        for (int j = 0; j < N; j++) {
            y = Y(j);
            for (int k = 0; k < N; k++) {
                x = X(k);
                deltaMax = max(deltaMax, abs(Omega[index(i, j, k)] - phi(x, y, z)));
            }
        }
    }
    
    return deltaMax;
}

double UpdateLayer(int RelativeZCoordinate, int LayerIdx, double* CurrentLayer, double* CurrentLayerBuf) {
    int AbsoluteZCoordinate = RelativeZCoordinate + LayerIdx;
    double deltaMax = 0;
    double x, y, z;

    if (AbsoluteZCoordinate == 0 || AbsoluteZCoordinate == N - 1) {
        memcpy(CurrentLayerBuf + LayerIdx * N * N, CurrentLayer + LayerIdx * N * N, N * N * sizeof(double));
        deltaMax = 0;
    }
    else {
        z = Z(AbsoluteZCoordinate);

        for (int i = 0; i < N; i++) {
            x = X(i);
            for (int j = 0; j < N; j++) {
                y = Y(j);

                if (i == 0 || i == N - 1 || j == 0 || j == N - 1) {
                    CurrentLayerBuf[index(LayerIdx, i, j)] = CurrentLayer[index(LayerIdx, i, j)];
                }
                else {
                    CurrentLayerBuf[index(LayerIdx, i, j)] =
                        ((CurrentLayer[index(LayerIdx + 1, i, j)] + CurrentLayer[index(LayerIdx - 1, i, j)]) / H_Z2 +
                            (CurrentLayer[index(LayerIdx, i + 1, j)] + CurrentLayer[index(LayerIdx, i - 1, j)]) / H_X2 +
                            (CurrentLayer[index(LayerIdx, i, j + 1)] + CurrentLayer[index(LayerIdx, i, j - 1)]) / H_Y2 -
                            ro(x, y, z)) / (2 / H_X2 + 2 / H_Y2 + 2 / H_Z2 + a);

                    deltaMax = abs(CurrentLayerBuf[index(LayerIdx, i, j)] - CurrentLayer[index(LayerIdx, i, j)]);
                }
            }
        }
    }

    return deltaMax;
}

int main(int argc, char* argv[]) {
    MPI_Init(&argc, &argv);
    MPI_Comm_size(MPI_COMM_WORLD, &ProcNum);
    MPI_Comm_rank(MPI_COMM_WORLD, &ProcRank);
    MPI_Request req[4];

    if (N % ProcNum && ProcRank == 0) {
        cout << "Grid size " << N << " should be divisible by the ProcNum " << endl;
        return 0;
    }

    double GlobalMaxDelta = DBL_MAX;

    int LayerSize = N / ProcNum;
    int LayerZCoordinate = ProcRank * LayerSize - 1;

    int ExtendedLayerSize = (LayerSize + 2) * N * N;
    double* CurrentLayer = new double[ExtendedLayerSize];
    double* CurrentLayerBuf = new double[ExtendedLayerSize];

    initializePhi(LayerSize, CurrentLayer);


    double Start = MPI_Wtime();

    while (GlobalMaxDelta > epsilon) {
        double tmpMaxDelta;
        double ProcMaxDelta = 0;

        if (ProcRank != 0) {
            MPI_Isend(CurrentLayerBuf + N * N, N * N, MPI_DOUBLE,
                ProcRank - 1, 888, MPI_COMM_WORLD, &req[1]);

            MPI_Irecv(CurrentLayerBuf, N * N, MPI_DOUBLE,
                ProcRank - 1, 888, MPI_COMM_WORLD, &req[0]);
        }

        if (ProcRank != ProcNum - 1) {
            MPI_Isend(CurrentLayerBuf + N * N * LayerSize, N * N, MPI_DOUBLE,
                ProcRank + 1, 888, MPI_COMM_WORLD, &req[3]);

            MPI_Irecv(CurrentLayerBuf + N * N * (LayerSize + 1), N * N, MPI_DOUBLE,
                ProcRank + 1, 888, MPI_COMM_WORLD, &req[2]);
        }

        for (int LayerIdx = 2; LayerIdx < LayerSize; LayerIdx++) {
            tmpMaxDelta = UpdateLayer(LayerZCoordinate, LayerIdx, CurrentLayer, CurrentLayerBuf);
            ProcMaxDelta = max(ProcMaxDelta, tmpMaxDelta);
        }

        if (ProcRank != 0) {
            MPI_Wait(&req[0], MPI_STATUS_IGNORE);
            MPI_Wait(&req[1], MPI_STATUS_IGNORE);
        }

        if (ProcRank != ProcNum - 1) {
            MPI_Wait(&req[2], MPI_STATUS_IGNORE);
            MPI_Wait(&req[3], MPI_STATUS_IGNORE);
        }

        tmpMaxDelta = UpdateLayer(LayerZCoordinate, 1, CurrentLayer, CurrentLayerBuf);
        ProcMaxDelta = max(ProcMaxDelta, tmpMaxDelta);

        tmpMaxDelta = UpdateLayer(LayerZCoordinate, LayerSize, CurrentLayer, CurrentLayerBuf);
        ProcMaxDelta = max(ProcMaxDelta, tmpMaxDelta);

        memcpy(CurrentLayer, CurrentLayerBuf, ExtendedLayerSize * sizeof(double));

        MPI_Allreduce(&ProcMaxDelta, &GlobalMaxDelta, 1, MPI_DOUBLE, MPI_MAX, MPI_COMM_WORLD);

    }
    double globalDeltaEnd = 0;
    double deltaEnd = CalculateDelta(CurrentLayer + N * N);
    
    MPI_Allreduce(&deltaEnd, &globalDeltaEnd, 1, MPI_DOUBLE, MPI_MAX, MPI_COMM_WORLD);///

    double End = MPI_Wtime();

    if (ProcRank == 0) {
        cout << "Delta: " << globalDeltaEnd << endl;
        cout << "Time taken: " << End - Start << endl;
    }

    delete[] CurrentLayerBuf;
    delete[] CurrentLayer;

    MPI_Finalize();
    return 0;
}
