package Part2;
/**
 * Gauss-Seidel class for part 2
 * @author Vishaak Ravi
 * @version 1.0
**/
public class GaussSeidel {
	/**
     * Calculates gauss_seidel of the input
     * @param mat1 matrix one
     * @param mat2 matrix two
     * @param tol tolerance that is used
     * @return the answer 
     */
    public static double gauss_seidel(EncodeMatrix mat1, EncodeMatrix mat2, double tol) {
        double error = tol;
        double pValue;
        double currentValue = 0;
        double[][] rArray = {{1}, {1}};
        int k = 0;
        EncodeMatrix result = new EncodeMatrix(rArray);
        while (error >= tol) {
            k++;
            double O;
            pValue = currentValue;
            for (int row = 0; row < mat1.height; row++) {
                O = 0;
                for (int col = 0; col < mat1.width; col++) {
                    if (col != row) {
                        O = O + (mat1.get(row, col) * result.get(col, 0));
                    }
                }
                result.set(row, 0,
                    (mat2.get(row, 0) - O) / mat1.get(row, row));
            }
            currentValue = MatrixOpsEncode.magnitudeVector(result);
            System.out.println(result);
            error = Math.abs(currentValue - pValue);
            System.out.println("Error:" + error);
        }
        return error;

    }
    public static void main(String[] args) {
        //double[][] matrix1 = {{8, 2, 9}, {4, 9, 4}, {6, 7, 9}};
        //double[][] matrix2 = {{3}, {4}, {5}};
        double[][] matrix1 = {{16, 3}, {7, -11}};
        double[][] matrix2 = {{11}, {13}};
        EncodeMatrix m = new EncodeMatrix(matrix1);
        EncodeMatrix b = new EncodeMatrix(matrix2);
        System.out.println(gauss_seidel(m, b, .2));
    }
}
