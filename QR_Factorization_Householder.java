import Jama.Matrix;

public class QR_Factorization_Householder extends Matrix {
	
	//Take in a Matrix A and perform Householder on it
	public Obj[] qr_fact_househ(Matrix inputMatrix) {
		//Get first column of Matrix A
		double[][] vals = inputMatrix.getArray();
		double[][] firstColVals = vals[1][1];
		Matrix firstCol = new Matrix(firstColVals);
		//Get the magnitude of first column of A and store as int
		//Make vector v by adding the magnitude to the first entry of first column of A
		//Get the magnitude of vector v and store as int
		//Make vector u by multiplying vector v times 1 over magnitude of v
		//Q matrix = Identity matrix - 2 times u vector time u vector transposed
		//Initialize Identity Matrix
		//Initialize Matrix for 2 times u vector time u vector transposed
		//Do Q matrix subtraction (I - 2uu^t)
		//R matrix = Q matrix times A matrix
		//Initialize R matrix
		//Do R matrix multiplication (Q * A) 
	}
}