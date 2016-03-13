package methodInterfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import backend.CommandParser;
import frontend.EntryManager;
import frontend.StringNumEntry;

public class UserDefinedIf implements UserDefinedInterface {

	@Override
	public void executeCommand(String command, CommandParser parser, List<String> userDefinedCommands,
			EntryManager terminal, EntryManager commandManager, 
			EntryManager workspace, boolean read) {
		String[] commandPieces = command.trim().split("\\s+");
		try {
			int expr = Integer.parseInt(commandPieces[1]);
			Pattern p = Pattern.compile("\\[(.*?)\\]");
			Matcher m = p.matcher(command);
			String newCommand = "";
			if (m.find()) {
				newCommand = m.group(1);
				if ( userDefinedCommands.contains(parser.parseCommand(newCommand.trim().split("\\s+")[0])))
					newCommand = newCommand + "]";
			}
			else {
				parser.throwError("Not a Valid Command!");
				return;
			}
			if ( expr != 0 )
				parser.parse(newCommand, terminal, commandManager, workspace, false, read);
			else
				terminal.addEntry(new StringNumEntry(command,0.0), false);
		} catch (NumberFormatException e) {
			parser.throwError("Not a Valid Command!");
		}
	}

}
