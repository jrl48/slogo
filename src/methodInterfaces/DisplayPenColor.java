package methodInterfaces;

import frontend.Display;

public class DisplayPenColor implements TurtleInterface {

	@Override
	public double executeCommand(double[] args, Display display) {
		//return display.getPenColor();
		return 0;
	}

}
