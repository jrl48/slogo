package methodInterfaces;
import frontend.SingleTurtle;

public class TurtleHome implements TurtleInterface {
	
	@Override
	public double executeCommand(double[] args, SingleTurtle display) {
		double currX = display.getTurtleX();
		double currY = display.getTurtleY();
		display.setTurtleCoordinates(0, 0);
		return Math.sqrt(Math.pow(currX, 2) + Math.pow(currY, 2));
	}
	
}
