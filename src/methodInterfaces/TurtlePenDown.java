package methodInterfaces;
import frontend.SingleTurtle;

public class TurtlePenDown implements TurtleInterface {
	
	@Override
	public double executeCommand(double[] args, SingleTurtle display) {
		display.turtlePenDown();
		return 1;
	}
	
}
