package methodInterfaces;

import java.util.ArrayList;

import backend.CommandParser;
import frontend.EntryManager;
import frontend.StringNumEntry;
import frontend.StringStringEntry;

public class UserDefinedMakeUserInstruction implements UserDefinedInterface {

	@Override
	public void executeCommand(String command, CommandParser parser, ArrayList<String> userDefinedCommands,
			EntryManager terminal, EntryManager commandManager, EntryManager workspace) {
		String substring1 = new String();
		String substring2 = new String();
		int breakpoint = command.indexOf(']');
		substring1 = command.substring(2, breakpoint + 1);
		substring2 = command.substring(breakpoint + 4, command.length() - 1);
		substring1 = substring1.trim();
		substring2 = substring2.trim();
		
		String substring1point5 = "";
		String[] first = substring2.split("\\s+");
		for(int i = 1; i < first.length; i++){
			substring1point5 = substring1point5 +" " + first[i];
		}
		System.out.println(substring1point5);
		String[] instructions = substring2.split("\\s+");
		for(int i = 0; i < instructions.length; i++){
			String s = instructions[i];
			if(parser.parseCommand(s).equals("")){
				if(substring1point5.contains(s)){
					instructions[i] = "1";
				}
			}
		}
		if(parser.makeTree(instructions, workspace) == null){
			terminal.addEntry(new StringNumEntry(command,0.0), false);
		}
		else{
			terminal.addEntry(new StringNumEntry(command,1.0), false);
			commandManager.addEntry(new StringStringEntry(substring1, substring2), false);
		}
	}

}
