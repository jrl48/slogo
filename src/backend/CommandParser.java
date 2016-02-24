package backend;

public class CommandParser {
	
	private String myCommand;
	private String myLanguage;
	private Parameters myParameters;

	public CommandParser() {
		// TODO Auto-generated constructor stub
	}
	
	public void parse(String command) {
		// TODO put it all together
	}
	
	private void inputCommand(String command) {
		myCommand = command;
	}
	
	public void setLanguage(String language) {
		myLanguage = language;
	}
	
//	private String parseCommand(String command) {
//		// TODO search through properties file to find command
//	}
	
	public Parameters getParameters() {
		return myParameters;
	}
	
	private void throwError() {
		// TODO throw an error 
	}

}
