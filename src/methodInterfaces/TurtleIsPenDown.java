package methodInterfaces;

import frontend.Display;

public class TurtleIsPenDown implements TurtleInterface {
	
	@Override
	public double executeCommand(double[] args, Display display) {
		if (display.isTurtlePenDown())
			return 1;
		return 0;
	}

}