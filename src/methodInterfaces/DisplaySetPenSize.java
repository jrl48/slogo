package methodInterfaces;

import frontend.SingleTurtle;

public class DisplaySetPenSize implements TurtleInterface {

	@Override
	public double executeCommand(double[] args, SingleTurtle display) {
		//set pen size to args[0]
		return args[0];
	}

}
