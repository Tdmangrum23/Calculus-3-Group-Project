package Part2;
/**
 * Jacobi class for part 2
 * @author Vishaak Ravi
 * @version 1.0
**/
public class Jacobi {
	/**
     * Calculates jacobi of the input
     * @param mat1 matrix one
     * @param mat2 matrix two
     * @param tol tolerance that is used
     * @return the answer 
     */
    public static double jacobi(EncodeMatrix mat1, EncodeMatrix mat2, double tol) {
        double error = tol;
        double pValue;
        double currentValue = 0;
        double[][] xArray = {{1}, {1}};
        int k = 0;
        EncodeMatrix xk = new EncodeMatrix(xArray);
        while (error >= tol) {
            k++;
            double O;
            EncodeMatrix result = new EncodeMatrix(new double[mat1.height][1]);
            pValue = currentValue;
            for (int row = 0; row < mat1.height; row++) {
                O = 0;
                for (int col = 0; col < mat1.width; col++) {
                    if (row != col) {
                        O = O + (mat1.get(row, col) * xk.get(col, 0));
                    }
                }
                result.set(row, 0,
                    (mat2.get(row, 0) - O) / mat1.get(row, row));
            }
            currentValue = MatrixOpsEncode.magnitudeVector(result);
            xk = result;
            System.out.println(result);
            error = Math.abs(currentValue - pValue);
            System.out.println("Error:" + error);
        }
        return error;
    }
    public static void main(String[] args) {
        //double[][] matrix1 = {{8, 2, 9}, {4, 9, 4}, {6, 7, 9}};
        //double[][] matrix2 = {{3}, {4}, {5}};
        double[][] matrix1 = {{2, 1}, {5, 7}};
        double[][] matrix2 = {{11}, {13}};
        EncodeMatrix m = new EncodeMatrix(matrix1);
        EncodeMatrix b = new EncodeMatrix(matrix2);
        System.out.println(jacobi(m, b, .2));
    }
}
