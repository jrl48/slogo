package methodInterfaces;

import java.util.ArrayList;
import java.util.List;

import backend.CommandParser;
import frontend.EntryManager;
import frontend.StringNumEntry;

public class UserDefinedMakeVariable implements UserDefinedInterface {

	@Override
	public void executeCommand(String command, CommandParser parser, List<String> userDefinedCommands,
			EntryManager terminal, EntryManager commandManager, 
			EntryManager workspace, boolean read) {
		System.out.println(command);
		String[] commandPieces = command.trim().split("\\s+");
		if (commandPieces.length != 3) {
			parser.throwError("Not a Valid Command!");
		} 
		try {
			workspace.addEntry(new StringNumEntry(commandPieces[1],Double.parseDouble(commandPieces[2])), true);
			terminal.addEntry(new StringNumEntry(command,Double.parseDouble(commandPieces[2])), false);
		} catch (NumberFormatException e) {
			parser.throwError("Not a Valid Command!");
		}
	}

}
