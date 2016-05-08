package methodinterfaces;

import frontend.turtle.SingleTurtle;

public class TurtleMoveWrap implements ITurtleMove {

	@Override
	public double[] move(SingleTurtle turtle, double deltaX, double deltaY, double length) {
		// TODO Auto-generated method stub
		turtle.setTurtleEdge(3);
		turtle.moveTurtleForward(length);
		double wrapX = getWrapCoordinate(turtle.getTurtleX(), turtle.getDisplayWidth());
		double wrapY = getWrapCoordinate(turtle.getTurtleY(), turtle.getDisplayHeight());
		turtle.setTurtleCoordinates(wrapX, wrapY);
		turtle.setTurtleEdge(1);
		double[] newPosition = new double[2];
		newPosition[0] = turtle.getTurtleX() + deltaX;
		newPosition[1] = turtle.getTurtleY()+deltaY;
		
		return newPosition;
	}
	
	private double getWrapCoordinate(double x, double displayLength){
		if( Math.abs(x) == displayLength/2){
			return -1*x;
		}
		return x;
	}

}
