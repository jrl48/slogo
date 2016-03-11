package methodInterfaces;
import frontend.SingleTurtle;

public class TurtleRight implements TurtleInterface {
	
	@Override
	public double executeCommand(double[] args, SingleTurtle display) {
		display.turnTurtle(args[0]);
		return args[0];
	}
	
}
