package Part3;
import java.util.Arrays;

/** 
 * This class is used to perform the power method on a matrix with a tolerance
 * @author Vishaak Ravi
 * @version 1.0 
 */
public class Power_Method {
    private double eigenValue;
    private double iteration;
    private double[][] eigenVector;
    

    public Power_Method(double eigenValue, double[][] eigenVector, double iteration) {
        this.iteration = iteration;
        this.eigenValue = eigenValue;
        this.eigenVector = eigenVector;
    }

    /**
     * This method calculates the largest eigenvalue of the matrix with certain tolerance, and
     * it reports if the method was unable to produce a result with a number of iterations.
     * @param a the matrix for which the eigenvalues and eigenvectors are being calculated
     * @param vector the starting approximation
     * @param tol the tolerance number
     */
    public void power_method(double[][] a, double[][] vector, double tol) {
        double error = 100;
        while (error > tol && iteration <= 100) {
            double x = eigenValue;
            vector = scalarMultiplication(multiplication(a,  vector), (1 / vector[0][0]));
            eigenValue = vector[0][0];
            iteration++;
            double normal = 0;
            for (int i = 0; i < vector.length; i++) {
                normal = normal + Math.pow(vector[i][0], 2);
            }
            normal = Math.sqrt(normal);
            eigenVector = scalarMultiplication(vector, (1 / normal));
            error = Math.abs(x - eigenValue);
        }
        if (iteration >= 100) {
            System.out.println("Did not converge");
        } else {
            System.out.println("The Eigenvector is: " + Arrays.deepToString(getEigenVector()));
            System.out.println("The Eigenvalue is: " + getEigenValue());
            System.out.println("The number of iterations needed are: " + getIteration());
        }
    }

    /**
     * Method to do matrix multiplication
     * @param matrixOne the matrix to be multiplied
     * @param matrixTwo the other matrix to be multiplied 
     * @return answer the multiplied matrix
     */
    private static double[][] multiplication(double[][] matrixOne, double[][] matrixTwo) {
        if  (matrixOne[0].length != matrixTwo.length) {
            throw new IllegalArgumentException("Dimensions do not match");
        }
        double[][] answer = new double [matrixOne.length][matrixTwo[0].length];
        for (int i = 0; i < answer.length; i++) {
            for (int j = 0; j < answer[0].length; j++) {
                for (int k = 0; k< matrixOne[0].length;k++) {
                    answer[i][j] += (matrixOne[i][k] * matrixTwo[k][j]);
                }
            }
        }
        return answer;
    }

    /**
     * Method to do scalar multiplication
     * @param matrix the matrix to be multiplied
     * @param scalar the scalar to be multiplied by 
     * @return the resulting matrix
     */
    private static double[][] scalarMultiplication(double[][] matrix, double scalar) {
        double[][] answer = deepCopy(matrix);
        for (int i = 0; i < answer.length; i++) {
            for (int j = 0; j < answer[0].length; j++) {
                answer[i][j] = (answer[i][j] * scalar);
            }
        }
        return answer;
    }

    /**
     * Method to get a deep copy of a matrix
     * @param matrix the matrix to be copied
     * @return a copy of the matrix
     */
    private static double[][] deepCopy(double[][] matrix) {
        double[][] dcM = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                dcM[i][j] = matrix[i][j];
            }
        }
        return dcM;
    }

    /**
     * Getter method for the eigenvector 
     * @return the eigenvector
     */
    public double[][] getEigenVector() {
        return eigenVector;
    }

    /**
     * Getter method for the eigenvalue
     * @return the eigenvalue
     */
    public double getEigenValue() {
        return eigenValue;
    }

    /**
     * Getter method for the number of iterations
     * @return the number of iterations
     */
    public double getIteration() {
        return iteration;
    }

}
