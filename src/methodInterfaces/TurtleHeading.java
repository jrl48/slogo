package methodInterfaces;

import frontend.turtle.SingleTurtle;

public class TurtleHeading implements TurtleInterface {
	
	@Override
	public double executeCommand(double[] args, SingleTurtle display) {
		return display.getTurtleAngle();
	}

}