package backend;

public class BooleanAnd implements MathInterface{
	public double executeCommand(double[] args){
		if(args[0] != 0 && args[1] != 0){
			return 1;
		}
		return 0;
	}
}
