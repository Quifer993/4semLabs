#include <malloc.h>
#include <math.h>
#include <float.h>
#include <stdio.h>
#include <stdlib.h>
#include <mpi.h>


void create_matrix(double* matrix, int size, int rank, int sizeRanks) {
	int length = size / sizeRanks;
	for (int i = 0; i < length; i++) {
		for (int j = 0; j < size; j++) {
			int indexMatrix = i * size + j;
			if ((rank * length + i) == j) {
				matrix[indexMatrix] = 2.0;
			}
			else {
				matrix[indexMatrix] = 1.0;
			}
		}
	}
}


void create_vector(double* vector, int size, double value) {
	for (int i = 0; i < size; i++) {
		vector[i] = value;
	}
}


void vectorMinusVector(double* resVect, double* vector1, double* vector2, int size) {
	for (int i = 0; i < size; i++) {
		resVect[i] = vector1[i] - vector2[i];
	}
}


void multVectInConst(double* vector, double t, int size) {
	for (int i = 0; i < size; i++) {
		vector[i] = vector[i] * t;
	}
}


void matrixMultVector(double* outVector, double* inMatrix, double* vector, int size, int capacityRanks, int rank) {
	int height = size / capacityRanks;
	//int start = height * rank;
	//int finish = height * (rank + 1);
	for (int i = 0; i < height; i++) {
		double sum = 0;
		for (int j = 0; j < size; j++) {
			sum += inMatrix[i * size + j] * vector[j];
		}
		outVector[i] = sum;
	}
}


double quadrSum(double* vector, int size) {
	double sum = 0;
	for (int i = 0; i < size; i++) {
		sum += vector[i] * vector[i];
	}
	return sum;
}


void copyVect(double* vectorGive, double* vectorGet, int size) {
	for (int i = 0; i < size; i++) {
		vectorGet[i] = vectorGive[i];
	}
}


int main(int argc, char* argv[]) {
	int lengthMatrix = 1536;
	//int lengthMatrix = 2048;
	double barier = 0.00001;
	double t = 0.00001;


	MPI_Init(&argc, &argv);
	int size, rank;
	MPI_Comm_rank(MPI_COMM_WORLD, &rank);
	MPI_Comm_size(MPI_COMM_WORLD, &size);

	int sizePart = lengthMatrix / size;

	double* x = calloc(sizeof(double), lengthMatrix);
	if (x == NULL) {
		printf("x");
		return 1;
	}

	double* b = calloc(sizeof(double), lengthMatrix);
	if (b == NULL) {
		printf("b");
		return 1;
	}

	double* matrix = calloc(sizeof(double), lengthMatrix * sizePart);
	if (matrix == NULL) {
		printf("matrix");
		return 1;
	}

	double* outAx = calloc(sizeof(double), sizePart);
	if (outAx == NULL) {
		printf("outAx");
		return 1;
	}

	double* outAxMinusB = calloc(sizeof(double), sizePart);
	if (outAxMinusB == NULL) {
		printf("outAx");
		return 1;
	}

	double* partsSumX = calloc(sizeof(double), size);
	if (partsSumX == NULL) {
		printf("outAx");
		return 1;
	}

	double* partsSumB = calloc(sizeof(double), size);
	if (partsSumB == NULL) {
		printf("outAx");
		return 1;
	}

	double value = lengthMatrix + 1;

	create_matrix(matrix, lengthMatrix, rank, size);
	create_vector(x, lengthMatrix, 0);
	create_vector(b, lengthMatrix, value);

	double startTime = MPI_Wtime();
	double shiftBefore = DBL_MAX;

	double e = 1;

	int i = 0;
	double sumPartX[1];
	double sumPartB[1];
	while (1) {
		double sumX = 0;
		double sumB = 0;

		matrixMultVector(outAx, matrix, x, lengthMatrix, size, rank);
		vectorMinusVector(outAx, outAx, b + sizePart * rank, sizePart);
		copyVect(outAx, outAxMinusB, sizePart);
		multVectInConst(outAx, t, sizePart);
		vectorMinusVector(outAx, x + sizePart * rank, outAx, sizePart);
		MPI_Allgather(outAx, sizePart, MPI_DOUBLE, x, sizePart, MPI_DOUBLE, MPI_COMM_WORLD);

		sumPartX[0] = quadrSum(outAxMinusB, sizePart);
		sumPartB[0] = quadrSum(b + rank * sizePart, sizePart);
		//printf("Successed! on iterationffff %lf\n", sumPartX[0]);
		MPI_Allgather(sumPartX, 1, MPI_DOUBLE, partsSumX, 1, MPI_DOUBLE, MPI_COMM_WORLD);
		MPI_Allgather(sumPartB, 1, MPI_DOUBLE, partsSumB, 1, MPI_DOUBLE, MPI_COMM_WORLD);
		for (int i = 0; i < size; i++) {
			sumX += partsSumX[i];
			sumB += partsSumB[i];
		}
		double shift = sumX / sumB;
		if (shift < barier * barier) {
			if (rank == 0) printf("Successed! on iteration %d\n", i);
			e = shift;
			break;
		}

		i++;
	}


	double endTime = MPI_Wtime();
	if (rank == 0) {
		printf("%f\n", e);
		printf("Programm took %f seconds\n", endTime - startTime);
	}

	free(outAxMinusB);
	free(partsSumX);
	free(partsSumB);
	free(matrix);
	free(outAx);
	free(b);
	free(x);

	MPI_Finalize();
	return 0;
}
