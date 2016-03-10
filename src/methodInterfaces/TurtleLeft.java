package methodInterfaces;
import frontend.SingleTurtle;

public class TurtleLeft implements TurtleInterface {
	
	@Override
	public double executeCommand(double[] args, SingleTurtle display) {
		display.turnTurtle(args[0]*(-1));
		return args[0];
	}
	
}
