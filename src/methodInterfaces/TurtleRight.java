package methodInterfaces;
import frontend.Display;

public class TurtleRight implements TurtleInterface {
	
	@Override
	public double executeCommand(int[] args, Display display) {
		display.turnTurtle(args[0]);
		return args[0];
	}
	
}
