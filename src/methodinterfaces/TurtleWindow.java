package methodinterfaces;

import frontend.turtle.SingleTurtle;

public class TurtleWindow implements TurtleInterface{

	@Override
	public double executeCommand(double[] args, SingleTurtle display) {
		return display.setTurtleEdge(2);
	}

}
