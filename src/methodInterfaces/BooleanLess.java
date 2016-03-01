package methodInterfaces;

public class BooleanLess implements MathInterface{
	public double executeCommand(double args[]){
		if(args[0] < args[1]){
			return 1;
		}
		return 0;
	}
}
