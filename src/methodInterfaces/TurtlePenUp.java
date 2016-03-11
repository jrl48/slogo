package methodInterfaces;
import frontend.SingleTurtle;

public class TurtlePenUp implements TurtleInterface {
	
	@Override
	public double executeCommand(double[] args, SingleTurtle display) {
		display.turtlePenUp();
		return 0;
	}
	
}
