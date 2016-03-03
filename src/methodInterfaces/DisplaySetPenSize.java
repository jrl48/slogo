package methodInterfaces;

import frontend.Display;

public class DisplaySetPenSize implements TurtleInterface {

	@Override
	public double executeCommand(double[] args, Display display) {
		//set pen size to args[0]
		return args[0];
	}

}
