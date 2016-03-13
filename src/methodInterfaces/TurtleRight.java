package methodInterfaces;
import frontend.turtle.SingleTurtle;

public class TurtleRight implements TurtleInterface {
	
	@Override
	public double executeCommand(double[] args, SingleTurtle display) {
		display.turnTurtle(args[0]);
		return args[0];
	}
	
}
