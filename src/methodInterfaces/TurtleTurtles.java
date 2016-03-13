package methodInterfaces;

import frontend.EntryManager;
import frontend.turtle.MultipleTurtles;
public class TurtleTurtles implements MultiTurtleInterface {


	@Override
	public double executeCommand(double[] args, EntryManager turtleManager, MultipleTurtles myTurtles) {
		// TODO Auto-generated method stub
		return turtleManager.getEntryList().size();
	}

}
