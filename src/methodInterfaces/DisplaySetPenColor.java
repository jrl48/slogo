package methodInterfaces;

import frontend.SingleTurtle;

public class DisplaySetPenColor implements TurtleInterface {

	@Override
	public double executeCommand(double[] args, SingleTurtle display) {
		//set pen color to args[0]
		return args[0];
	}

}
