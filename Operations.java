import Jama.Matrix;
import java.util.Arrays;

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

    public static Matrix multiplication(Matrix x, Matrix y) {
        double[][] xDub = x.getArrayCopy();
        double[][] yDub = y.getArrayCopy();
        
        return multiplication(xDub, yDub);
    }

    public static double[][] twoDimensionalMultiplication(double[][] X, double[][] Y) {
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

        return Z;
    }

    /**
     * Hilbert Matrix
     * @param n Dimension
     * @return a n x n Hilbert Matrix
     */
    public static Matrix hilbertMatrix(int n) {
        double[][] hilArray = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                hilArray[i][j] = 1. / (1 + j + i);
            }
        }
        Matrix hilbert = new Matrix(hilArray);
        return hilbert;
    }

    /**
     * Returns the b for the hilbert matrix 
     * b = (0.1)^(n/3)* (1, 1,...,1)^t
     * @param n The length of the hilbert matrix n
     * @return b for the hilbert matrix
     */
    public static double[] hilbertB(int n) {
        double[] b = new double[n];
        double x = Math.pow(0.1, n/3.);
        for (int i = 0; i < b.length; i++) {
            b[i] = x;
        }
        return b;
    }

    public static double norm(double[][] inputMatrix) {
        return Arrays.stream(inputMatrix)
                .flatMapToDouble(x -> Arrays.stream(x))
                .map(x -> Math.abs(x))
                .max().getAsDouble();
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

    public static Matrix rowEchelonForm(double[][] A){
        Matrix matrix = new Matrix(A);
        for(int row = 0; row <= A.length; row++){
            for (int col = 0; col <= row; col++){
                double entry = matrix.get(row, col);
            }
        }
        return matrix;
    }


    /**
     * NEED TO ADD AN INDEXOUTOFBOUNDSEXCEPTION !!!
     * @param A a martrix
     * @param rowIndex index of the row to be printed
     */
    public static void printRow(double[][] A, int rowIndex) {
        Matrix matrix = new Matrix(A);
        System.out.println(" " );
        for(int col = 0; col < A.length; col++){
            System.out.print(matrix.get(rowIndex - 1, col) + "   " );
        }
    }
    /**
     * NEED TO ADD AN INDEXOUTOFBOUNDSEXCEPTION !!!
     * @param A a martrix
     * @param colIndex index of the column to be printed
     */
    public static void printColumn(double[][] A, int colIndex) {
        Matrix matrix = new Matrix(A);
        System.out.println(" ");
        for(int row = 0; row < (A[colIndex - 1]).length; row++){
            System.out.println(matrix.get(row, colIndex - 1) + "   " );
        }
    }



}