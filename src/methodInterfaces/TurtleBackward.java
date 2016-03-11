package methodInterfaces;
import frontend.Display;
import frontend.SingleTurtle;

public class TurtleBackward implements TurtleInterface {

	@Override
	public double executeCommand(double[] args, SingleTurtle turtle) {
		turtle.moveTurtleForward(args[0]*(-1));
		return args[0];
	}
}
