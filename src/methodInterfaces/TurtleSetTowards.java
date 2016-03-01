package methodInterfaces;
import frontend.Display;

public class TurtleSetTowards implements TurtleInterface {
	
	@Override
	public double executeCommand(double[] args, Display display) {
		display.setTurtleAngle(args[1]/args[0]);
		return display.getTurtleAngle();
	}
	
}
