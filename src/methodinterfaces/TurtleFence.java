package methodinterfaces;

import frontend.turtle.SingleTurtle;

public class TurtleFence implements TurtleInterface{

	@Override
	public double executeCommand(double[] args, SingleTurtle display) {
		return display.setTurtleEdge(3);
	}

}
