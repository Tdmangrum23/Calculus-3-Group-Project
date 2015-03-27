package Normal;

public class Operations {
	
	public static double[][] multiplication(double[][] X, double[][] Y) {
		if (X[0].length != Y.length) {
			throw new IllegalArgumetnException("Dimensions do not match");
		}

		double[][] Z = new double[X.length][Y[0].length];

		for (int i = 0; i < Z.length; i++) {
			for (int j = 0; j < Z[0].length; j++) {
				for (int k = 0; k< X[0].length;k++) {
					Z[i][j] += X[i][k] * Y[k][j];
				}
			}
		}

		return Z;
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


}
