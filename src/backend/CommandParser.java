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
import frontend.*;



public class CommandParser {
	
	//private final static CommandParser myParser = new CommandParser();
	private String myCommand;
	private String myLanguage;
	private Parameters myParameters;
	private Map<String, Integer> commandInputs;
	private Display myDisplay;

	public CommandParser(Display display) {
		commandInputs = new HashMap<String, Integer>();
		myDisplay = display;
		myLanguage = "English";
	}
	
	public void parse(String command, EntryManager terminal, EntryManager commandManager, EntryManager workspace) {
		System.out.println(command);
		// TODO put it all together
		if ( command.equals("") )
			return;
		String[] commandPieces = command.split(" ");
		ParseNode commandTree = makeTree(commandPieces);
		double result = readTree(commandTree);
		terminal.addEntry(new StringNumEntry(command,result));
		
	}
	
	public ParseNode makeTree(String[] commands){
        for(String s: commands){
        	System.out.println(s);
        }
		ParseNode root = new ParseNode(parseCommand(commands[0]));
		List<ParseNode> instructions = new ArrayList<ParseNode>();
		instructions.add(root);
		for(int i = 1;i< commands.length; i++){
			int size = instructions.size() - 1;
			ParseNode currentNode = new ParseNode(parseCommand(commands[i]));
			if(!parseCommand(commands[i]).equals("")){
				//currentNode.setName(parseCommand(commands[i]));
				instructions.add(currentNode);
			}
			else{
				currentNode.setValue(Integer.parseInt(commands[i]));
			}
			
//			if(i == 0){
//				root = currentNode;
//			}
			if(i > 0){
				ParseNode parent = instructions.get(size);
				for(int j = size; j >= 0; j--){
					parent = instructions.get(j);
					if(parent.getChildren().size() < commandInputs.get(parent.getName())){
						break;
					}
				}
				parent.addChild(currentNode);
			}
		}
		return root;
	}
	
	private double readTree(ParseNode root){
		ParseNode current = root;
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
			if(current.getChildren().size() == commandInputs.get(current.getName())){
				MathCommands mathmathmath = new MathCommands();
				//call the correct method with current
				//make sure I have the correct # of kids
				//current == the instruction
				//its kids == the inputs
				//int value;
				//current.setValue(value);
				List<ParseNode> womp = current.getChildren();
				double[] args = new double[womp.size()];
				int i = 0;
				for(ParseNode w: womp){
					args[i] = w.getValue();
					i++;
				}
				
				current.setValue(mathmathmath.callCommand(current.getName(), args));
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
				ErrorMessage err = new ErrorMessage("Not a Valid Command");
				err.showError();
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
			String[] values = properties.getProperty(key).split("|");
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
