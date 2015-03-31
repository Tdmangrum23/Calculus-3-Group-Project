import Jama.Matrix;

public class Main{

	public static void main(String[] args) {
		Operations op = new Operations();
		double[][] vals = {{1., 0., 3}, {4., 5., 6.}, {7., 8., 10.}};
		Matrix matrix = new Matrix(vals);
		matrix.print(5, 3);
		//op.printRow(matrix.getArray(), 2);
		//Operations.vectorSubtraction(vals[2], vals[1]);
		//LUDecomp.stabilize(matrix.getArray());
		matrix = LUDecomp.lu_fact(vals);
		//matrix.print(5, 3);
		//Operations.swapRows(matrix, 1, 2);
		//System.out.println(matrix.getRowDimension());
//		for(int i = 0; i < Operations.getRow(vals, 2).length; i++) {
//			System.out.print(Operations.getRow(vals, 2)[i] + "  ");
//		}
		//double[] newRow = {1,2,3};
		//Matrix luMatrix = LUDecomp.lu_fact(vals);
		//luMatrix.print(1,3);


	}


}