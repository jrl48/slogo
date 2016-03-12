package methodInterfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import backend.CommandParser;
import frontend.Entry;
import frontend.EntryManager;
import frontend.StringNumEntry;

public class UserDefinedDoTimes implements UserDefinedInterface {

	@Override
	public void executeCommand(String command, CommandParser parser, List<String> userDefinedCommands,
			EntryManager terminal, EntryManager commandManager, 
			EntryManager workspace, EntryManager colorManager, EntryManager shapeManager, boolean read) {
		Pattern p = Pattern.compile("\\[(.*?)\\]");
		Matcher m = p.matcher(command);
		String varLimit = "";
		if (m.find())
			varLimit = m.group(1);
		else {
			parser.throwError("Not a Valid Command!");
			return;
		}
		String newCommand = "";
		if (m.find()) {
			newCommand = m.group(1);
			if ( userDefinedCommands.contains(parser.parseCommand(newCommand.split("\\s+")[0])))
				newCommand = newCommand + "]";
		}
		else {
			parser.throwError("Not a Valid Command!");
			return;
		}
		String[] variableLimit = varLimit.split("\\s+");
		if ( variableLimit.length != 2) {
			parser.throwError("Not a Valid Command!");
			return;
		}
		try {
			int varLim = Integer.parseInt(variableLimit[1]);
			Entry repcount = new StringNumEntry(variableLimit[0],0.0);
			workspace.addEntry(repcount, true);
			for ( Integer i = 1; i <= varLim; i++ ) {
				workspace.removeEntry(repcount);
				repcount.setSecondValue((double)i);
				workspace.addEntry(repcount, true);
				String currIter = i.toString();
				parser.parse(newCommand, terminal, commandManager, workspace, colorManager, shapeManager, false, read);
			}
		} catch (NumberFormatException e) {
			parser.throwError("Not a Valid Command!");
		}
	}

}
