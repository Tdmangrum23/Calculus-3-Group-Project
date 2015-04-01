import Jama.Matrix;
import java.util.Arrays;

/**
 * A class that holds a method that QR-factorizes matrices by method of
 * Givens rotations.
 *
 */
public class qr_fact_givens extends Operations {
   
    
    public static void QR_factorize(Matrix A) {
        QR_factorize(A.getArray());
    }
    
    
    /**
     * Takes in a matrix, and decomposes it into a Q matrix and an R matrix by
     * means of Givens rotations.
     * 
     * @param A the given matrix
     * @return an array of the two matrices, Q and R
     */
   public static void QR_factorize(double[][] A) {
        if (A == null) {
            throw new IllegalArgumentException();
        }

         //Keep a copy of the Matrix for error later
        double[][] errorMatrix = deepCopy(A);
        
        double[][] futureR_Matrix = A;
        double[][] futureQ_Matrix = new double[A.length][A.length];
        double[][] Q = new double[A.length][A.length];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                if (i == j) {
                    futureQ_Matrix[i][j] = 1;
                    Q[i][j] = 1;
                } else {
                    futureQ_Matrix[i][j] = 0;
                    Q[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < A[0].length; i++) {
            for (int j = i+1; j < A.length; j++) {
                double x = futureR_Matrix[i][i];
                double y = futureR_Matrix[j][i];
                double cosTheta = x / (Math.sqrt(x * x + y * y));
                double sinTheta = -(y) / (Math.sqrt(x * x + y * y));
                futureQ_Matrix[i][i] = cosTheta;
                futureQ_Matrix[j][i] = sinTheta;
                futureQ_Matrix[i][j] = -sinTheta;
                futureQ_Matrix[j][j] = cosTheta;
                
                futureR_Matrix = twoDimensionalMultiplication(futureQ_Matrix, futureR_Matrix);
                
                Q = twoDimensionalMultiplication(futureQ_Matrix, Q);
                
                for (int a = 0; a < A.length; a++) {
                    for (int b = 0; b < A.length; b++) {
                        if (a == b) {
                            futureQ_Matrix[a][b] = 1;
                        } else {
                            futureQ_Matrix[a][b] = 0;
                        }
                    }
                }
            }
        }
        
        
        Matrix Qmatrix = new Matrix(Q);
        Qmatrix = Qmatrix.transpose();
        double[][] R = futureR_Matrix;
        Matrix Rmatrix = new Matrix(R);
        Matrix[] QR = {Qmatrix, Rmatrix};

        double error = norm(matrixSubtraction(Rmatrix.getArrayCopy(), twoDimensionalMultiplication(Qmatrix.getArrayCopy(), errorMatrix)));
        Qmatrix.print(10, 6);
        Rmatrix.print(10, 6);
        System.out.println(error);
    }

}