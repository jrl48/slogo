

public interface CommandParserInterface {
	//parses the command: translates the string to an instruction
	
	public void parse(String command); //this is how the front end inputs a string to the backend to parse
	public void setLanguage(String language);//this will be updated everything the user changes the language on the GUI		
	public int getInfo();	//returns a 1 or 0 depending on whether the instruction is a turtle move or not					
	public Parameters getParameters();		//returns a Parameter object to the front end if they need to view any values
}
