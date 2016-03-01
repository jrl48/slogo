package methodInterfaces;

import frontend.Display;

public class TurtleYCor implements TurtleInterface {
	
	@Override
	public double executeCommand(double[] args, Display display) {
		return display.getTurtleY();
	}

}