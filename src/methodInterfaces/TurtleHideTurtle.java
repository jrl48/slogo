package methodInterfaces;
import frontend.SingleTurtle;

public class TurtleHideTurtle implements TurtleInterface {
	
	@Override
	public double executeCommand(double[] args, SingleTurtle display) {
		display.hideTurtle();
		return 0;
	}
	
}
