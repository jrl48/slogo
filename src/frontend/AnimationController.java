package frontend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javafx.scene.shape.Line;

/** This class is called to segment and control the movement of turtles.
 * 	That is, it maintains a register of the turtles that are currently moving (either dislocating, or turning)
 *  and updated their positions (and line lengths) accordingly.
 *  
 *  The turtle speed is set to a default, but can be controlled easily by a get/set function linked 
 *  to the UI.
 *  
 *  Additionally, should the program be extended to include animation controlling features, 
 *  the initial and final positions of each moving turtle is stored here, to be used if needed.
 *  
 * @author AlanCavalcanti
 *
 */
public class AnimationController 
{
	ArrayList<SingleTurtle> turtlesToMove;
	ArrayList<ArrayList<Double>> initialPosition;	//This position is the SLogo position, not the JavaFX position
	ArrayList<ArrayList<Double>> endPosition;
	ArrayList<ArrayList<Double>> steps;
	HashMap<Turtle, Line> lines;
	ArrayList<Turtle> moveWaitList;
	
	ArrayList<SingleTurtle> turtlesToTurn;
	ArrayList<Double> initialAngle;
	ArrayList<Double> endAngle;
	ArrayList<Integer> isRight;
	ArrayList<Turtle> turnWaitList;
	
	private double turtleMoveSpeed;
	private double turtleTurnSpeed;
	
	public AnimationController()
	{
		turtlesToMove = new ArrayList<SingleTurtle>();
		initialPosition = new ArrayList<ArrayList<Double>>();
		endPosition = new ArrayList<ArrayList<Double>>();
		steps = new ArrayList<ArrayList<Double>>();
		lines = new HashMap<Turtle, Line>();
		moveWaitList = new ArrayList<Turtle>();
		
		turtlesToTurn = new ArrayList<SingleTurtle>();
		initialAngle = new ArrayList<Double>();
		endAngle = new ArrayList<Double>();	
		isRight = new ArrayList<Integer>();
		turnWaitList = new ArrayList<Turtle>();
		
		// DEFAULT
		turtleMoveSpeed = 2; 	// pixels per step proc
		turtleTurnSpeed = 5;	// degrees per step proc 
	}
	
	/**
	 * 	This method is called by a JavaFX timeline, and updates the turtles who need to move/turn.
	 */
	public void step()
	{	
		if ( turtlesToMove.isEmpty() && turtlesToTurn.isEmpty() )
			return;

		ArrayList<Double> currentStep = new ArrayList<Double>();
		boolean ended;
		
		for ( int i = 0; i < turtlesToMove.size(); i++ )
		{
			SingleTurtle currentTurtle = turtlesToMove.get(i);
			
			if ( moveWaitList.contains(currentTurtle) )
				continue;
			
			ArrayList<Double> currentPos = (ArrayList<Double>) turtlesToMove.get(i).getCoordinates();
			ended = false;
			
			for ( int j = 0; j < steps.get(i).size(); j++ )
			{
				currentStep.add(currentPos.get(j) + steps.get(i).get(j) * turtleMoveSpeed);

				// Sees if the animation would make a bigger step than needed
				if ( initialPosition.get(i).get(j) < endPosition.get(i).get(j) )
					ended = ( currentStep.get(j) > endPosition.get(i).get(j) );

				else
					ended = ( currentStep.get(j) < endPosition.get(i).get(j) );
				
				if (ended)
					break;
			}
		
			// If the animation would make a bigger step than needed
			if ( ended )
			{
				// Simply make the new position the position it needs to be
				currentTurtle.setTurtleCoordinates(endPosition.get(i));
				turtlesToMove.remove(i);
				initialPosition.remove(i);
				endPosition.remove(i);
				steps.remove(i);
				lines.remove(currentTurtle);
				if ( turnWaitList.contains(currentTurtle) )
					turnWaitList.remove(currentTurtle);
			}
			else
			{
				Line line = lines.get(currentTurtle);
				
				currentTurtle.setTurtleCoordinates(currentStep);
				if ( line != null )
				{
					currentTurtle.updateLine(line);
				}
			}

			currentStep.clear();
			currentTurtle.updateTurtleVisualPosition(true);
		}
		
		// Now, updates the turning turtles
		for ( int i = 0; i < turtlesToTurn.size(); i++ )
		{	
			Turtle currentTurtle = turtlesToTurn.get(i);
			
			if ( turnWaitList.contains(currentTurtle) )
				continue;
			
			double currentAngle = currentTurtle.getTurtleAngle() + turtleTurnSpeed * isRight.get(i);

			// Ended the turning
			if ( Math.abs(currentAngle - endAngle.get(i)) < turtleTurnSpeed + 1 )
			{
				currentTurtle.setTurtleAngle(endAngle.get(i));
				turtlesToTurn.remove(i);
				initialAngle.remove(i);
				endAngle.remove(i);
				isRight.remove(i);
				if ( moveWaitList.contains(currentTurtle) )
					moveWaitList.remove(currentTurtle);
			}
			else
				currentTurtle.setTurtleAngle(currentAngle);
		}
	}
	
	/**
	 * This method adds to the list of Turtles that are bound to move.
	 * Gives the initial and final positions.
	 * @param turtle
	 * @param initX
	 * @param initY
	 * @param endX
	 * @param endY
	 */
	public void addTurtleToMove(SingleTurtle turtle, double initX, double initY, double endX, double endY)
	{
		turtlesToMove.add(turtle);
		initialPosition.add(new ArrayList<Double>(Arrays.asList(initX, initY)));
		endPosition.add(new ArrayList<Double>(Arrays.asList(endX, endY)));		
		
		double hipothenuse = 0;
		for ( int j = 0; j < initialPosition.get(0).size(); j++ )
			hipothenuse += Math.pow(initialPosition.get(0).get(j) - endPosition.get(0).get(j),2);
		hipothenuse = Math.sqrt(hipothenuse);

		steps.add(new ArrayList<Double>( Arrays.asList(
						(endX - initX)/hipothenuse,
						(endY - initY)/hipothenuse
						)
					)
				);
		
		if ( turtlesToTurn.contains(turtle))
			moveWaitList.add(turtle);
	}
	
	/**
	 * This method is simply the same as the other one, but for turtles that have their pen down. 
	 * The line being generated by it are added in as a HasMap to the turtle.
	 * 
	 * @param turtle
	 * @param initX
	 * @param initY
	 * @param endX
	 * @param endY
	 * @param line
	 */
	public void addTurtleToMove(SingleTurtle turtle, double initX, double initY, double endX, double endY, Line line)
	{
		addTurtleToMove(turtle, initX, initY, endX, endY);
		lines.put(turtle, line);
	}
	
	/**
	 * This method adds in to the turtles that are bound to spin.
	 * 
	 * @param turtle
	 * @param initAngle
	 * @param endAngle
	 * @param isRight
	 */
	public void addTurtleToTurn(SingleTurtle turtle, double initAngle, double endAngle, boolean isRight )
	{
		turtlesToTurn.add(turtle);
		initialAngle.add(initAngle);
		this.endAngle.add(endAngle);
		if ( isRight )
			this.isRight.add(1);
		else
			this.isRight.add(-1);
		
		if ( turtlesToMove.contains(turtle))
			turnWaitList.add(turtle);
			
	}
	
}
