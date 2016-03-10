package methodInterfaces;

import frontend.SingleTurtle;

public class DisplaySetBackground implements TurtleInterface {

	@Override
	public double executeCommand(double[] args, SingleTurtle display) {
		//set background to args[0]
		return args[0];
	}

}
