package backend;

import frontend.EntryManager;
import frontend.StringNumEntry;
import frontend.turtle.MultipleTurtles;

public class LoopCommandParser extends CommandParser{

	public LoopCommandParser(MultipleTurtles turtles) {
		super(turtles);
		// TODO Auto-generated constructor stub
	}
	
	public parse(){
		
	}
	
	private void loopHandler(String command, String instruction, EntryManager terminal, EntryManager commandManager, EntryManager workspace, boolean read, boolean add){
		Double loopVal = myUserDefinedHandler.callCommand(command, instruction, this, terminal, 
				commandManager, workspace, read, myTurtles);
		command = command.substring(command.lastIndexOf(']')+1).trim();
		if(loopVal != null && command.equals("")){
			terminal.addEntry(new StringNumEntry(originalCommand,loopVal),false);
		}
		if(!instruction.equals("MakeVariable")){
			parse(command, terminal, commandManager, workspace, false, read, add);
		}
	}

	
}
