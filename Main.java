import Jama.Matrix;
import java.io.*;
import java.util.Scanner;

public class Main{

	public static void main(String[] args) throws IOException {
		System.out.println("Part 1: Matrix Decompositions");
		//Retrieves dimensions of matrix
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the dimensions of your matrix (a single value will be fine)");
		int dimension = scanner.nextInt();
		//Retrieves Matrix file
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the name of your file. Or path to your file if it's in a different directory");
		String textFile = scan.nextLine();
		System.out.println("You entered: " + textFile);

		File f = new File(textFile);
		while (!f.exists()) {
			System.out.println("You entered: " + textFile);
			System.out.println("Incorrect Path. Try again. Be sure to include the .txt (or.dat) extension ");
			textFile = scan.nextLine();
			f = new File(textFile);
		}

		//Creates matrix from the file
		double[][] vals = new double[dimension][dimension];
		Scanner fileScanner = new Scanner(f);
		while (fileScanner.hasNextDouble()) {
			for (int i = 0; i < dimension; i++) {
				for (int j = 0; j < dimension; j++) {
					vals[i][j] = fileScanner.nextDouble();
				}
			}
		}
		//Creates Matrix
		Matrix matrix = new Matrix(vals);
		System.out.println(" ");
		System.out.println("You matrix is: ");
		matrix.print(8, 8);

		System.out.println("Alright, Which operation would you like to perform? Type 'q' anytime");
		System.out.println("1. lu_fact");
		System.out.println("2. solve_lu_b)");
		System.out.println("3. qr_fact_givens");
		System.out.println("4. qr_fact_househ)");
		System.out.println("5. solve_qr_b)");
		System.out.println("6. Solve for Hilbert....)");

		int quit = 0;
		while (quit != 1) {
			int choice = new Scanner(System.in).nextInt();
			if(choice != 'q') {
				if (choice == 1) {
					LUDecomp.lu_fact(vals);
				}
				if (choice == 2) {
					LUDecomp.lu_fact(vals);
				}
				if (choice == 3) {
					//LUDecomp.solve_lu_b(vals);
				}
				if (choice == 4) {
					qr_fact_givens.QR_factorize(vals);
				}
				if (choice == 5) {
					qr_fact_househ.QR_factorize(vals);
				}
				if (choice == 6) {
					//solve_qr_b(vals);
				}
				if (choice == 7) {
					//Hilbert.hilbert_solve(vals);
				}

			}
			if(choice == 'q') {
				quit = 1;
			}
		}
	}
}