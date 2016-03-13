package methodInterfaces;

import backend.CommandParser;
import frontend.EntryManager;
import frontend.MultipleTurtles;

public interface SpecialTurtleInterface {
	double executeCommand(String command, MultipleTurtles myTurtles, CommandParser parser);
}
