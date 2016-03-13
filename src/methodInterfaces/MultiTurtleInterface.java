package methodInterfaces;

import frontend.EntryManager;
import frontend.MultipleTurtles;

public interface MultiTurtleInterface {
	double executeCommand(double[] args, EntryManager turtleManager, MultipleTurtles myTurtles);
}
