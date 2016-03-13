package methodInterfaces;

import frontend.turtle.SingleTurtle;

public class TurtleIsShowing implements TurtleInterface {
	
	@Override
	public double executeCommand(double[] args, SingleTurtle display) {
		if (display.getTurtleVisibility())
			return 1;
		return 0;
	}

}