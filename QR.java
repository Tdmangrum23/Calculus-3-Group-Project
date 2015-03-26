import Jama.*;
import java.util.ArrayList;

public abstract class QR {
	
	protected Matrix Q;
	protected Matrix R;
	protected ArrayList<Matrix> eMatrix;

	protected static int r;
	protected static int c;

	public Matrix getQ() {
		return this.Q;
	}

	public Matrix getR() {
		return this.R;
	}
}
