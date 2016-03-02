package methodInterfaces;
import frontend.Display;

public class TurtleClearScreen implements TurtleInterface {
	
	@Override
	public double executeCommand(double[] args, Display display) {
		double currX = display.getTurtleX();
		double currY = display.getTurtleY();
		display.setTurtleCoordinates(0, 0);
		display.setTurtleAngle(0);
		display.clearDisplay();
		return Math.sqrt(Math.pow(currX, 2) + Math.pow(currY, 2));
	}
}
