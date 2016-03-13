package methodInterfaces;

import backend.CommandParser;
import frontend.turtle.MultipleTurtles;

public interface SpecialTurtleInterface {
	double executeCommand(String command, MultipleTurtles myTurtles, CommandParser parser);
}
