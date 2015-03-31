package Part2;

/**
 * Encoding class for Part 2
 * @author Vishaak Ravi
 * @version 1.0
**/
public class Encode {
    public static void encoding(int n) {
        //make random stream x
    	EncodeMatrix x = new EncodeMatrix(new double[n + 3][1]);
        for (int i = 0; i < 3; i++) {
            x.set(i, 0, 0);
        }
        for (int i = 0; i < n; i++) {
            x.set(i, 0, (int)(Math.random() * 2));//to get zero or one
        }

        //A0 Matrix
        EncodeMatrix A0 = new EncodeMatrix(new double[n + 3][n + 3]);

        int A0index1 = 0;
        int A0index2 = 1;
        int A0index3 = 3;
        for (int row = 0; row < n + 3; row++) {
            for (int col = 0; col < n + 3; col++) {
                if (col == A0index1 || col == A0index2
                    || col == A0index3) {
                    A0.set(row, col, 1);
                } else {
                    A0.set(row, col, 0);
                }
            }
            A0index1++;
            A0index2++;
            A0index3++;
        }
        //A1 matrix
        EncodeMatrix A1 = new EncodeMatrix(new double[n + 3][n + 3]);
        int A1index1 = 0;
        int A1index2 = 2;
        int A1index3 = 3;
        for (int row = 0; row < n + 3; row++) {
            for (int col = 0; col < n + 3; col++) {
                if (col == A1index1 || col == A1index2
                    || col == A1index3) {
                    A1.set(row, col, 1);
                } else {
                    A1.set(row, col, 0);
                }
            }
            A1index1++;
            A1index2++;
            A1index3++;
        }


        EncodeMatrix y0 = MatrixOpsEncode.matrixMultiply(A0, x);
        //mod by 2
        for (int i = 0; i < y0.height; i++) {
            y0.set(i, 0, y0.get(i, 0) % 2);
        }
        //System.out.println(y0);

        EncodeMatrix y1 = MatrixOpsEncode.matrixMultiply(A1, x);
        //mod by 2
        for (int i = 0; i < y1.height; i++) {
            y1.set(i, 0, y1.get(i, 0) % 2);
        }
        //System.out.println(y1);

        for (int i = 0; i < y0.height; i++) {
            System.out.println("" + (int) y0.get(i, 0)
                + (int) y1.get(i, 0));
        }
    }
    public static void main(String[] args) {
        encoding(5);
    }


}
