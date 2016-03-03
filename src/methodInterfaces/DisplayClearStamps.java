package methodInterfaces;

import frontend.Display;

public class DisplayClearStamps implements TurtleInterface {

	@Override
	public double executeCommand(double[] args, Display display) {
		//clear stamps from display
		return 0;
	}

}
