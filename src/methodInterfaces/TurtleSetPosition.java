package methodInterfaces;
import frontend.Display;

public class TurtleSetPosition implements TurtleInterface {
	
	@Override
	public double executeCommand(int[] args, Display display) {
		double currX = display.getTurtleX();
		double currY = display.getTurtleY();
		display.setTurtleCoordinates(args[0], args[1]);
		return Math.sqrt(Math.pow(args[0]-currX,2) + Math.pow(args[1]-currY,2));
	}
	
}
