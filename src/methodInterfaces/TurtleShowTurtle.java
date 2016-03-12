package methodInterfaces;
import frontend.SingleTurtle;

public class TurtleShowTurtle implements TurtleInterface {
	
	@Override
	public double executeCommand(double[] args, SingleTurtle display) {
		display.showTurtle();
		return 1;
	}
	
}
