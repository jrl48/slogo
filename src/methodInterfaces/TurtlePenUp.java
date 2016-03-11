package methodInterfaces;
import frontend.Display;

public class TurtlePenUp implements TurtleInterface {
	
	@Override
	public double executeCommand(double[] args, SingleTurtle turtle) {
		turtle.turtlePenUp();
		return 0;
	}
	
}
