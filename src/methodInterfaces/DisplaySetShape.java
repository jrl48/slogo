package methodInterfaces;

import frontend.Display;

public class DisplaySetShape implements TurtleInterface {

	@Override
	public double executeCommand(double[] args, Display display) {
		//set shape to args[0]
		return args[0];
	}

}
