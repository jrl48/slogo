package backend;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import frontend.Entry;
import frontend.EntryManager;
import frontend.ErrorMessage;
import frontend.StringNumEntry;

public class UserDefinedHandler {
	
	List<String> myUserDefinedCommands;

	public UserDefinedHandler() {
		myUserDefinedCommands = initializeLoops();
	}
	
	private List<String> initializeLoops() {
		List<String> result = new ArrayList<String>();
		result.add("MakeVariable");
		result.add("Repeat");
		result.add("DoTimes");
		result.add("For");
		result.add("If");
		result.add("IfElse");
		result.add("MakeUserInstruction");
		return result;
	}
	
	public boolean isLoopCommand(String command) {
		return myUserDefinedCommands.contains(command);
	}
	
	public void handleLoops(String command, String parsedInstruction, CommandParser parser, 
			EntryManager terminal, EntryManager commandManager, EntryManager workspace) {
		String[] commandPieces = command.split("\\s+");
		if ( parsedInstruction.equals(myUserDefinedCommands.get(0))){
			makeVariable(command,workspace,terminal);}
		else if ( parsedInstruction.equals(myUserDefinedCommands.get(1)))
			repeatLoop(command,parser,terminal,commandManager,workspace);
		else if ( parsedInstruction.equals(myUserDefinedCommands.get(2)))
			doTimesLoop(command,parser,terminal,commandManager,workspace);
		else if ( parsedInstruction.equals(myUserDefinedCommands.get(3)))
			forLoop(command,parser,terminal,commandManager,workspace);
		else if ( parsedInstruction.equals(myUserDefinedCommands.get(4)))
			ifLoop(command,parser,terminal,commandManager,workspace);
		else if ( parsedInstruction.equals(myUserDefinedCommands.get(5)))
			ifElseLoop(command,parser,terminal,commandManager,workspace);
		else if ( parsedInstruction.equals(myUserDefinedCommands.get(6)))
			makeUserInstruction();
	}

	private void makeUserInstruction() {
		throwError("Not Implemented Yet!");
	}

	private void ifElseLoop(String command, CommandParser parser, 
			EntryManager terminal, EntryManager commandManager, EntryManager workspace) {
		String[] commandPieces = command.split("\\s+");
		try {
			int expr = Integer.parseInt(commandPieces[1]);
			Pattern p = Pattern.compile("\\[(.*?)\\]");
			Matcher m = p.matcher(command);
			String newTrueCommand = "";
			if (m.find())
				newTrueCommand = m.group(1);
			else {
				throwError("Not a Valid Command!");
				return;
			}
			String newFalseCommand = "";
			if (m.find())
				newFalseCommand = m.group(1);
			else {
				throwError("Not a Valid Command!");
				return;
			}
			if ( expr != 0 )
				parser.parse(newTrueCommand, terminal, commandManager, workspace);
			else
				parser.parse(newFalseCommand, terminal, commandManager, workspace);
		} catch (NumberFormatException e) {
			throwError("Not a Valid Command!");
		}
	}

	private void ifLoop(String command, CommandParser parser, 
			EntryManager terminal, EntryManager commandManager, EntryManager workspace) {
		String[] commandPieces = command.split("\\s+");
		try {
			int expr = Integer.parseInt(commandPieces[1]);
			Pattern p = Pattern.compile("\\[(.*?)\\]");
			Matcher m = p.matcher(command);
			String newCommand = "";
			if (m.find())
				newCommand = m.group(1);
			else {
				throwError("Not a Valid Command!");
				return;
			}
			if ( expr != 0 )
				parser.parse(newCommand, terminal, commandManager, workspace);
			else
				terminal.addEntry(new StringNumEntry(command,0.0),false);
		} catch (NumberFormatException e) {
			throwError("Not a Valid Command!");
		}
	}

