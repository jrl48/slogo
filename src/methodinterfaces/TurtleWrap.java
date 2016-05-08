package methodinterfaces;

import frontend.turtle.SingleTurtle;

public class TurtleWrap implements TurtleInterface{

	@Override
	public double executeCommand(double[] args, SingleTurtle display) {
		return display.setTurtleEdge(1);
	}

}
