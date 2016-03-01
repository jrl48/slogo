package methodInterfaces;

import frontend.Display;

public class TurtleXCor implements TurtleInterface {
	
	@Override
	public double executeCommand(double[] args, Display display) {
		return display.getTurtleX();
	}

}
