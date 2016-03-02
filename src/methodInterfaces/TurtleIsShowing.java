package methodInterfaces;

import frontend.Display;

public class TurtleIsShowing implements TurtleInterface {
	
	@Override
	public double executeCommand(double[] args, Display display) {
		if (display.getTurtleVisibility())
			return 1;
		return 0;
	}

}