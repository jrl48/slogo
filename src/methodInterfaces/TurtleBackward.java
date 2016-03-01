package methodInterfaces;
import frontend.Display;

public class TurtleBackward implements TurtleInterface {

	@Override
	public double executeCommand(double[] args, Display display) {
		display.moveTurtleForward(args[0]*(-1));
		return args[0];
	}
}
