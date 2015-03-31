import Jama.Matrix;

import java.util.ArrayList;

public class LUDecomp extends Operations {


    public static Matrix lu_fact(double[][] A) {
        Matrix U = stabilize(A);
        ArrayList<Matrix> eMatrices = new ArrayList<>();
        int rowLength = A.length;
        int colLength = A[0].length;
        int reducingRowIndex = 0;
        for (int col = 0; col <= colLength; col++) {
            for (int row = col + 1; row < rowLength; row++) {
                Matrix elementaryMatrix = Matrix.identity(rowLength, colLength);
                reducingRowIndex = col;
                double entry = U.get(reducingRowIndex, col);
                //System.out.println("Entry  " + entry + "  Row  " + (reducingRowIndex) + "  Col  " + (col));

                //use row corresponding to reduce other rows for given column
                double nextColEntry = U.get(row, col);
                if (nextColEntry != 0) {

                    double reducingFactor = nextColEntry / entry;
                    //System.out.println("ReducingFactor " + reducingFactor);
                    //System.out.println("");
                    double[] reducingVector = Operations.vectorMultiplyByScalar(Operations.getRow(A, reducingRowIndex + 1), reducingFactor);
                    double[] currentRow = Operations.getRow(A, row + 1);
                    double[] newRow = Operations.vectorSubtraction(currentRow, reducingVector);
                    Operations.setRow(U, row + 1, newRow);
                    //U.print(5, 3);
                    double[] reducingIVector = Operations.vectorMultiplyByScalar(Operations.getRow(elementaryMatrix.getArray(), reducingRowIndex + 1), reducingFactor);
                    double[] currentIRow = Operations.getRow(elementaryMatrix.getArray(), row + 1);
                    double[] newIRow = Operations.vectorSubtraction(currentRow, reducingVector);
                    Operations.setRow(elementaryMatrix, row + 1, newRow);
                    System.out.println("element Matrix: ");
                    elementaryMatrix.print(5, 3);
                    eMatrices.add(elementaryMatrix);
                }
            }
        }
        Matrix L = Operations.transpose((eMatrices.get(0).getArray()));
        for (int index = 1; index < eMatrices.size(); index++) {
            L = Operations.multiplication(L.getArray(), eMatrices.get(index).getArray());
        }
        System.out.println("L is: ");
        L.print(5, 5);
        System.out.println("And U is ");
        U.print(5, 5);
        return U;
    }


    /**
     * Preprocessing the matrix so that...
     * @param A
     * @return
     */
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
        matrix.print(5, 5);
        return matrix;
    }



}



