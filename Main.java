import Jama.Matrix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

public class Main{

	public static void main(String[] args) {
		Operations op = new Operations();
		double[][] vals = {{1., 2., 3}, {4., 5., 6.}, {7., 8., 10.}};
		Matrix matrix = new Matrix(vals);
		matrix.print(10,3);
		op.printRow(matrix.getArray(), 2);
		op.printColumn(matrix.getArray(), 2);


	}


}