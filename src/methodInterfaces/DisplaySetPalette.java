package methodInterfaces;

import frontend.SingleTurtle;

public class DisplaySetPalette implements TurtleInterface {

	@Override
	public double executeCommand(double[] args, SingleTurtle display) {
		//set color corresponding to args[0] to args[1],args[2],args[3] (r,g,b)
		return args[0];
	}

}
