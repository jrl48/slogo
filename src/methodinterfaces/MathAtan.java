package methodinterfaces;

public class MathAtan implements MathInterface {
	public double executeCommand(double[] args){
		return Math.atan(args[0]);
	}
}
