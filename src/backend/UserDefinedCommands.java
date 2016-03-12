package backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import frontend.EntryManager;
import methodInterfaces.*;

public class UserDefinedCommands {

	Map<String, UserDefinedInterface> myUserDefinedCommands;

	public UserDefinedCommands() {
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
		return result;
	}
	
	public boolean isLoopCommand(String command) {
		return myUserDefinedCommands.containsKey(command);
	}
	
	public void callCommand(String command, String instruction, CommandParser parser, 
			EntryManager terminal, EntryManager commandManager, EntryManager workspace, 
			EntryManager colorManager, EntryManager shapeManager){
		if ( myUserDefinedCommands.containsKey(instruction)) {
			UserDefinedInterface userDefinedCommand = myUserDefinedCommands.get(instruction);
			ArrayList<String> commands = new ArrayList<String>();
			commands.addAll(myUserDefinedCommands.keySet());
			userDefinedCommand.executeCommand(command,parser,commands,terminal,
					commandManager,workspace, colorManager, shapeManager);
		}
	}

}
