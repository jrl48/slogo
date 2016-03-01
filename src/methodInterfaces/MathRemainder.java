package methodInterfaces;

public class MathRemainder implements MathInterface{
	public double executeCommand(double[] args){
		return args[0] % args[1];
	}
}
