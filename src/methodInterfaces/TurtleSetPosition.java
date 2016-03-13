package methodInterfaces;
import frontend.turtle.SingleTurtle;

public class TurtleSetPosition implements TurtleInterface {
	
	@Override
	public double executeCommand(double[] args, SingleTurtle display) {
		double currX = display.getTurtleX();
		double currY = display.getTurtleY();
		display.setTurtleCoordinates(args[0], args[1]);
		return Math.sqrt(Math.pow(args[0]-currX,2) + Math.pow(args[1]-currY,2));
	}
	
}
