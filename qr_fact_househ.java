import Jama.Matrix;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A class that holds methods that QR-factorize matrices by method of
 * Householder Reflections.
 * 
 */
public class qr_fact_househ extends Operations {

    
    /**
     * Takes in a matrix, and decomposes it into a Q matrix and an R matrix by
     * means of Householder Reflections.
     * 
     * @param A the given matrix
     * @return an array of the two matrices, Q and R
     */
    public static void QR_factorize(Matrix A) {
        
        //Keep a copy of the Matrix for error later
        double[][] errorMatrix = deepCopy(A.getArrayCopy());
        
        int cols = A.getColumnDimension();
        int rows = A.getRowDimension();
        
        //The column; increases by one every iteration
        int[] chosenColumn = {0};
        //The starting position of the column; increases by one every iteration
        int start = 0;
        //The ending position of the column; decreases by one every iteration
        int endPos = rows - 1;
        
        ArrayList<Matrix> houseHoldersList = new ArrayList<>();
        
        Matrix R = A.copy();
        
        for (int i = 0; i < cols; i++) {

            //The identity container for the householder matrix
            Matrix container = Matrix.identity(rows, rows);
            Matrix currentMatrix = R.getMatrix(start, endPos, chosenColumn);
            Matrix u = calculateU_Matri(currentMatrix);
            
            //From here, we need matrix multiply to find Hn = I - 2uuT
            Matrix houseNRaw = multiplication(u, u.transpose());
            Matrix identyMatrix = Matrix.identity(houseNRaw.getRowDimension(),
                    houseNRaw.getColumnDimension());
            Matrix houseN = identyMatrix.minus(houseNRaw.times(2));
            
            //Put the values into the container matrix
            container.setMatrix(start, rows - 1, start, rows - 1, houseN);
            houseHoldersList.add(container);
            R = multiplication(container, R);
            chosenColumn[0] = i + 1;
            start++;
        }
        
        Matrix Q = Matrix.identity(rows, rows);
        
        for (Matrix i : houseHoldersList) {
            Q = multiplication(Q, i);
        }

        // Calculate Error
        double error = norm(matrixSubtraction(R.getArrayCopy(), twoDimensionalMultiplication(Q.getArrayCopy(), errorMatrix)));

        //Print QR Matrixes and error
        Q.print(10, 6);
        R.print(10, 6);
        System.out.println(error);
    }
    
    /**
     * Takes in a 2-dimensional array of doubles, mutates this into a
     * Jama.Matrix, and then decomposes it into a Q matrix and an R matrix by
     * means of Householder Reflections.
     * 
     * @param A the given 2-dimensional array of doubles
     * @return an array of the two matrices, Q and R
     */
    public static void QR_factorize(double[][] A) {
        QR_factorize(new Matrix(A));
    }
 
    /**
     * Calculate the normal u vector that is required to reflect the matrix.
     * 
     * @param v the n X 1 matrix whose u vector is to be found
     * @return the u vector of the given matrix
     */
    public static Matrix calculateU_Matri(Matrix v) {
        //Create e1, the first vector of the standard basis
        Matrix e = Matrix.identity(v.getRowDimension(), 1);
        //Get the magnitude of the v matrix
        double rawMagnitude = 0;
        for (int i = 0; i < v.getRowDimension(); i++) {
            double value = v.get(i,0);
            rawMagnitude += (value * value);
        }
        double magnitude = Math.sqrt(rawMagnitude);
        double firstElementV = v.get(0,0);
        firstElementV = firstElementV - magnitude;
        Matrix vMutated = v;
        vMutated.set(0,0,firstElementV);
        //Get the magnitude of the vMutated matrix
        rawMagnitude = 0;
        for (int j = 0; j < vMutated.getRowDimension(); j++) {
            double value = vMutated.get(j,0);
            rawMagnitude += (value * value);
        }
        magnitude = Math.sqrt(rawMagnitude);
        double magInverse = Math.pow(magnitude, -1.0);
        Matrix uMatrix = vMutated.times(magInverse);
        return uMatrix;
    }
}