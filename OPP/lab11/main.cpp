#include <malloc.h>
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <mpi.h>


void create_matrix(double* matrix, int size) {
    for (int i = 0; i < size * size; i++) {
        int tens = i / size;
        if (tens == i - tens * size) {
            matrix[i] = 2.0;
        }
        else {
            matrix[i] = 1.0;
        }
    }
    for (int i = 0; i < size * size; i++) {
        if (i % size == 0) {
            printf("\n");
        }
        printf("%.3f\t", matrix[i]);
    }
    printf("\n\n");
}


void create_vector (double* vector, int size, double value) {
    for (int i = 0; i < size; i++) {
        vector[i] = value;
    }
}


void vectorMinusVector (double* vector1,  double* vector2,int size ) {
    for (int i = 0; i < size; i++) {
        vector1[i] = vector1[i] - vector2[i];
    }
}


void multVectInConst (double* vector,  double t,int size ) {
    for (int i = 0; i < size; i++) {
        vector[i] = vector[i] * t;
    }
}


void matrixMultVector (double* inMatrix, double* outVector,  double* vector,int size ) {
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            outVector[i] += inMatrix[i * size + j] * vector[j];
        }
    }
}


int main(int argc, char* argv) {
    MPI_Init(&argc, &argv);
    int size, rank;
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Comm_size(MPI_COMM_WORLD, &size);


    int lengthMatrix = 8;
    double e = 1 / pow(10, 5);
    double t = 0.01;
    int parts = 4;


    double * outAxMinusB = calloc(sizeof(double), lengthMatrix);
    if (outAxMinusB == NULL) {
        printf("outAxMinusB");
        return 1;
    }
    double *x = calloc(sizeof(double), lengthMatrix);
    if (x == NULL) {
        printf("x");
        return 1;
    }
    double *b = calloc(sizeof(double), lengthMatrix);
    if (b == NULL) {
        printf("b");
        return 1;
    }
    double* matrix = calloc(sizeof(double), lengthMatrix * lengthMatrix);
    if (matrix == NULL) {
        printf("matrix");
        return 1;
    }
    double* outAx = calloc(sizeof(double), lengthMatrix);
    if (outAx == NULL) {
        printf("outAx");
        return 1;
    }

    double value = lengthMatrix + 1;

    create_matrix(matrix, lengthMatrix);
    create_vector(x, lengthMatrix, 0);
    //create_vector(resOperatorAx, size, 0);
    create_vector(b, lengthMatrix, value);

    for (int i = 0; i < 1000; i++) {
        matrixMultVector(matrix, outAx, x, lengthMatrix);
        vectorMinusVector(outAx, b, lengthMatrix);
        multVectInConst(outAx, t, lengthMatrix);
        vectorMinusVector(x, outAx, lengthMatrix);
    }

    for (int i = 0; i < lengthMatrix; i++) {
        printf("%.3f\n", x[i]);
    }

    free(matrix);
    free(outAx);
    free(outAxMinusB);
    free(b);
    free(x);
    MPI_Finalize();
    return 0;
}