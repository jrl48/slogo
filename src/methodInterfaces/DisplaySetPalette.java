package methodInterfaces;

import frontend.Display;

public class DisplaySetPalette implements TurtleInterface {

	@Override
	public double executeCommand(double[] args, Display display) {
		//set color corresponding to args[0] to args[1],args[2],args[3] (r,g,b)
		return args[0];
	}

}
