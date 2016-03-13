package backend;

import java.util.*;
import java.util.HashMap;
import java.util.Map;

import frontend.EntryManager;
import frontend.MultipleTurtles;
import methodInterfaces.*;

public class UserDefinedCommands {

	Map<String, UserDefinedInterface> myUserDefinedCommands;
	Map<String, SpecialTurtleInterface> myTurtleInterface;

	public UserDefinedCommands() {
		myTurtleInterface = new HashMap<String, SpecialTurtleInterface>();
		myUserDefinedCommands = initializeLoops();
	}
	
	private Map<String, UserDefinedInterface> initializeLoops() {
		Map<String, UserDefinedInterface> result = new HashMap<String, UserDefinedInterface>();
		result.put("MakeVariable", new UserDefinedMakeVariable());
		result.put("Repeat", new UserDefinedRepeat());
		result.put("DoTimes", new UserDefinedDoTimes());
		result.put("For", new UserDefinedFor());
		result.put("If", new UserDefinedIf());
		result.put("IfElse", new UserDefinedIfElse());
		result.put("MakeUserInstruction", new UserDefinedMakeUserInstruction());
		myTurtleInterface.put("Tell", new TurtleTell());
		myTurtleInterface.put("Ask", new TurtleAsk());
		//myTurtleInterface.put("AskWith", new TurtleAskWith());
		return result;
	}
	
	public boolean isLoopCommand(String command) {
		return myUserDefinedCommands.containsKey(command) || myTurtleInterface.containsKey(command);
	}
	
	public Double callCommand(String command, String instruction, CommandParser parser, 
			EntryManager terminal, EntryManager commandManager, EntryManager workspace, boolean read, MultipleTurtles myTurtles){
		if ( myUserDefinedCommands.containsKey(instruction)) {
			UserDefinedInterface userDefinedCommand = myUserDefinedCommands.get(instruction);
			ArrayList<String> commands = new ArrayList<String>();
			commands.addAll(myUserDefinedCommands.keySet());
			userDefinedCommand.executeCommand(command,parser,commands,terminal,
					commandManager,workspace, read);
			return null;
		}
		else if(myTurtleInterface.containsKey(instruction)){
			SpecialTurtleInterface turtleCommand = myTurtleInterface.get(instruction);
			return turtleCommand.executeCommand(command, myTurtles, parser);

		}
		
		return null;
	}

}
