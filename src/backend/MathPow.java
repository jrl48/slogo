package backend;

public class MathPow implements MathInterface {
	public double executeCommand(double[] args){
		return Math.pow(args[0],  args[1]);
	}
}
