#include <malloc.h>
#include <stdio.h>
#include <stdlib.h>
#include <omp.h>


void matrixPadding(double* matrix, int size) {
#pragma omp for   
	for (int i = 0; i < size * size; i++) {
		int tens = i / size;
		if (tens == i - tens * size) {
			matrix[i] = 2.0;
		}
		else {
			matrix[i] = 1.0;
		}
	}
	//for (int i = 0; i < size * size; i++) {
	//	if (i % size == 0) {
	//		printf("\n");
	//	}
	//	printf("%f\t", matrix[i]);
	//}
	//printf("\n\n");
}


void vectorPadding(double* vector, int size, const double value) {
#pragma omp for  
	for (int i = 0; i < size; i++) {
		vector[i] = value;
	}

}


void vectorMinusVector(double* resVect, double* vector1, double* vector2, int size) {
#pragma omp for  
	for (int i = 0; i < size; i++) {
		resVect[i] = vector1[i] - vector2[i];
	}
}


void multVectInConst(double* vector, double t, int size) {
#pragma omp for  
	for (int i = 0; i < size; i++) {
		vector[i] = vector[i] * t;
	}
}


double quadrSum(double* vector, int size) {
	double sum = 0;
#pragma omp for reduction(+: sum)
	for (int i = 0; i < size; i++) {
		sum += vector[i] * vector[i];
	}
	return sum;
}


void copyVect(double* vectorGive, double* vectorGet, int size) {
#pragma omp for  
	for (int i = 0; i < size; i++) {
		vectorGet[i] = vectorGive[i];
	}
}


void matrixMultVector(double* inMatrix, double* outVector, double* vector, int size) {
#pragma omp for
	for (int i = 0; i < size; i++) {
		for (int j = 0; j < size; j++) {
			outVector[i] += inMatrix[i * size + j] * vector[j];
		}
	}
}


int main(int argc, char* argv[]) {
	int lengthMatrix = 8000;
	int capacityProc = 12;
	if (argc != 1) {
		capacityProc = atoi(argv[1]);
		lengthMatrix = atoi(argv[2]);
	}
	omp_set_num_threads(capacityProc);

	double barier = 0.00001;
	double t = 0.00001;

	double* x = (double*)calloc(sizeof(double), lengthMatrix);
	if (x == NULL) {
		printf("x");
		return 1;
	}
	double* b = (double*)calloc(sizeof(double), lengthMatrix);
	if (b == NULL) {
		printf("b");
		return 1;
	}
	double* matrix = (double*)calloc(sizeof(double), lengthMatrix * lengthMatrix);
	if (matrix == NULL) {
		printf("matrix");
		return 1;
	}
	double* outAx = (double*)calloc(sizeof(double), lengthMatrix);
	if (outAx == NULL) {
		printf("outAx");
		return 1;
	}

	double value = lengthMatrix + 1;

	double startTime = omp_get_wtime();
	int error = 1;
	bool isWork = true;
#pragma omp	parallel shared(isWork)
	{
		matrixPadding(matrix, lengthMatrix);
		vectorPadding(x, lengthMatrix, 0);
		vectorPadding(b, lengthMatrix, value);
		double SumB = quadrSum(b, lengthMatrix);

		for (int i = 0; isWork && i < 10000; i++) {
			vectorPadding(outAx, lengthMatrix, 0);
			matrixMultVector(matrix, outAx, x, lengthMatrix);

			vectorMinusVector(outAx, outAx, b, lengthMatrix);
			double sumX = quadrSum(outAx, lengthMatrix);
			multVectInConst(outAx, t, lengthMatrix);
			vectorMinusVector(outAx, x, outAx, lengthMatrix);

			copyVect(outAx, x, lengthMatrix);

#pragma omp single
			double shift = sumX / SumB;
			if (shift < barier * barier) {
				error = 0;
				printf("Successed! on iteration %d, shift = %.16lf\n", i, shift);
				isWork = false;

			
			}
		}
	}
	

	double endTime = omp_get_wtime();
	if (error == 1) {
		printf("Please, change param t or take less matrix!\n");
	}

	printf("Programm took %lf seconds\n", endTime - startTime);


	free(matrix);
	free(outAx);
	free(b);
	free(x);
	return 0;
}
