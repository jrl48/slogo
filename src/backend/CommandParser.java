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

import com.sun.xml.internal.ws.util.StringUtils;

import frontend.*;



public class CommandParser {
	
	//private final static CommandParser myParser = new CommandParser();
	private String myCommand;
	private String myLanguage;
	private Parameters myParameters;
	private ParametersMap myParametersMap;
//	private Map<String, Integer> commandInputs;
	private Display myDisplay;

	public CommandParser(Display display) {
		myParametersMap = new ParametersMap();
		myDisplay = display;
		myLanguage = "English";
	}
	
	public void parse(String command, EntryManager terminal, EntryManager commandManager, EntryManager workspace) {
		System.out.println(command);
		// TODO put it all together
		if (command.equals("") )
			return;
		String[] commandPieces = command.split(" ");
		ParseNode commandTree = makeTree(commandPieces);
		if(commandTree == null){
			ErrorMessage err = new ErrorMessage("Not a Valid Command");
			err.showError();
			return;
		}
		double result = readTree(commandTree);
		terminal.addEntry(new StringNumEntry(command,result));
		
	}
	
	public ParseNode makeTree(String[] commands){
		ParseNode root = new ParseNode(parseCommand(commands[0]));
		List<ParseNode> instructions = new ArrayList<ParseNode>();
		instructions.add(root);
		for(int i = 1;i< commands.length; i++){
			int size = instructions.size() - 1;
			String parsedCommand = parseCommand(commands[i]);
			ParseNode currentNode = new ParseNode(parsedCommand);
			if(!parsedCommand.equals("")){
				//currentNode.setName(parseCommand(commands[i]));
				instructions.add(currentNode);
			}
			else{
				try{
					if(commands[i].charAt(0) == ':'){
						//call the variables map
						
					}
					currentNode.setValue(Integer.parseInt(commands[i]));
				}
				catch(NumberFormatException exception){
					return null;
				}
			}
			
//			if(i == 0){
//				root = currentNode;
//			}
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
					return null;
				}
				else{
					parent.addChild(currentNode);
				}
			}
		}
		return root;
	}
	
	private double readTree(ParseNode root){
		ParseNode current = root;
		if(root.getChildren().size() == 0){
			double[] args = new double[0];
			MathCommands mathmathmath = new MathCommands();
			root.setValue(mathmathmath.callCommand(current.getName(), args));
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
				MathCommands mathmathmath = new MathCommands();
				//call the correct method with current
				//make sure I have the correct # of kids
				//current == the instruction
				//its kids == the inputs
				//int value;
				//current.setValue(value);
				List<ParseNode> womp = current.getChildren();
				for(ParseNode node: womp){
					if(!node.getName().equals("")){
						node.setValue(mathmathmath.callCommand(node.getName(), new double[0]));
					}
				}
				double[] args = new double[womp.size()];
				int i = 0;
				for(ParseNode w: womp){
					args[i] = w.getValue();
					i++;
				}
				
				current.setValue(mathmathmath.callCommand(current.getName(), args));
				current.setName("");
				current.removeChildren();
				
				
				
				
//				if(current.getName().equals("SUM") || current.getName().equals("SUB")){
//					List<ParseNode> womp = current.getChildren();
//					int one = womp.get(0).getValue();
//					int two = womp.get(1).getValue();
//					if(current.getName().equals("SUB")){
//						two = -1*two;
//					}
//					int value = sum(one,two);
//					current.setValue(value);
//					current.removeChildren();	
//				}
			}
		}
	}
	
	private static int sum(int x, int y){
		return x + y;
	}
	
	private void inputCommand(String command) {
		myCommand = command;
	}
	
	public void setLanguage(String language) {
		myLanguage = language;
	}
	

	private String parseCommand(String command) {
		try {
			FileInputStream fileInput = new FileInputStream(new File("bin/resources/languages/" + myLanguage + ".properties"));
			Properties properties = new Properties();
			properties.load(fileInput);
			fileInput.close();
			Enumeration commands = properties.keys();
			String desiredCommand = getDesiredCommand(properties,commands,command);
			if (desiredCommand.equals("")){
				//ErrorMessage err = new ErrorMessage("Not a Valid Command");
				//err.showError();
			}
			return desiredCommand;
		} catch (FileNotFoundException e) {
			throwError(e);
		} catch (IOException e) {
			throwError(e);
		}
		return "";
	}
	
	private String getDesiredCommand(Properties properties, Enumeration commands, String command) {
		while ( commands.hasMoreElements() ) {
			String key = (String) commands.nextElement();
			String[] values = properties.getProperty(key).split("\\|");
			for ( String value: values) {
				if (value.equals(command)) {
					return key;
				}
			}
		}
		return "";
	}
	
	public Parameters getParameters() {
		return myParameters;
	}
	
	private void throwError(Exception e) {
		// CHANGE THIS LATER!!!!!!!!!!!!!!!!!!
		e.printStackTrace();
	}
	
    /*public static void main(String[] args){
        String command = "SUM 1 SUM 1 1";
        String[] commands = command.split(" ");
        for(String s: commands){
        	System.out.println(s);
        }
        ParseNode root = makeTree(commands);
        double x = readTree(root);
        System.out.println(x);
        
    }*/

}
