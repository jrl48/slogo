package backend;

public class BooleanNot implements MathInterface{
	public double executeCommand(double[] args){
		if(args[0] == 0){
			return 1;
		}
		return 0;
	}
}
