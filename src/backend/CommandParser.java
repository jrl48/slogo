package backend;


import java.util.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import frontend.*;

public class CommandParser {
	
	private UserDefinedCommands myUserDefinedHandler;
	private Display myDisplay;
	private String myLanguage;
	private ParametersMap myParametersMap;
	private String originalCommand;
	private MultipleTurtles myTurtles;

	public CommandParser(MultipleTurtles turtles, Display display) {
		myUserDefinedHandler = new UserDefinedCommands();
		myParametersMap = new ParametersMap();
		myTurtles = turtles;
		myLanguage = "English";
		myDisplay = display;
	}
	
	public Object parse(String command, EntryManager terminal, EntryManager commandManager, EntryManager workspace, boolean updateString, boolean read) {
		command = command.trim();
		if(parsingHouseKeeping(updateString, command, terminal, workspace, commandManager) == null){
			throwError("Not a Valid Command!");
		}
		
		String instruction = parseCommand(command.split("\\s+")[0]);
		if (myUserDefinedHandler.isLoopCommand(instruction)) {
			myUserDefinedHandler.callCommand(command, instruction, this, terminal, 
					commandManager, workspace, read);
			return 0;
		} 
		else {
			String newCommand = methodLoop(command, commandManager);
			if(newCommand == null){
				return null;
			}
			if(!command.equals(newCommand)){
				parse(newCommand, terminal, commandManager, workspace, false, read);
			}
			else{
			String[] commandPieces = newCommand.split("\\s");
			List<ParseNode> commandTree = makeTree(commandPieces,workspace, commandManager);
			if(commandTree == null){
				throwError("Not a Valid Command!");
				return null;
			}
			if(read){
				treeReader(commandTree, terminal);
			}}
		}	
		return 0;	
	}
	
	private String parsingHouseKeeping(boolean updateString, String command, EntryManager terminal, EntryManager workspace, EntryManager commandManager){
		if(updateString){
			originalCommand = command;
		}
		if (command.equals("")){
			return "";
		}
		String[] commandPieces = command.split("\\s+");
		if ( commandPieces.length == 0) {
			return null;
		}
		if ( commandPieces.length >= 2) {
			Pattern p = Pattern.compile("\\((.*?)\\)");
			Matcher m = p.matcher(command);
			if(m.find()) {
				handleGrouping(command,terminal,commandManager,workspace,myTurtles);
				return ""; //check this
			}
		}
		
		return "";
	}
	
	private void treeReader(List<ParseNode> commandTree, EntryManager terminal){
		double result = 0.0;
		for(ParseNode node: commandTree){
			result = readTree(node, myTurtles);
		}
		terminal.addEntry(new StringNumEntry(originalCommand,result),false);
	}
	
	private void handleGrouping(String command, EntryManager terminal, EntryManager commandManager,
			EntryManager workspace, MultipleTurtles myTurtles) {
		Pattern p = Pattern.compile("\\((.*?)\\)");
		Matcher m = p.matcher(command);
		String commandInParen = "";
		if (m.find())
			commandInParen = m.group(1);
		else {
			throwError("Not a valid Command!");
			return;
		}
		String[] commandPieces = commandInParen.trim().split("\\s+");
		String commandPiece = parseCommand(commandPieces[0]);
		if (myParametersMap.getNumParams(commandPiece) < 2 || commandPieces.length < 3) {
			throwError("Command cannot be grouped!");
			return;
		}
		String newCommand = "";
		for ( int i = 0; i < commandPieces.length-2; i++ ) {
			newCommand = newCommand + commandPieces[0] + " ";
		}
		for ( int i = 1; i < commandPieces.length; i++ ) {
			newCommand = newCommand + commandPieces[i] + " ";
		}
		String[] originalCommandPieces = command.split("\\s+");
		originalCommandPieces[0] = originalCommandPieces[0].replaceAll("\\(","");
		if (originalCommandPieces[0] != "\\(") {
			newCommand = originalCommandPieces[0] + " " + newCommand;
		}
		parse(newCommand,terminal,commandManager,workspace, false, true);
		
	}
	
	private int getEndBracket(String command, int startBracket){
		int endBracket = command.lastIndexOf(']');
		int counter = 0;
		for(int i = startBracket+ 1; i < command.length(); i++){
			if(command.charAt(i) == ']'){
				if(counter == 0){
					return i;
				}
				else{
					counter--;
				}
			}
			if(command.charAt(i) == '['){
				counter++;
			}
		}
		
		return endBracket;
	}
	
