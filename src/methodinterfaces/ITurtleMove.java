package methodinterfaces;

import frontend.turtle.SingleTurtle;

public interface ITurtleMove {
	public double[] move(SingleTurtle turtle, double deltaX, double deltaY, double length);
}
