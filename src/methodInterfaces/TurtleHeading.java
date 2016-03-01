package methodInterfaces;

import frontend.Display;

public class TurtleHeading implements TurtleInterface {
	
	@Override
	public double executeCommand(double[] args, Display display) {
		return display.getTurtleAngle();
	}

}