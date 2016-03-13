package methodInterfaces;

import frontend.turtle.SingleTurtle;

public class TurtleIsPenDown implements TurtleInterface {
	
	@Override
	public double executeCommand(double[] args, SingleTurtle display) {
		if (display.isTurtlePenDown())
			return 1;
		return 0;
	}

}