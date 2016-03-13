package frontend;

import frontend.turtle.SingleTurtle;
import frontend.turtle.TurtleAction;

/**
 * Turtle action describing a change in pen position.
 * 
 * @author AlanCavlacnti
 *
 */
public class PenAction extends TurtleAction
{
	boolean newPenPosition;
	
	public PenAction(SingleTurtle turtle, boolean newPenPosition) 
	{
		super(turtle);
		this.newPenPosition = newPenPosition;
	}
	

	/**
	 * Since the pen doesn't really need a speed, this value can be anything
	 * becaus it is ignored.
	 */
	public boolean takeAction(double speed) 
	{
		if ( newPenPosition )
		{
			getTurtle().turtlePenDown();
		}
		else
		{
			getTurtle().turtlePenUp();
		}
		
		return true;
	}
}
