package methodinterfaces;

import frontend.Entry;
import frontend.EntryManager;
import frontend.turtle.MultipleTurtles;
import frontend.turtle.SingleTurtle;

public class TurtleID implements MultiTurtleInterface{



	@Override
	public double executeCommand(double[] args, EntryManager turtleManager, MultipleTurtles myTurtles) {
		double value = 0;
		int counter = 0;
		for (Entry t : turtleManager.getEntryList()) {
			System.out.println((String)t.getFirstValue());
			counter++;
			SingleTurtle turtle = (SingleTurtle) t.getSecondValue();
			if (turtle.isActive()) {
				value = counter;
			}
		}

		return value;
	}
	

}
