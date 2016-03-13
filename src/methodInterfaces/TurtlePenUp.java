package methodInterfaces;
import frontend.turtle.SingleTurtle;

public class TurtlePenUp implements TurtleInterface {
	
	@Override
	public double executeCommand(double[] args, SingleTurtle display) {
		display.turtlePenUp();
		return 0;
	}
	
}
