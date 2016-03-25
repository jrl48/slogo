//This code is a part of my masterpiece
// Virginia Cheng
// It is apart of my masterpiece because it uses interfaces to allow the program to easily create different commands.

package methodinterfaces;

import frontend.EntryManager;
import frontend.turtle.MultipleTurtles;

public interface MultiTurtleInterface {
	double executeCommand(double[] args, EntryManager turtleManager, MultipleTurtles myTurtles);
}
