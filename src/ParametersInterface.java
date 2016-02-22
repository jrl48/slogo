

public interface ParametersInterface {	//the point of this class is to make it easier for the CommandParser to parse the instruction
	//it should be extended into subclasses depending on the type of instruction
	//i.e. turtle move, boolean operation, etc
	// a different type of the Parameters class will be called depending on what type of instruction it is
	//and then the Parameters class will call the correct method

	public void addParam(int paramType, String param);	//adds a parameter to the List which will help keep track of history and which parameters have already been updated
	public Parameters getParameters();//returns the parameters if the CommandParser class needs extra information
}
