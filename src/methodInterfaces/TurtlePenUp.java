package methodInterfaces;
import frontend.Display;

public class TurtlePenUp implements TurtleInterface {
	
	@Override
	public double executeCommand(int[] args, Display display) {
		display.turtlePenUp();
		return 0;
	}
	
}
