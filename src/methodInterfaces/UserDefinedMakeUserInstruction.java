package methodinterfaces;

import java.util.List;

import backend.CommandParser;
import frontend.EntryManager;
import frontend.StringNumEntry;
import frontend.StringStringEntry;

public class UserDefinedMakeUserInstruction implements UserDefinedInterface {

	@Override
	public void executeCommand(String command, CommandParser parser, List<String> userDefinedCommands,
			EntryManager terminal, EntryManager commandManager, EntryManager workspace, boolean read) {
		String substring1 = new String();
		String substring2 = new String();
		int breakpoint = command.indexOf(']');
		substring1 = command.substring(2, breakpoint + 1);
		System.out.println("SUB1" + substring1);
		substring2 = command.substring(breakpoint + 2 + 2, command.length() - 1);
		if(substring2.equals("")){
			terminal.addEntry(new StringNumEntry(command,1.0), false);
			commandManager.addEntry(new StringStringEntry(substring1, " "), true);
			return;
		}
		System.out.println("SUB2" + substring2);
		substring1 = substring1.trim();
		substring2 = substring2.trim();
		commandManager.addEntry(new StringStringEntry(substring1, substring2), true);
		
		String parameters = substring1.substring(substring1.indexOf('[') + 1, substring1.indexOf(']')).trim();
		System.out.println("SUB1.5" + parameters);
		String finalString = parser.methodLoop(substring2, commandManager);
		String[] instructions = finalString.split("\\s+");
		for(int i = 0; i < instructions.length; i++){
			String s = instructions[i];
			if(parser.parseCommand(s).equals("")){
				if(s.equals(substring1.split("\\s+")[0])){
					
				}
				if(parameters.contains(s)){
					instructions[i] = "0";
				}
				
			}
		}
	
		String tempCommand = "";
		for(String x: instructions){
			tempCommand = tempCommand + " " + x;
		}
//		if(parser.makeTree(instructions, workspace, commandManager) == null){
//			terminal.addEntry(new StringNumEntry(command,0.0), false);
//		}
		if(parser.parse(tempCommand.trim(), terminal, commandManager, workspace, false, false) == null){
			terminal.addEntry(new StringNumEntry(command,0.0), false);
			//commandManager.removeEntry(); //figure out how to remove the entry
		}
		else{
			terminal.addEntry(new StringNumEntry(command,1.0), false);
			commandManager.addEntry(new StringStringEntry(substring1, substring2), true);
		}
	}

}
