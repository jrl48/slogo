package methodInterfaces;
import frontend.Display;

public class TurtleShowTurtle implements TurtleInterface {
	
	@Override
	public double executeCommand(double[] args, Display display) {
		display.showTurtle();
		return 1;
	}
	
}
