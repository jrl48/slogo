package methodInterfaces;
import frontend.turtle.SingleTurtle;

public class TurtleForward implements TurtleInterface {
	
	@Override
	public double executeCommand(double[] args, SingleTurtle display) {
		display.moveTurtleForward(args[0]);
		return args[0];
	}
}
