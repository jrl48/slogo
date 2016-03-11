package methodInterfaces;
import frontend.SingleTurtle;

public class TurtleBackward implements TurtleInterface {

	@Override
	public double executeCommand(double[] args, SingleTurtle display) {
		display.moveTurtleForward(args[0]*(-1));
		return args[0];
	}
}
