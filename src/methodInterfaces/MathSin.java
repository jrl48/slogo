package methodInterfaces;

public class MathSin implements MathInterface {
	public double executeCommand(double[] args){
		return Math.sin(args[0]);
	}
}
