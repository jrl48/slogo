package methodInterfaces;

import frontend.SingleTurtle;

public class DisplaySetShape implements TurtleInterface {

	@Override
	public double executeCommand(double[] args, SingleTurtle display) {
		//set shape to args[0]
		return args[0];
	}

}
