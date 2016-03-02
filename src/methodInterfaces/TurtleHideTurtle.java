package methodInterfaces;
import frontend.Display;

public class TurtleHideTurtle implements TurtleInterface {
	
	@Override
	public double executeCommand(double[] args, Display display) {
		display.hideTurtle();
		return 0;
	}
	
}
