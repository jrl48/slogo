package methodinterfaces;

import frontend.turtle.SingleTurtle;

public class TurtleMoveFence implements ITurtleMove {

	@Override
	public double[] move(SingleTurtle turtle, double deltaX, double deltaY, double length) {
		// TODO Auto-generated method stub
		double[] newPosition = new double[2];
		newPosition[0] = checkBounds(turtle.getTurtleX() + deltaX, turtle.getDisplayWidth());
		newPosition[1] = checkBounds(turtle.getTurtleY()+deltaY, turtle.getDisplayHeight());
		
		return newPosition;
		
	}
	
	private double checkBounds(double moveLocation, double displayLength){
		if(moveLocation>displayLength/2){return displayLength/2;}
		if(moveLocation<-1*displayLength/2){return -1*displayLength/2;}
		return moveLocation;
	}

}
