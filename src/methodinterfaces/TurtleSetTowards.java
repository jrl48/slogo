package methodinterfaces;
import frontend.turtle.SingleTurtle;

public class TurtleSetTowards implements TurtleInterface {
	
	@Override
	public double executeCommand(double[] args, SingleTurtle display) {
		display.setTurtleAngle(args[1]/args[0]);
		return display.getTurtleAngle();
	}
	
}
