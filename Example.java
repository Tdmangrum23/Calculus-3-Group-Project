import Jama.Matrix;

public class TestHouseHolder {
	
	public static void main (String[] args) {
		double[][] vals = {{1.,2.,3},{4.,5.,6.},{7.,8.,10.}};
      	Matrix A = new Matrix(vals);
		Matrix[] QR_Matrixes = factorize(A);
		Matrix Q = QR_Matrixes[0];
		Matrix R = QR_Matrixes[1];
		Q.print(10,3);
		R.print(10,3);
	}
}