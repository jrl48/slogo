package methodinterfaces;

import frontend.turtle.SingleTurtle;

public class TurtleMoveWindow implements ITurtleMove {
	
	@Override
	public double[] move(SingleTurtle turtle, double deltaX, double deltaY, double length) {
		double[] newPosition = new double[2];
		newPosition[0] = turtle.getTurtleX() + deltaX;
		newPosition[1] = turtle.getTurtleY() + deltaY;
		
		return newPosition;
	}

}
