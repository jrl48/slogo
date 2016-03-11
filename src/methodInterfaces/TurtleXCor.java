package methodInterfaces;

import frontend.SingleTurtle;

public class TurtleXCor implements TurtleInterface {
	
	@Override
	public double executeCommand(double[] args, SingleTurtle display) {
		return display.getTurtleX();
	}

}
