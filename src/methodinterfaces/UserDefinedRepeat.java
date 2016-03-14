package methodinterfaces;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import backend.CommandParser;
import frontend.Entry;
import frontend.EntryManager;
import frontend.StringNumEntry;

public class UserDefinedRepeat implements UserDefinedInterface  {

	@Override
	public void executeCommand(String command, CommandParser parser, List<String> userDefinedCommands, EntryManager terminal, EntryManager commandManager,
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
			Entry repcount = new StringNumEntry("repcount",0.0);
			workspace.addEntry(repcount, true);
			for ( int i = 1; i <= expr; i++) {
				boolean add = false;
				if(i == expr){
					add = true;
				}
				workspace.removeEntry(repcount);
				repcount.setSecondValue((double)i);
				workspace.addEntry(repcount, true);
				parser.parse(newCommand, terminal, commandManager, workspace, false, read, add);
			}
		} catch (NumberFormatException e) {
			parser.throwError("Not a Valid Command!");
		}
	}

}
