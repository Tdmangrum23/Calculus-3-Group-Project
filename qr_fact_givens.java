import Jama.Matrix;
import java.util.Arrays;

/**
 * A class that holds a method that QR-factorizes matrices by method of
 * Givens rotations.
 *
 */
public class qr_fact_givens extends Operations {
   
    
    public static void factorize(Matrix A) {
        factorize(A.getArray());
    }
    
    
    /**
     * Takes in a matrix, and decomposes it into a Q matrix and an R matrix by
     * means of Givens rotations.
     * 
     * @param A the given matrix
     * @return an array of the two matrices, Q and R
     */
   public static void factorize(double[][] A) {
        if (A == null) {
            throw new IllegalArgumentException();
        }

         //Keep a copy of the Matrix for error later
        double[][] errorMatrix = deepCopy(A);
        
        double[][] An = A;
        double[][] Gn = new double[A.length][A.length];
        double[][] Q = new double[A.length][A.length];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                if (i == j) {
                    Gn[i][j] = 1;
                    Q[i][j] = 1;
                } else {
                    Gn[i][j] = 0;
                    Q[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < A[0].length; i++) {
            for (int j = i+1; j < A.length; j++) {
                double x = An[i][i];
                double y = An[j][i];
                double cosTheta = x / (Math.sqrt(x * x + y * y));
                double sinTheta = -(y) / (Math.sqrt(x * x + y * y));
                Gn[i][i] = cosTheta;
                Gn[j][i] = sinTheta;
                Gn[i][j] = -sinTheta;
                Gn[j][j] = cosTheta;
                
                An = twoDimensionalMultiplication(Gn, An);
                
                Q = twoDimensionalMultiplication(Gn, Q);
                
                for (int a = 0; a < A.length; a++) {
                    for (int b = 0; b < A.length; b++) {
                        if (a == b) {
                            Gn[a][b] = 1;
                        } else {
                            Gn[a][b] = 0;
                        }
                    }
                }
            }
        }
        
        
        Matrix Qmatrix = new Matrix(Q);
        Qmatrix = Qmatrix.transpose();
        double[][] R = An;
        Matrix Rmatrix = new Matrix(R);
        Matrix[] QR = {Qmatrix, Rmatrix};

        double error = norm(matrixSubtraction(Rmatrix.getArrayCopy(), twoDimensionalMultiplication(Qmatrix.getArrayCopy(), errorMatrix)));
        Qmatrix.print(10, 6);
        Rmatrix.print(10, 6);
        System.out.println(error);
    }

}