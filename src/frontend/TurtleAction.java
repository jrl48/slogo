package frontend;

public abstract class TurtleAction 
{
	private SingleTurtle turtle;
	
	public TurtleAction(SingleTurtle turtle)
	{
		this.turtle = turtle;
	}
	
	public SingleTurtle getTurtle()
	{
		return turtle;
	}

	public abstract boolean takeAction(double speed);
}
