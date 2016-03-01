package methodInterfaces;


public class MathMinus implements MathInterface{
	public double executeCommand(double[] args){
		return -args[0];
	}
}
