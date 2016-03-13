package methodInterfaces;
import frontend.turtle.SingleTurtle;

public class TurtleBackward implements TurtleInterface {

	@Override
	public double executeCommand(double[] args, SingleTurtle display) {
		display.moveTurtleForward(args[0]*(-1));
		return args[0];
	}
}
