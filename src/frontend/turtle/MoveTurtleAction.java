// This entire file is part of my masterpiece.
// Alan Cavalcanti

package frontend.turtle;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.shape.Line;

public class MoveTurtleAction extends TurtleAction 
{
	private ArrayList<Double> startPosition;
	private ArrayList<Double> endPosition;
	private ArrayList<Double> currentPosition;
	private ArrayList<Double> steps;
	private ArrayList<Double> currentStep;
	private Line line = null;
	
	public MoveTurtleAction(SingleTurtle turtle, List<Double> startPosition, List<Double> endPosition) 
	{
		super(turtle);
		this.startPosition = (ArrayList<Double>) startPosition;
		this.endPosition = (ArrayList<Double>) endPosition;
		currentPosition = new ArrayList<>();
		
		currentPosition.addAll(startPosition);
		currentStep = new ArrayList<>();
		steps = new ArrayList<>();
		
		double hipothenuse = 0;
		
		for ( int j = 0; j < startPosition.size(); j++ )
		{
			hipothenuse += Math.pow(startPosition.get(j) - endPosition.get(j),2);
		}
		
		hipothenuse = Math.sqrt(hipothenuse);
		
		for ( int i = 0; i < startPosition.size(); i++ )
		{
			steps.add( ( endPosition.get(i) - startPosition.get(i) ) / hipothenuse) ;
		}
		
	}
	
	public void setLine(Line line)
	{
		this.line = line;
	}

	public boolean takeAction(double speed) 
	{
		boolean ended = false;
		currentStep.clear();
		
		for ( int i = 0; i < startPosition.size(); i++ )
		{
			currentStep.add(currentPosition.get(i) + steps.get(i) * speed);

			// Sees if the animation would make a bigger step than needed
			if ( startPosition.get(i) < endPosition.get(i) )
			{
				ended = ( currentStep.get(i) > endPosition.get(i) );
			}

			else
			{
				ended = ( currentStep.get(i) < endPosition.get(i) );
			}
			
			if ( ended )
			{
				break;
			}
		}
		
		
		
		
		if ( ended )
		{
			// Simply make the new position the position it needs to be
			getTurtle().updateTurtleVisualPosition(endPosition);
			
			return true;
		}
		else
		{		
			currentPosition.clear();
			currentPosition.addAll(currentStep);
			getTurtle().updateTurtleVisualPosition(currentStep);
			if ( line != null )
			{
				getTurtle().updateLine(line, startPosition, currentStep);
			}
			
			
			return false;
		}
	}
}
