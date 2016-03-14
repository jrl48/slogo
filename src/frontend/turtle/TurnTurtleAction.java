package frontend.turtle;

/**
 * Action describing a turn of the turtle.
 * 
 * @author AlanCavalcanti
 *
 */
public class TurnTurtleAction extends TurtleAction
{
	private double startAngle;
	private double endAngle;
	private double currentAngle;
	private int isRight;

	/**
	 * Constructor, takes intial conditions and stores them.
	 * 
	 * @param turtle
	 * @param startAngle
	 * @param endAngle
	 * @param isRight
	 */
	public TurnTurtleAction(SingleTurtle turtle, double startAngle, double endAngle, boolean isRight) 
	{
		super(turtle);
		this.startAngle = startAngle;
		this.endAngle = endAngle;
		
		currentAngle = startAngle;

		if ( isRight )
		{
			this.isRight = 1;
		}
		else
		{
			this.isRight = -1;
		}
	}

	/**
	 * Turns until necessary, then stops.
	 */
	@Override
	public boolean takeAction(double speed) 
	{
		currentAngle += speed * isRight;

		// Ended the turning
		if ( Math.abs(currentAngle - endAngle) < speed + 1 )
		{
			getTurtle().setVisualAngle(endAngle);
			return true;
		}
		else
		{
			getTurtle().setVisualAngle(currentAngle);
		}
	
		return false;
	}
}
