import Jama.Matrix;

public class TestHouseHolder extends qr_fact_househ {
	
	public static void main (String[] args) {
		double[][] vals = {{1.0,.5,.333333,.25},{.5,.333333,.25,.2},{.333333,.25,.2,.166667},{.25,.2,.166667,.142857}};
      	Matrix A = new Matrix(vals);
		Matrix[] QR_Matrixes = factorize(A);
		Matrix Q = QR_Matrixes[0];
		Matrix R = QR_Matrixes[1];
		Q.print(10,3);
		R.print(10,3);
	}
}