	private void forLoop(String command, CommandParser parser, 
			EntryManager terminal, EntryManager commandManager, EntryManager workspace) {
		Pattern p = Pattern.compile("\\[(.*?)\\]");
		Matcher m = p.matcher(command);
		String loopInfo = "";
		if (m.find())
			loopInfo = m.group(1);
		else {
			throwError("Not a Valid Command!");
			return;
		}
		String newCommand = "";
		if (m.find())
			newCommand = m.group(1);
		else {
			throwError("Not a Valid Command!");
			return;
		}
		String[] loopStuff = loopInfo.split("\\s+");
		if ( loopStuff.length != 4) {
			throwError("Not a Valid Command!");
			return;
		}
		try {
			int startNum = Integer.parseInt(loopStuff[1]);
			int endNum = Integer.parseInt(loopStuff[2]);
			int increment = Integer.parseInt(loopStuff[3]);
			Entry repcount = new StringNumEntry(loopStuff[0],startNum);
			workspace.addEntry(repcount,true);
			for ( Integer i = startNum; i < endNum; i+=increment ) {
				workspace.removeEntry(repcount);
				repcount.setSecondValue((double)i);
				workspace.addEntry(repcount,true);
				String currIter = i.toString();
				parser.parse(newCommand, terminal, commandManager, workspace);
			}
		} catch (NumberFormatException e) {
			throwError("Not a Valid Command!");
		}
	}

	private void doTimesLoop(String command, CommandParser parser, 
			EntryManager terminal, EntryManager commandManager, EntryManager workspace) {
		Pattern p = Pattern.compile("\\[(.*?)\\]");
		Matcher m = p.matcher(command);
		String varLimit = "";
		if (m.find())
			varLimit = m.group(1);
		else {
			throwError("Not a Valid Command!");
			return;
		}
		String newCommand = "";
		if (m.find())
			newCommand = m.group(1);
		else {
			throwError("Not a Valid Command!");
			return;
		}
		String[] variableLimit = varLimit.split("\\s+");
		if ( variableLimit.length != 2) {
			throwError("Not a Valid Command!");
			return;
		}
		try {
			int varLim = Integer.parseInt(variableLimit[1]);
			Entry repcount = new StringNumEntry(variableLimit[0],0.0);
			workspace.addEntry(repcount,true);
			for ( Integer i = 1; i <= varLim; i++ ) {
				workspace.removeEntry(repcount);
				repcount.setSecondValue((double)i);
				workspace.addEntry(repcount,true);
				String currIter = i.toString();
				parser.parse(newCommand, terminal, commandManager, workspace);
			}
		} catch (NumberFormatException e) {
			throwError("Not a Valid Command!");
		}
	}

	private void repeatLoop(String command, CommandParser parser, 
			EntryManager terminal, EntryManager commandManager, EntryManager workspace) {
		String[] commandPieces = command.split("\\s+");
		try {
			int expr = Integer.parseInt(commandPieces[1]);
			Pattern p = Pattern.compile("\\[(.*?)\\]");
			Matcher m = p.matcher(command);
			String newCommand = "";
			if (m.find())
				newCommand = m.group(1);
			else {
				throwError("Not a Valid Command!");
				return;
			}
			Entry repcount = new StringNumEntry("repcount",0.0);
			workspace.addEntry(repcount,true);
			for ( int i = 1; i <= expr; i++) {
				workspace.removeEntry(repcount);
				repcount.setSecondValue((double)i);
				workspace.addEntry(repcount,true);
				parser.parse(newCommand, terminal, commandManager, workspace);
			}
		} catch (NumberFormatException e) {
			throwError("Not a Valid Command!");
		}
	}

	private void makeVariable(String command, EntryManager workspace, EntryManager terminal) {
		System.out.println(command);
		String[] commandPieces = command.split("\\s+");
		if (commandPieces.length != 3) {
			throwError("Not a Valid Command!");
		} 
		try {
			workspace.addEntry(new StringNumEntry(commandPieces[1],Double.parseDouble(commandPieces[2])),true);
			terminal.addEntry(new StringNumEntry(command,Double.parseDouble(commandPieces[2])),false);
		} catch (NumberFormatException e) {
			throwError("Not a Valid Command!");
		}
	}
	
	private void throwError(String errorMessage) {
		ErrorMessage err = new ErrorMessage(errorMessage);
		err.showError();
	}

}
