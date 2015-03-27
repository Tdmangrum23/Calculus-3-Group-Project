import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import Jama.Matrix;

public class inputFileReader {
	
	/*public Matrix readFile() throws IOException {
		String [] args;
		BufferedReader in = new BufferedReader(new FileReader(args[0]));
		Matrix mat = Matrix.read(in);
		System.out.println(mat);
		return mat;
	}*/

	public static void main (String[] args) throws IOException {
		//readFile();
		BufferedReader in = new BufferedReader(new FileReader(args[0]));
		Matrix mat = Matrix.read(in);
		System.out.println(mat);
	}
}