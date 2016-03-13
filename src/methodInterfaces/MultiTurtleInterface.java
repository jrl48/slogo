package methodInterfaces;

import frontend.EntryManager;
import frontend.turtle.MultipleTurtles;

public interface MultiTurtleInterface {
	double executeCommand(double[] args, EntryManager turtleManager, MultipleTurtles myTurtles);
}
