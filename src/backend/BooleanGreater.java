package backend;

public class BooleanGreater implements MathInterface{
	public double executeCommand(double args[]){
		if(args[0] > args[1]){
			return 1;
		}
		return 0;
	}
}