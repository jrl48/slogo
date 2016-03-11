package methodInterfaces;
import frontend.SingleTurtle;

public class TurtleForward implements TurtleInterface {
	
	@Override
	public double executeCommand(double[] args, SingleTurtle display) {
		display.moveTurtleForward(args[0]);
		return args[0];
	}
}
