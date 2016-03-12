package frontend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javafx.scene.shape.Line;
import jdk.internal.org.objectweb.asm.tree.IntInsnNode;

// This class will be called to segment and control the movement of turtles.
public class AnimationController 
{
	ArrayList<SingleTurtle> turtlesToMove;
	ArrayList<ArrayList<Double>> initialPosition;	//This position is the SLogo position, not the JavaFX position
	ArrayList<ArrayList<Double>> endPosition;
	ArrayList<ArrayList<Double>> steps;
	HashMap<Turtle, Line> lines;
	
	ArrayList<SingleTurtle> turtlesToTurn;
	ArrayList<Double> initialAngle;
	ArrayList<Double> endAngle;
	ArrayList<Integer> isRight;
	
	private double turtleMoveSpeed;
	private double turtleTurnSpeed;
	
	public AnimationController()
	{
		turtlesToMove = new ArrayList<SingleTurtle>();
		initialPosition = new ArrayList<ArrayList<Double>>();
		endPosition = new ArrayList<ArrayList<Double>>();
		steps = new ArrayList<ArrayList<Double>>();
		lines = new HashMap<Turtle, Line>();
		
		turtlesToTurn = new ArrayList<SingleTurtle>();
		initialAngle = new ArrayList<Double>();
		endAngle = new ArrayList<Double>();	
		isRight = new ArrayList<Integer>();
		
		// DEFAULT
		turtleMoveSpeed = 2; 	// pixels per step proc
		turtleTurnSpeed = 5;	// degrees per step proc 
	}
	
	public void step()
	{	
		if ( turtlesToMove.isEmpty() && turtlesToTurn.isEmpty() )
			return;

		ArrayList<Double> currentStep = new ArrayList<Double>();
		boolean ended;
		
		for ( int i = 0; i < turtlesToMove.size(); i++ )
		{
			SingleTurtle currentTurtle = turtlesToMove.get(i);
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
				currentTurtle.setTurtleCoordinates(endPosition.get(i));
				turtlesToMove.remove(i);
				initialPosition.remove(i);
				endPosition.remove(i);
				steps.remove(i);
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
		
		
		for ( int i = 0; i < turtlesToTurn.size(); i++ )
		{	
			Turtle currentTurtle = turtlesToTurn.get(i);
			double currentAngle = currentTurtle.getTurtleAngle() + turtleTurnSpeed * isRight.get(i);

			// Ended the turning!
			if ( Math.abs(currentAngle - endAngle.get(i)) < turtleTurnSpeed + 1 )
			{
				currentTurtle.setTurtleAngle(endAngle.get(i));
				turtlesToTurn.remove(i);
				initialAngle.remove(i);
				endAngle.remove(i);
				isRight.remove(i);
			}
			else
				currentTurtle.setTurtleAngle(currentAngle);
		}
	}
	
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
	}
	
	public void addTurtleToMove(SingleTurtle turtle, double initX, double initY, double endX, double endY, Line line)
	{
		addTurtleToMove(turtle, initX, initY, endX, endY);
		lines.put(turtle, line);
	}
	
	public void addTurtleToTurn(SingleTurtle turtle, double initAngle, double endAngle, boolean isRight )
	{
		turtlesToTurn.add(turtle);
		initialAngle.add(initAngle);
		this.endAngle.add(endAngle);
		if ( isRight )
			this.isRight.add(1);
		else
			this.isRight.add(-1);
	}
	
	
	
}
