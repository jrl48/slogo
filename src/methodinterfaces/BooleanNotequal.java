package methodinterfaces;

public class BooleanNotequal implements MathInterface {
	public double executeCommand(double[] args){
		if(args[0] != args[1]){
			return 1;
		}
		return 0;
	}
}