	public String methodLoop(String command, EntryManager commandManager){
		boolean hasMethods = true;
		while(hasMethods){
			hasMethods = false;
			String [] commandPieces = command.split("\\s+");
			for(String s: commandPieces){
				if(commandManager.contains(s) != null){
					hasMethods = true;
					int loc = command.indexOf(s);
					String str2 = methodDealer(commandManager, command.substring(loc));
					if(str2 == null){
						return null;
					}
					command = command.substring(0, loc) + str2;

				}
			}
		}
		
		return command;
	}
	
	private String methodDealer(EntryManager commandManager, String command){
		String originalParameters = (String)commandManager.contains(command.split("\\s+")[0]);
		int bracket = originalParameters.indexOf('[');
		String parameters = originalParameters.substring(bracket+1, originalParameters.length() - 1).trim();
		Map<String, String> paramToNum = new HashMap<String, String>();
		bracket = command.indexOf('[');
		int endBracket = getEndBracket(command, bracket);
		String insideCommand = command.substring(bracket+1, endBracket).trim();
		
		String[] paramArray = parameters.split("\\s+");
		String[] commandArray;

		if(insideCommand.contains("[") && insideCommand.contains("]")){
			commandArray = stringReplacement(paramArray, insideCommand, commandManager);
		}
		else{
			commandArray = insideCommand.split("\\s+");
			if(commandArray.length != paramArray.length){
				throwError("Incorrect Number of Parameters");
				return null;
			}
		}

		for(int x = 0; x < paramArray.length; x++){
			paramToNum.put(paramArray[x], commandArray[x]);
		}
		
		String actualCommands = (String) commandManager.getValue(originalParameters) +  command.substring(endBracket+1);
		String[] commandPieces = actualCommands.split("\\s+");
		String result = "";
		for(int y = 0; y < commandPieces.length; y++){
			if(paramToNum.containsKey(commandPieces[y])){
				commandPieces[y] = paramToNum.get(commandPieces[y]);
			}
			result = result + " " + commandPieces[y];
		}
		return result.trim();
	}
	
	private String[] stringReplacement(String[] paramArray, String tempString, EntryManager commandManager){
		int arrayCount = 0;
		int breakpoint = 0;
		String[] commandArray = new String[paramArray.length];
		while(arrayCount < commandArray.length){
			tempString = tempString.substring(breakpoint).trim();
			breakpoint = 0;
			String[] tempArray = tempString.split("\\s+");
			if(commandManager.contains(tempArray[0]) == null){
				commandArray[arrayCount] = tempArray[0];
				breakpoint = tempString.indexOf(" ");
			}
			else{
				int tempStartBracket = tempString.indexOf('[');
				int tempEndBracket = getEndBracket(tempString, tempStartBracket);
				commandArray[arrayCount] = tempString.substring(0, tempEndBracket+1);
				breakpoint = tempEndBracket+1;		
			}
			arrayCount++;
		}

		if(tempString.contains("[") && tempString.contains("]")){
			tempString = tempString.substring(0, tempString.indexOf("[")) + tempString.substring(tempString.indexOf(']') +1 );
		}
		if(tempString.trim().contains(" ")){
			throwError("Incorrect Number of Parameters");
		}
		return commandArray;
	}
	
	private List<ParseNode> makeTree(String[] commands, EntryManager workspace, EntryManager commandManager){
		List<ParseNode> rootList = new ArrayList<ParseNode>();
		ParseNode root = new ParseNode(parseCommand(commands[0]));
		rootList.add(root);
		if(parseCommand(commands[0]).equals("")){
			return null;
		}
		System.out.println(parseCommand(commands[0]));
		List<ParseNode> instructions = new ArrayList<ParseNode>();
		instructions.add(root);
		for(int i = 1;i< commands.length; i++){
			int size = instructions.size() - 1;
			String parsedCommand = parseCommand(commands[i]);   
			ParseNode currentNode = new ParseNode(parsedCommand);
			instructions = createNode(commands, i ,parsedCommand, instructions, currentNode, workspace);
			if(instructions == null){
				return null;
			}
			
			ParseNode parent = parentNodeLoop(size, instructions);
			if(parent == null){
				if(currentNode.getName().equals("")){
					return null;
				}
				rootList.add(currentNode);
				instructions.clear();
				instructions.add(currentNode);
				root = currentNode;
			}
			else{
				parent.addChild(currentNode);
			}
		}
		return rootList;
	}
	
