package methodinterfaces;

public class MathDifference implements MathInterface{
	public double executeCommand(double[] args){
		return args[0]-args[1];
		
	}
}
