package backend;

import java.util.*;


public class CommandParser {
	
	private String myCommand;
	private String myLanguage;
	private Parameters myParameters;
	private Map<String, Integer> commandInputs;
	

	public CommandParser() {
		// TODO Auto-generated constructor stub
	}
	
	public void parse(String command) {
		// TODO put it all together
				
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
	
	
	private void inputCommand(String command) {
		myCommand = command;
	}
	
	public void setLanguage(String language) {
		myLanguage = language;
	}
	
	private String parseCommand(String command) {
		// TODO search through properties file to find command
	}
	
	public Parameters getParameters() {
		return myParameters;
	}
	
	private void throwError() {
		// TODO throw an error 
	}

}
