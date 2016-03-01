package methodInterfaces;
import frontend.Display;

public class TurtleForward implements TurtleInterface {
	
	@Override
	public double executeCommand(double[] args, Display display) {
		display.moveTurtleForward(args[0]);
		return args[0];
	}
}
