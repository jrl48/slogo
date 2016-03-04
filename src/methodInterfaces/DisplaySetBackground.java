package methodInterfaces;

import frontend.Display;

public class DisplaySetBackground implements TurtleInterface {

	@Override
	public double executeCommand(double[] args, Display display) {
		//set background to args[0]
		return args[0];
	}

}
