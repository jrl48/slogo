package frontend;

public class PenAction extends TurtleAction
{
	boolean newPenPosition;
	
	public PenAction(SingleTurtle turtle, boolean newPenPosition) 
	{
		super(turtle);
		this.newPenPosition = newPenPosition;
	}
	

	public boolean takeAction(double speed) 
	{
		if ( newPenPosition )
			getTurtle().turtlePenDown();
		else
			getTurtle().turtlePenUp();
		
		return true;
	}
}
