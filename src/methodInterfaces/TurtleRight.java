package methodInterfaces;
import frontend.Display;

public class TurtleRight implements TurtleInterface {
	
	@Override
	public double executeCommand(double[] args, Display display) {
		display.turnTurtle(args[0]);
		return args[0];
	}
	
}
