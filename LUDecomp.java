import Jama.Matrix;

public class LUDecomp extends Operations {


    public static Matrix lu_fact(double[][] A) {
        Matrix matrix = stabilize(A);
        int rowLength = A.length;
        int colLength = A[0].length;
        int reducingRowIndex = 0;
        for (int col = 0; col <= colLength; col++) {
            for(int row = col + 1; row < rowLength; row++) {
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
        matrix.print(5, 3);
        return matrix;
    }



}



