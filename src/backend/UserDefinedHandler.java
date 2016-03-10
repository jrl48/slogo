package backend;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import frontend.Entry;
import frontend.EntryManager;
import frontend.ErrorMessage;
import frontend.StringNumEntry;
import frontend.StringStringEntry;

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
		if ( parsedInstruction.equals(myUserDefinedCommands.get(0))){
			makeVariable(command,parser,workspace,terminal);}
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
			makeUserInstruction(command, parser, terminal, commandManager, workspace);
	}

	private void makeUserInstruction(String command, CommandParser parser, 
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
		if(parser.makeTree(instructions, workspace, commandManager) == null){
			terminal.addEntry(new StringNumEntry(command,0.0), false);
		}
		else{
			terminal.addEntry(new StringNumEntry(command,1.0), false);
			commandManager.addEntry(new StringStringEntry(substring1, substring2), false);
		}
	}

	private void ifElseLoop(String command, CommandParser parser, 
			EntryManager terminal, EntryManager commandManager, EntryManager workspace) {
		String[] commandPieces = command.split("\\s+");
		try {
			int expr = Integer.parseInt(commandPieces[1]);
			Pattern p = Pattern.compile("\\[(.*?)\\]");
			Matcher m = p.matcher(command);
			String newTrueCommand = "";
			if (m.find()) {
				newTrueCommand = m.group(1);
				if ( myUserDefinedCommands.contains(parser.parseCommand(newTrueCommand.split("\\s+")[0])))
					newTrueCommand = newTrueCommand + "]";
			}
			else {
				parser.throwError("Not a Valid Command!");
				return;
			}
			String newFalseCommand = "";
			if (m.find()) {
				newFalseCommand = m.group(1);
				if ( myUserDefinedCommands.contains(parser.parseCommand(newFalseCommand.split("\\s+")[0])))
					newFalseCommand = newFalseCommand + "]";
			}
			else {
				parser.throwError("Not a Valid Command!");
				return;
			}
			if ( expr != 0 )
				parser.parse(newTrueCommand, terminal, commandManager, workspace);
			else
				parser.parse(newFalseCommand, terminal, commandManager, workspace);
		} catch (NumberFormatException e) {
			parser.throwError("Not a Valid Command!");
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
			if (m.find()) {
				newCommand = m.group(1);
				if ( myUserDefinedCommands.contains(parser.parseCommand(newCommand.split("\\s+")[0])))
					newCommand = newCommand + "]";
			}
			else {
				parser.throwError("Not a Valid Command!");
				return;
			}
			if ( expr != 0 )
				parser.parse(newCommand, terminal, commandManager, workspace);
			else

				terminal.addEntry(new StringNumEntry(command,0.0), false);
		} catch (NumberFormatException e) {
			parser.throwError("Not a Valid Command!");
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
			parser.throwError("Not a Valid Command!");
			return;
		}
		String newCommand = "";
		if (m.find()) {
			newCommand = m.group(1);
			if ( myUserDefinedCommands.contains(parser.parseCommand(newCommand.split("\\s+")[0])))
				newCommand = newCommand + "]";
		}
		else {
			parser.throwError("Not a Valid Command!");
			return;
		}
		String[] loopStuff = loopInfo.split("\\s+");
		if ( loopStuff.length != 4) {
			parser.throwError("Not a Valid Command!");
			return;
		}
		try {
			int startNum = Integer.parseInt(loopStuff[1]);
			int endNum = Integer.parseInt(loopStuff[2]);
			int increment = Integer.parseInt(loopStuff[3]);
			Entry repcount = new StringNumEntry(loopStuff[0],startNum);

			workspace.addEntry(repcount, true);
			for ( Integer i = startNum; i < endNum; i+=increment ) {
				workspace.removeEntry(repcount);
				repcount.setSecondValue((double)i);
				workspace.addEntry(repcount, true);
				String currIter = i.toString();
				parser.parse(newCommand, terminal, commandManager, workspace);
			}
		} catch (NumberFormatException e) {
			parser.throwError("Not a Valid Command!");
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
			parser.throwError("Not a Valid Command!");
			return;
		}
		String newCommand = "";
		if (m.find()) {
			newCommand = m.group(1);
			if ( myUserDefinedCommands.contains(parser.parseCommand(newCommand.split("\\s+")[0])))
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
				parser.parse(newCommand, terminal, commandManager, workspace);
			}
		} catch (NumberFormatException e) {
			parser.throwError("Not a Valid Command!");
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
			if (m.find()) {
				newCommand = m.group(1);
				if ( myUserDefinedCommands.contains(parser.parseCommand(newCommand.split("\\s+")[0])))
					newCommand = newCommand + "]";
			}
			else {
				parser.throwError("Not a Valid Command!");
				return;
			}
			Entry repcount = new StringNumEntry("repcount",0.0);

			workspace.addEntry(repcount, true);
			for ( int i = 1; i <= expr; i++) {
				workspace.removeEntry(repcount);
				repcount.setSecondValue((double)i);
				workspace.addEntry(repcount, true);
				parser.parse(newCommand, terminal, commandManager, workspace);
			}
		} catch (NumberFormatException e) {
			parser.throwError("Not a Valid Command!");
		}
	}

	private void makeVariable(String command, CommandParser parser, EntryManager workspace, EntryManager terminal) {
		System.out.println(command);
		String[] commandPieces = command.split("\\s+");
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
