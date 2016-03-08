package methodInterfaces;

import java.util.ArrayList;

import backend.CommandParser;
import frontend.EntryManager;

public interface UserDefinedInterface {
	public void executeCommand(String command, CommandParser parser, ArrayList<String> userDefinedCommands,
			EntryManager terminal, EntryManager commandManager, EntryManager workspace);
}
