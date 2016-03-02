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
	
	private UserDefinedHandler myUserDefinedHandler;
	private String myLanguage;
	private ParametersMap myParametersMap;
	private Display myDisplay;

	public CommandParser(Display display) {
		myUserDefinedHandler = new UserDefinedHandler();
		myParametersMap = new ParametersMap();
		myDisplay = display;
		myLanguage = "English";
	}
	
	public void parse(String command, EntryManager terminal, EntryManager commandManager, EntryManager workspace) {
		if (command.equals("") )
			return;
		String[] commandPieces = command.split("\\s+");
		if ( commandPieces.length == 0) {
			throwError("Not a Valid Command!");
			return;
		}
		String instruction = parseCommand(commandPieces[0]);
		if (myUserDefinedHandler.isLoopCommand(instruction)) {
			String newCommand = command.replaceFirst(commandPieces[0], instruction);
			myUserDefinedHandler.handleLoops(newCommand, this, terminal, commandManager, workspace);
		} else {
			List<ParseNode> commandTree = makeTree(commandPieces,workspace);
			if(commandTree == null)
			{
				throwError("Oh shit!");
				return;
			}
			for(ParseNode node: commandTree){
				double result = readTree(node);
				terminal.addEntry(new StringNumEntry(command,result));
			}
			
		}
	}
	
	private List<ParseNode> makeTree(String[] commands, EntryManager workspace){
		List<ParseNode> rootList = new ArrayList<ParseNode>();
		ParseNode root = new ParseNode(parseCommand(commands[0]));
		rootList.add(root);
		if(parseCommand(commands[0]).equals("")){
			return null;
		}
		List<ParseNode> instructions = new ArrayList<ParseNode>();
		instructions.add(root);
		for(int i = 1;i< commands.length; i++){
			int size = instructions.size() - 1;
			String parsedCommand = parseCommand(commands[i]);
			ParseNode currentNode = new ParseNode(parsedCommand);
			if(!parsedCommand.equals("")){
				instructions.add(currentNode);
			}
			else{
				try{
					if(commands[i].charAt(0) == ':'){
						//call the variables map
						String variable = commands[i].substring(1);
						if(workspace.getValue(variable) == null){
							workspace.addEntry(new StringNumEntry(variable,0.0));
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
			if(i > 0){
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
				if(originalParent == null){
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
		}
		return rootList;
	}
	
	private double readTree(ParseNode root){
		ParseNode current = root;
		if(root.getChildren().size() == 0){
			double[] args = new double[0];
			Commands commandMap = new Commands();
			if(!root.getName().equals("")){
				root.setValue(commandMap.callCommand(current.getName(), args, myDisplay));
			}
		}
		while(root.getChildren().size() > 0){
			dfs(root, current);
		}
		
		return root.getValue();
	}
	
	private void dfs(ParseNode root, ParseNode current){
		int count = 0;
		for(ParseNode child: current.getChildren()){
			if(child.getChildren().size() != 0){
				count++;
				dfs(root, child);
			}
		}
		if(count == 0){
			if(current.getChildren().size() == myParametersMap.getNumParams(current.getName())){
				Commands commandMap = new Commands();
				//call the correct method with current
				//make sure I have the correct # of kids
				//current == the instruction
				//its kids == the inputs
				List<ParseNode> nodeChildren = current.getChildren();
				for(ParseNode node: nodeChildren){
					if(!node.getName().equals("")){
						node.setValue(commandMap.callCommand(node.getName(), new double[0], myDisplay));
					}
				}
				double[] args = new double[nodeChildren.size()];
				int i = 0;
				for(ParseNode w: nodeChildren){
					args[i] = w.getValue();
					i++;
				}
				
				current.setValue(commandMap.callCommand(current.getName(), args, myDisplay));
				current.setName("");
				current.removeChildren();
			}
		}
	}
	
	
	public void setLanguage(String language) {
		myLanguage = language;
	}
	

	private String parseCommand(String command) {
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
	
	private void throwError(String errorMessage) {
		ErrorMessage err = new ErrorMessage(errorMessage);
		err.showError();
	}

}
