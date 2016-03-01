package methodInterfaces;

public class MathTan implements MathInterface {
	public double executeCommand(double[] args){
		return Math.tan(args[0]);
	}
}
