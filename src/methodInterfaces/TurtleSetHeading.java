package methodInterfaces;
import frontend.Display;

public class TurtleSetHeading implements TurtleInterface {
	
	@Override
	public double executeCommand(double[] args, Display display) {
		double currAngle = display.getTurtleAngle();
		display.setTurtleAngle(args[0]);
		return args[0]-currAngle;
	}
	
}