	private ParseNode parentNodeLoop(int size, List<ParseNode> instructions ){
		ParseNode originalParent = null;
		ParseNode parent = instructions.get(size);
		for(int j = size; j >= 0; j--){
			parent = instructions.get(j);
			int numParams = myParametersMap.getNumParams(parent.getName());
			if(numParams == -1){
				return null;
			}
			if(parent.getChildren().size() < numParams){
				originalParent = parent;
				break;
			}
		}
		return originalParent;
	}
	
	private List<ParseNode> createNode(String[] commands, int i, String parsedCommand, List<ParseNode> instructions, ParseNode currentNode, EntryManager workspace){
		if(!parsedCommand.equals("")){
			instructions.add(currentNode);
		}
		else{
			try{
				if(commands[i].charAt(0) == ':'){
					String variable = commands[i].substring(1);
					if(workspace.getValue(variable) == null){
						workspace.addEntry(new StringNumEntry(variable,0.0),true);
					}
					else{
						currentNode.setValue((double) workspace.getValue(variable));
					}
				}
				else{
					currentNode.setValue(Double.parseDouble(commands[i]));
				}
			}
			catch(NumberFormatException exception){
				return null;
			}
		}
		
		return instructions;
	}
	
	private double readTree(ParseNode root, MultipleTurtles MyTurtles){
		ParseNode current = root;
		if(root.getChildren().size() == 0){
			double[] args = new double[0];
			Commands commandMap = new Commands();
			if(!root.getName().equals("")){
				root.setValue(commandMap.callCommand(current.getName(), args, MyTurtles));
			}
		}
		while(root.getChildren().size() > 0){
			dfs(root, current, MyTurtles);
		}
		
		return root.getValue();
	}
	
	private void dfs(ParseNode root, ParseNode current, MultipleTurtles myTurtles){
		int count = 0;
		for(ParseNode child: current.getChildren()){
			if(child.getChildren().size() != 0){
				count++;
				dfs(root, child, myTurtles);
			}
		}
		if(count == 0){
			if(current.getChildren().size() == myParametersMap.getNumParams(current.getName())){
				Commands commandMap = new Commands();
				List<ParseNode> nodeChildren = current.getChildren();
				for(ParseNode node: nodeChildren){
					if(!node.getName().equals("")){
						node.setValue(commandMap.callCommand(node.getName(), new double[0], myTurtles));
					}
				}
				double[] args = new double[nodeChildren.size()];
				int i = 0;
				for(ParseNode w: nodeChildren){
					args[i] = w.getValue();
					i++;
				}
				
				current.setValue(commandMap.callCommand(current.getName(), args, myTurtles));
				current.setName("");
				current.removeChildren();
			}
		}
	}
	
	
	public void setLanguage(String language) {
		myLanguage = language;
	}
	

	public String parseCommand(String command) {
		try {
			FileInputStream fileInput = new FileInputStream(
					new File("bin/resources/languages/" + myLanguage + ".properties"));
			Properties properties = new Properties();
			properties.load(fileInput);
			fileInput.close();
			String desiredCommand = getDesiredCommand(properties,properties.keys(),command);
			return desiredCommand;
		} catch (FileNotFoundException e) {
			throwError("Language entered is invalid!");
		} catch (IOException e) {
			throwError("Invalid input!");
		}
		return "";
	}
	
	private String getDesiredCommand(Properties properties, Enumeration commands, String command) {
		while ( commands.hasMoreElements() ) {
			String key = (String) commands.nextElement();
			String[] values = properties.getProperty(key).split("\\|");
			for ( String value: values) {
				String realValue = value;
				if(value.startsWith("\\")){
					realValue = value.substring(1);
				}
				if(value.endsWith("\\?")){
					realValue = value.substring(0, value.length() -2) + "?";
				}
				if (realValue.equals(command)) {
					return key;
				}
			}
		}
		return "";
	}
	
	public void throwError(String errorMessage) {
		ErrorMessage err = new ErrorMessage(errorMessage);
		err.showError();
	}

}
