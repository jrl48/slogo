package methodInterfaces;
import frontend.SingleTurtle;

public class TurtleSetHeading implements TurtleInterface {
	
	@Override
	public double executeCommand(double[] args, SingleTurtle display) {
		double currAngle = display.getTurtleAngle();
		display.setTurtleAngle(args[0]);
		return args[0]-currAngle;
	}
	
}
