package Part2;
/**
 * Matrix class used for Part 2 encoding
 * @author Vishaak Ravi
 * @version 1.0
**/
public class EncodeMatrix {

    private double[][] matrix;
    public int height;
    public int width;

    /**
     * Initialize instance variables
     * @param matrix 2D array representation of Matrix
     */
    public EncodeMatrix(double[][] matrix) {
        this.matrix = matrix;
        height = matrix.length;
        width = matrix[0].length;
    }

    /**
     * Gets value located at specified row and column
     * @param row the row
     * @param column the column
     * @return double located at row i and column j in matrix
     */
    public double get(int row, int column) {
        if (row < 0 || row > height || column < 0 || column > width) {
            throw new IllegalArgumentException("row and column do not match height and width");
        }
        return matrix[row][column];
    }

    /**
     * Sets input at specified row and height
     * @param row row
     * @param col column
     * @return double located at row i and column j in matrix
     */
    public double set(int row, int col, double input) {
        if (row < 0 || row > height || col < 0 || col > width) {
            throw new IllegalArgumentException("row and column do not match height and width");
        }
        double t = matrix[row][col];
        matrix[row][col] = input;
        return t;
    }

    
    /**
     * Scalar multiply for rows
     * @param scalar the scalar to be multiplied by
     * @param row the row to be multiplied
     */
    public void rowScalarMultiply(double scalar, int row) {
        for (int col = 0; col < width; col++) {
            double temp = this.get(row, col);
            this.set(row, col, scalar * temp);
        }
    }
    //row 2 = row 1 + row 2
    //second one is changed
    /**
   	 * Adding two rows to each other
     * @param row1 the row to be added
     * @param row2 the row to be added
     */
    public void rowAdd(int row1, int row2) {
        for (int col = 0; col < width; col++) {
            double row1Value = this.get(row1, col);
            double row2Value = this.get(row2, col);
            this.set(row2, col, row1Value + row2Value);
        }
    }

    /**
     * Gets String representation of matrix.
     * @return String representation of matrix.
     */
    public String toString() {
        String finalList = "";
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                finalList = finalList + String.format("%12.6f", matrix[row][col]);
            }
            finalList += "\n";
        }
        return finalList;
    }

    public double[][] toArray(){
        return matrix;
    }


}
