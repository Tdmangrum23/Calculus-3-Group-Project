import Jama.Matrix;

public static void main (String[] args) {
    double[][] vals = {{1.,2.,3},{4.,5.,6.},{7.,8.,10.}};
    Matrix A = new Matrix(vals);
    Obj[] matrixToPrint = qr_fact_househ(A);
    Matrix Q_Matrix = matrixToPrint[0];
    Matrix R_Matrix = matrixToPrint[1];
    Q_Matrix.print();
    R_Matrix.print();
}