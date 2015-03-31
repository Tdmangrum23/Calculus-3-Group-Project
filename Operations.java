import Jama.Matrix;

public class Operations {

    public static Matrix multiplication(double[][] X, double[][] Y) {
        if (X[0].length != Y.length) {
            throw new IllegalArgumentException("Dimensions do not match");
        }

        double[][] Z = new double[X.length][Y[0].length];

        for (int i = 0; i < Z.length; i++) {
            for (int j = 0; j < Z[0].length; j++) {
                for (int k = 0; k < X[0].length; k++) {
                    Z[i][j] += X[i][k] * Y[k][j];
                }
            }
        }

        return new Matrix(Z);
    }

    public static double[][] deepCopy(double[][] matrix) {
        double[][] newM = new double[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                newM[i][j] = matrix[i][j];
            }
        }
        return newM;
    }

    public static double[] matVecMult(double[][] matrix, double[] v) {
        double[] b = new double[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < v.length; j++) {
                b[i] += matrix[i][j] * v[j];
            }
        }

        return b;
    }


    public static double[][] transpose(double[][] matrix) {
        double[][] Tmat = deepCopy(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                Tmat[i][j] = matrix[j][i];
                Tmat[j][i] = matrix[i][j];
            }
        }

        return Tmat;
    }

    public static double[][] matrixSubtraction(double[][] X, double[][] Y) {
        double[][] Z = new double[X.length][X[0].length];

        for (int i = 0; i < X.length; i++) {
            for (int j = 0; j < X[0].length; j++) {
                Z[i][j] = X[i][j] - Y[i][j];
            }
        }

        return Z;
    }

    public static double[][] matrixAddition(double[][] X, double[][] Y) {
        double[][] Z = new double[X.length][X[0].length];

        for (int i = 0; i < X.length; i++) {
            for (int j = 0; j < X[0].length; j++) {
                Z[i][j] = X[i][j] + Y[i][j];
            }
        }

        return Z;
    }

    public static Matrix rowEchelonForm(double[][] A) {
        Matrix matrix = stabilize(A);
        int rowLength = A.length;
        int colLength = A[0].length;
        int reducingRowIndex = 0;
        for (int col = 0; col <= colLength; col++) {
            for (int row = col + 1; row < rowLength; row++) {
                reducingRowIndex = col;
                double entry = matrix.get(reducingRowIndex, col);
                //System.out.println("Entry  " + entry + "  Row  " + (reducingRowIndex) + "  Col  " + (col));

                //use row corresponding to reduce other rows for given column
                double nextColEntry = matrix.get(row, col);
                if (nextColEntry != 0) {

                    double reducingFactor = nextColEntry / entry;
                    //System.out.println("ReducingFactor " + reducingFactor);
                    //System.out.println("");
                    double[] reducingVector = Operations.vectorMultiplyByScalar(Operations.getRow(A, reducingRowIndex + 1), reducingFactor);
                    double[] currentRow = Operations.getRow(A, row + 1);
                    double[] newRow = Operations.vectorSubtraction(currentRow, reducingVector);
                    Operations.setRow(matrix, row + 1, newRow);
                    matrix.print(5, 3);
                }
            }
        }
        return matrix;
    }


        /**
         * NEED TO ADD AN INDEXOUTOFBOUNDSEXCEPTION !!!
         *
         * @param A        a martrix
         * @param rowIndex index of the row to be printed
         */

    public static void printRow(double[][] A, int rowIndex) {
        Matrix matrix = new Matrix(A);
        System.out.println(" ");
        for (int col = 0; col < A.length; col++) {
            System.out.print(matrix.get(rowIndex - 1, col) + "   ");
        }
    }

    /**
     * NEED TO ADD AN INDEXOUTOFBOUNDSEXCEPTION !!!
     *
     * @param A        a martrix
     * @param colIndex index of the column to be printed
     */
    public static void printColumn(double[][] A, int colIndex) {
        Matrix matrix = new Matrix(A);
        System.out.println(" ");
        for (int row = 0; row < (A[colIndex - 1]).length; row++) {
            System.out.println(matrix.get(row, colIndex - 1) + "   ");
        }
    }


    public static double[] getRow(double[][] A, int rowIndex) {
        Matrix matrix = new Matrix(A);
        double[] row = new double[A.length];
        for (int col = 0; col < A.length; col++) {
            row[col] = matrix.get(rowIndex - 1, col);
        }
        return row;
    }

    /**
     * Sets row of Matrix object to row given
     *
     * @param matrix   a Jama.Matrix object
     * @param rowIndex an int
     * @param newRow   a double[]
     * @return a Jama.Matrix object
     */
    public static Matrix setRow(Matrix matrix, int rowIndex, double[] newRow) {
        if (matrix.getRowDimension() != newRow.length) {
            throw new IllegalArgumentException("Row is not the same length.");
        }
        for (int col = 0; col < matrix.getRowDimension(); col++) {
            matrix.set(rowIndex - 1, col, newRow[col]);
        }
        return matrix;
    }

    /**
     * Sets row of Matrix object to row given
     *
     * @param A        a double[][]
     * @param rowIndex an int
     * @param newRow   a double[]
     * @return a double[][]
     */
    public static double[][] setRow(double[][] A, int rowIndex, double[] newRow) {
        if (A.length != newRow.length) {
            throw new IllegalArgumentException("Row is not the same length.");
        }
        for (int col = 0; col < A.length; col++) {
            A[rowIndex - 1][col] = newRow[col];
        }
        return A;
    }

    public static double[] vectorMultiplyByScalar(double[] vector, double scalar) {
        for (int col = 0; col < vector.length; col++) {
            vector[col] *= scalar;
        }
        return vector;
    }

    public static double[] vectorSubtraction(double[] vectorA, double[] vectorB) {
        if (vectorA.length != vectorB.length) {
            throw new IllegalArgumentException("Rows are not the same length.");
        }
        for (int index = 0; index < vectorA.length; index++) {
            System.out.println("vA  " + vectorA[index] + "    vB  " + vectorB[index]);
            vectorA[index] = vectorA[index] - vectorB[index];
        }
        return vectorA;
    }

    public static boolean equalRows(double[] vectorA, double[] vectorB) {
        if (vectorA.length != vectorB.length) {
            throw new IllegalArgumentException("Rows are not the same length.");
        }
        for (int entry = 0; entry < vectorA.length; entry++) {
            if (vectorA[entry] != vectorB[entry]) {
                return false;
            }
        }
        return true;
    }

    public static void swapRows(Matrix matrix, int row1, int row2) {
        int row1Index = row1 - 1;
        int row2Index = row2 - 1;
        if (equalRows(getRow(matrix.getArray(), row1), getRow(matrix.getArray(), row2))) {
            return;
        }
        double[] temp = getRow(matrix.getArray(), row1);
        //Sets row1 to row2
        matrix = setRow(matrix, row1, getRow(matrix.getArray(), row2));
        //matrix.print(5, 3);
        matrix = setRow(matrix, row2, temp);
        //matrix.print(5,3);
    }


    private static Matrix stabilize(double[][] A) {
        Matrix matrix = new Matrix(A);
        int rowLength = A.length;
        int colLength = A[0].length;
        int rowIndex = 0;
        for (int row = 0; row < rowLength; row++) {
            for (int col = row + 1; col < colLength; col++) {
                if (matrix.get(row, col) == 0) {
                    int r = row;
                    int c = col;
                    while (matrix.get(r, c) == 0) {
                        if (r == rowLength) {
                            r = row - 1;
                        }
                        r++;
                    }
                    Operations.swapRows(matrix, row + 1, r + 1);
                }

            }
        }
        matrix.print(5, 3);
        return matrix;
    }


}
