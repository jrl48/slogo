package methodInterfaces;
import frontend.Display;

public class TurtlePenDown implements TurtleInterface {
	
	@Override
	public double executeCommand(int[] args, Display display) {
		display.turtlePenDown();
		return 1;
	}
	
}
