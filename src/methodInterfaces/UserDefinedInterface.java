package methodInterfaces;

import java.util.ArrayList;
import java.util.List;

import backend.CommandParser;
import frontend.EntryManager;

public interface UserDefinedInterface {
	public void executeCommand(String command, CommandParser parser, List<String> userDefinedCommands,
			EntryManager terminal, EntryManager commandManager, EntryManager workspace, 
			EntryManager colorManager, EntryManager shapeManager);
}
