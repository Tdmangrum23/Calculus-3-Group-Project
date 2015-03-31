package Part2;
/**
 * Operations needed for the encoding part
 * @author Vishaak Ravi
 * @version 1.0
**/
public class MatrixOpsEncode {
	
	/**
     * Multiply two matrices by each other
     * @param m1 matrix to be multiplied by
     * @param m2 matrix to be multiplied by
     * @return the new matrix that is the result
     */
    public static EncodeMatrix matrixMultiply(EncodeMatrix m1, EncodeMatrix m2) {
        if (m1.width != m2.height) {
            throw new IllegalArgumentException("width and length are different");
        }

        double[][] holder = new double[m1.height][m2.width];
        for (int i = 0; i < m1.height; i++) {
            for (int j = 0; j < m2.width; j++) {
                double sum = 0;
                for (int row = 0, col = 0; row < m2.height; row++, col++) {
                    sum += m1.get(i, col) * m2.get(row, j);
                }
                holder[i][j] = sum;
            }
        }
        return new EncodeMatrix(holder);
    }
    
    /**
     * Multiply matrix by scalar
     * @param m1 matrix to be multiplied by
     * @param scalar scalar to be multiplied by
     * @return the new matrix that is the result
     */
    public static EncodeMatrix scalarMultiply(EncodeMatrix m1, double scalar) {

        double[][] holder = new double[m1.height][m1.width];
        for (int row = 0; row < m1.height; row++) {
            for (int col = 0; col < m1.width; col++) {
                holder[row][col] = scalar * m1.get(row, col);
            }
        }
        return new EncodeMatrix(holder);
    }

    
    /**
     * Magnitude of the vector
     * @param m1 matrix that will have the magnitude of 
     * @return the new matrix that is the result
     */
    public static double magnitudeVector(EncodeMatrix m) {
        if (m.width != 1) {
            throw new IllegalArgumentException("Not a vector");
        }
        double squareSum = 0;
        for (int i = 0; i < m.height; i++) {
            squareSum += Math.pow(m.get(i, 0), 2);
        }

        return Math.sqrt(squareSum);
    }
}
    
