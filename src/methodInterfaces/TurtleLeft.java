package methodInterfaces;
import frontend.Display;

public class TurtleLeft implements TurtleInterface {
	
	@Override
	public double executeCommand(int[] args, Display display) {
		display.turnTurtle(args[0]*(-1));
		return args[0];
	}
	
}
