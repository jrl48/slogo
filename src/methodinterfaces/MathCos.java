package methodinterfaces;

public class MathCos implements MathInterface{
	public double executeCommand(double[] args){
		return Math.cos(args[0]);
	}
}
