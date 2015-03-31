import Jama.*;
import Jama.utils;

public class QR_Factorization_Householder extends Operations {
	
	//Take in a Matrix A and perform Householder on it
	public Obj[] qr_fact_househ(Matrix inputMatrix) {
		//Get the sum of the first column of Matrix A
		double sumOfFirstColMatrix = colsum(inputMatrix, 1);
		//Get the magnitude of first column of A and store as double
		double magnitudeOfFirstCol = Math.sqrt(sumOfFirstColMatrix);
		//Make vector v by adding the magnitude to the first entry of first column of A
		double[][] vectorV_Values = inputMatrix.getcol(inputMatrix, 1).getArray();
		vectorV_Values[0][0] = vectorV_Values[0][0] + magnitudeOfFirstCol;
		Matrix vectorV = new Matrix(vectorV_Values);
		//Get the magnitude of vector v and store as int
		double magnitudeOfVectorV = colsum(vectorV);
		//Make vector u by multiplying vector v times 1 over magnitude of v
		Matrix vectorU = vectorV.times(1.0/magnitudeOfVectorV);
		//Q matrix = Identity matrix - 2 times u vector time u vector transposed
		//Initialize Identity Matrix
		int numberOfRowsVectorU = vectorU.getRowDimension();
		Matrix identityMatrix = identity(numberOfRowsVectorU, numberOfRowsVectorU);
		//Initialize Matrix for 2 times u vector time u vector transposed
		Matrix uVectorTimesU_Transposed = vectorU.matrixMultiplication(vectorU.transpose());
		uVectorTimesU_Transposed = uVectorTimesU_Transposed.times(2.0);
		//Do Q matrix subtraction (I - 2uu^t)
		Matrix Q_Matrix = identityMatrix.minus(uVectorTimesU_Transposed);
		//R matrix = Q matrix times A matrix
		//Initialize R matrix
		//Do R matrix multiplication (Q * A) 
		Matrix R_Matrix = Q_Matrix.matrixMultiplication(inputMatrix);
		Obj[] QR_Matrix = new Obj[2]
		QR_Matrix[0] = Q_Matrix;
		QR_Matrix[1] = R_Matrix;
		return QR_Matrix;
	}
}