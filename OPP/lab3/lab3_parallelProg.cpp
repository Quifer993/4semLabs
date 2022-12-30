#include "mpi.h"
#include <iostream>
#include <stdlib.h>

#define HEIGHT1 24
#define WIDTH_HEIGHT 24
#define WIDTH2 24
#define P1 4
#define P2 3

using namespace std;

void showMatrix(double* matrix, size_t height, size_t width)
{
    size_t i, j;
    for (i = 0; i < height; ++i)
    {
        for (j = 0; j < width; ++j)
        {
            cout << matrix[i * width + j] << " ";
        }
        cout << endl;
    }
    cout << "\n"
        << "\n"
        << "\n";
}

void multMatrix(double* M1, double* M2, double* result, size_t n1, size_t n2, size_t n3){
    size_t i, k, j;
    for (i = 0; i < n1; i++){
        for (k = 0; k < n2; k++){
            for (j = 0; j < n3; j++){
                result[i * n3 + j] += M1[i * n2 + k] * M2[k * n3 + j];
            }
        }
    }
}


double* createEMatrix(size_t height, size_t width) {
    double* tmp = (double*)calloc(height * width, sizeof(double));
    for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
            int indexMatrix = i * width + j;
            if (i == j) {
                tmp[indexMatrix] = 1.0;
            }
            else {
                tmp[indexMatrix] = 0.0;
            }
        }
    }
    return tmp;
}


double* createMatrix(size_t height, size_t width){
    double* tmp = (double*)calloc(height * width, sizeof(double));
    for (size_t i = 0; i < height * width; i++){
        tmp[i] = rand() % 5;
    }
    return tmp;
}

int main(int argc, char* argv[]){
    int procRank, procSize;

    MPI_Comm vert_comm, hor_comm, comm2d;
    MPI_Init(&argc, &argv);

    MPI_Comm_rank(MPI_COMM_WORLD, &procRank);
    MPI_Comm_size(MPI_COMM_WORLD, &procSize);

    double* A_full = NULL;
    double* B_full = NULL;
    double* result = NULL;

    if (procRank == 0)
    {
        A_full = createMatrix(HEIGHT1, WIDTH_HEIGHT);
        B_full = createMatrix(WIDTH_HEIGHT, WIDTH2);
        result = (double*)calloc(HEIGHT1 * WIDTH2, sizeof(double));
    }

    int dims[2];
    dims[0] = P1;
    dims[1] = P2;

    int periods[2] = { 0, 0 };

    MPI_Cart_create(MPI_COMM_WORLD, 2, dims, periods, 0, &comm2d);
    int coords[2];
    MPI_Cart_coords(comm2d, procRank, 2, coords);

    MPI_Comm_split(comm2d, coords[1], coords[0], &vert_comm);
    MPI_Comm_split(comm2d, coords[0], coords[1], &hor_comm);  

    int rows_in_proc = HEIGHT1 / P1;
    int columns_in_proc = WIDTH2 / P2;

    int* recvcounts = (int*)calloc(procSize, sizeof(int));
    int* displs = (int*)calloc(procSize, sizeof(int));
    int temp = 0;
    int shift = 0;
    for (int i = 1; i < procSize; ++i){
        if (i % dims[1] != 0){
            temp = shift + (i % dims[1]) * columns_in_proc;
        }
        else{
            temp = WIDTH2 * rows_in_proc * (i / dims[1]);
            shift = temp;
        }
        displs[i] = temp;
        recvcounts[i] = 1;
    }
    displs[0] = 0;
    recvcounts[0] = 1;

    double* A_part = (double*)calloc(WIDTH_HEIGHT * rows_in_proc, sizeof(double));
    double* B_part = (double*)calloc(WIDTH_HEIGHT * columns_in_proc, sizeof(double));
    double* res_part = (double*)calloc(rows_in_proc * columns_in_proc, sizeof(double));

    double start = MPI_Wtime();

    if (coords[1] == 0) { // zero process by x coordinate
        MPI_Scatter(A_full, rows_in_proc * WIDTH_HEIGHT, MPI_DOUBLE, A_part, rows_in_proc * WIDTH_HEIGHT, MPI_DOUBLE, 0, vert_comm);
    }

    if (coords[0] == 0) {// zero process by y coordinate
        MPI_Datatype column_type;
        MPI_Datatype column_resized_type;

        MPI_Type_vector(WIDTH_HEIGHT, columns_in_proc, WIDTH2, MPI_DOUBLE, &column_type);
        MPI_Type_commit(&column_type); 

        MPI_Type_create_resized(column_type, 0, columns_in_proc * sizeof(double), &column_resized_type);
        MPI_Type_commit(&column_resized_type);

        MPI_Scatter(B_full, 1, column_resized_type, B_part, WIDTH_HEIGHT * columns_in_proc, MPI_DOUBLE, 0, hor_comm);
        MPI_Type_free(&column_resized_type);
        MPI_Type_free(&column_type);
    }

    MPI_Bcast(A_part, rows_in_proc * WIDTH_HEIGHT, MPI_DOUBLE, 0, hor_comm);
    MPI_Bcast(B_part, columns_in_proc * WIDTH_HEIGHT, MPI_DOUBLE, 0, vert_comm);

    multMatrix(A_part, B_part, res_part, rows_in_proc, WIDTH_HEIGHT, columns_in_proc);

    // Traversing the comm_cart grid and sending each C_part to definite place in zero process
    MPI_Datatype res_block;
    MPI_Datatype res_block_resized;

    MPI_Type_vector(rows_in_proc, columns_in_proc, WIDTH2, MPI_DOUBLE, &res_block);
    MPI_Type_commit(&res_block);

    MPI_Type_create_resized(res_block, 0, sizeof(double), &res_block_resized);
    MPI_Type_commit(&res_block_resized);

    MPI_Gatherv(res_part, rows_in_proc * columns_in_proc, MPI_DOUBLE, result, recvcounts, displs, res_block_resized, 0, comm2d);

    double finish = MPI_Wtime();
    if (procRank == 0){
        //showMatrix(A_full, HEIGHT1, WIDTH_HEIGHT);
        //showMatrix(B_full, WIDTH_HEIGHT, WIDTH2);
        //showMatrix(result, HEIGHT1, WIDTH2);
        cout << "Total time: " << finish - start << " sec." << endl;
        free(A_full);
        free(B_full);
        free(result);
    }
    MPI_Type_free(&res_block);
    MPI_Type_free(&res_block_resized);
    free(recvcounts);
    free(displs);
    free(A_part);
    free(B_part);
    free(res_part);

    MPI_Finalize();

    return 0;
}