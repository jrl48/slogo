package methodInterfaces;

import frontend.Display;

public class DisplaySetPenColor implements TurtleInterface {

	@Override
	public double executeCommand(double[] args, Display display) {
		//set pen color to args[0]
		return args[0];
	}

}
