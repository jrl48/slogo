package backend;

public class MathQuotient implements MathInterface {
	public double executeCommand(double[] args){
		return args[0]/args[1];
	}
}
