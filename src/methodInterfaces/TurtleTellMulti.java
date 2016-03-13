package methodInterfaces;

import java.util.HashSet;
import java.util.Set;

import frontend.Entry;
import frontend.EntryManager;
import frontend.turtle.MultipleTurtles;
import frontend.turtle.SingleTurtle;
public class TurtleTellMulti implements MultiTurtleInterface{

	@Override
	public double executeCommand(double[] args, EntryManager turtleManager, MultipleTurtles myTurtles) {
		Set<Double> currentTurtles = new HashSet<Double>();
		for (Entry t : turtleManager.getEntryList()) {
			SingleTurtle turtle = (SingleTurtle) t.getSecondValue();
			double turtleID = Double.parseDouble(((String) t.getFirstValue()).split(" ")[1]);
			for(double x: args){
				if(x == turtleID){
					turtle.setActive(true);
					currentTurtles.add(x);
					break;
				}
				else{
					turtle.setActive(false);
				}
				
			}
		}
		
		for(double x: args){
			if(!currentTurtles.contains(x)){
				myTurtles.addTurtle((int)x);
			}
		}
		
		return args[args.length -1 ];
	}
	

}
