package frontend.turtle;

/**
 * Simple abstract class that will execute a certain command to a certain turtle
 * given the inital conditions.
 * 
 * Used for animation.
 * 
 * @author AlanCavalcanti
 *
 */
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

	/**
	 * Abstract class, to be implemented for each type of action. It will be called by
	 * the step() method in Animaiton Controller, each time a frame of animation needs to be done.
	 * Return a boolean that specifies if the action is complete.
	 * The parameter is the speed with which the turtle will execute this certain command
	 * 
	 * @param speed
	 * @return
	 */
	public abstract boolean takeAction(double speed);
}
