package backend;

import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import frontend.UserInterface;

public class CommandParser {
	
	private final static CommandParser myParser = new CommandParser();
	private String myCommand;
	private String myLanguage;
	private Parameters myParameters;
	private Map<String, Integer> commandInputs;
	

	private CommandParser() {
		// TODO Auto-generated constructor stub
	}
	
	public void parse(String command) {
		// TODO put it all together
				
	}
	
	public CommandParser getParser(){
		return myParser;
	}
	
	private ParseNode makeTree(String[] commands){
		ParseNode root = new ParseNode(commands[0]);
		List<ParseNode> instructions = new ArrayList<ParseNode>();
		for(int i = 0;i< commands.length; i++){
			int size = instructions.size() - 1;
			ParseNode currentNode = new ParseNode(commands[i]);
			if(!parseCommand(commands[i]).equals("")){
				currentNode.setValue(parseCommand(commands[i]));
				instructions.add(currentNode);
			}
			if(i == 0){
				root = currentNode;
			}
			else{
				ParseNode parent = instructions.get(size);
				for(int j = size; j >= 0; j--){
					parent = instructions.get(j);
					if(parent.getChildren().size() < commandInputs.get(parent)){
						break;
					}
				}
				parent.addChild(currentNode);
			}
		}
		return root;
	}
	
	private void readTree(ParseNode root){
		
	}
	
	
	private void inputCommand(String command) {
		myCommand = command;
	}
	
	public void setLanguage(String language) {
		myLanguage = language;
	}
	

	private String parseCommand(String command) {
		try {
			FileInputStream fileInput = new FileInputStream(new File(myLanguage + ".properties"));
			Properties properties = new Properties();
			properties.load(fileInput);
			fileInput.close();
			Enumeration commands = properties.keys();
			String desiredCommand = getDesiredCommand(properties,commands,command);
			if (desiredCommand.equals(""))
				UserInterface.displayError("That is not a command!");
			return desiredCommand;
		} catch (FileNotFoundException e) {
			throwError(e);
		} catch (IOException e) {
			throwError(e);
		}
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

}
