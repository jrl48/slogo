## SLOGO Design

### Introduction

With this program, our team is trying to construct an API for a command line language that can move a turtle object, do basic math, work with booleans, edit a background display, and work with basic variables and loops. Our primary design goal is to construct our program in such a way that adding new commands is extremely simple, so it will be easy to expand our range of functionality. A large majority of our code is closed, but there are certain areas that remain open for extension due to the potential increase in functionality (adding new panels to our display, being able to handle different categories of functions, etc.).

### UserInterface 
![UI Mockup](/SLOGO_mockup.png)

The interface will be laid out according to the sketch shown above.  The user will be able to type commands in on the command line, hitting enter after each command.  After each command, the GUI will display results accordingly.  If the turtle is moved, its position will be updated on the display screen; if a variable is to be stored, it will be displayed in the workspace; if a command is defined, it will be displayed in the user defined commands panel.  After every command, both the command and the returned result will be displayed in the command history panel.  There is also a preferences button that will open up a pop-up window with a number of personalization settings, such as display background color, line color, turtle image, drop-down for language selection, etc. Error messages will be displayed as popups to the user whenever any erroneous situations should occur.  These will include bad input data, divide by zero, reference a variable that doesn’t exist, and reference to a command that doesn’t exist.  

### Design Overview
![Program UML](/SLOGO_uml.png)

For every command entered in the frontend, it will call the backend through the public void parse(String command) method, which calls the other methods in the CommandParser class; and the setLanguage(String language), which sets the language. Then the parse method would go through all the cases to figure out which instruction the string corresponds with and then call the appropriate method from the UserInterface class. When that method is called, a Parameters object will be populated through the addParam(int paramType, String param), and the abstract Parameters class will have subclasses to help in determining which . If needed, other classes can also call the getParameters() and the getInfo() methods. 

The Frontend is a collection of Classes that comprise the complete User Interface. Each class adds a different functionality to the program. They are:

Display, shows the Slogo doodling
Turtle, contains all information on turtle (Pen, Turtle image, etc)
Command Line, the input for the user
Terminal, shows the list of past commands and backend output
ErrorHandler, creates a popup of whatever Error comes up
PreferencesPane, opens up a popup that changes the program’s Parameters (Background Color, Line Color, Language)
Workspace, displays all variables and their values
DefinedCommands, displays the user-created commands that are available
HelpScreen, displays the help window


### API Details

#### Front End:

##### External:

* UserInterface:
    * moveTurtle(int x, int y)
    * openError()
    * showResult()
    * addVariable(double var, string varName)
    * setVariable(double var, string varName)
    * getInstance()
    * changeTurtlePen()
    * changeTurtleVisibility()
    * clearDisplay()
    * addCommand()


##### Internal:

* UserInterface
    * makePane()
* Display
    * init()
    * drawLine(int x, int y, Color)
    * change BackgroundColor()
    * clearDisplay()
* Turtle
    * moveTurtle(int x, int y)
    * changeImage()
    * changeVisibility()
    * changePen()
* Terminal
    * init()
    * updateHistory()
    * giveResult(double Result)
* Workspace
    * addVariable()
    * setVariable()
    * loadVariable()
    * init()
* DefinedCommands
    * addCommand()
    * loadCommand()
    * init()
* HelpScreen
    * init()
* CommandLine
    * init()
* ErrorHandler
    * init()
* PreferencesPane
    * init()

As we are planning to implement the Facade design pattern, centered around our UserInterface class, all of the methods in our project’s front end external API will be from this class.  These methods, listed above, are all that the back end classes will ever need to access in order to execute any command that the user can be expected to input on the command line.  For example, if the user enters a command to move the turtle, the back end classes will use the moveTurtle() method of our API which will update the movement accordingly.  If the user clicks on a command from the history terminal, the back end classes will still only need access to the showResult() method of our API.  Our front end internal API includes all of the methods necessary for front end classes to communicate with each other and to be extended.  For example, if one wanted to extend the GUI to include another window, they would need to call the makePane() method of the UserInteface class.  Additionally, if changes are made by the user to the preferences of the program such as the display background color, our internal API can handle this input through Display’s changeBackgroundColor() method.  Each class included in the internal API serves a specific purpose necessary to achieve a project functionality specification, as described by its name.  For example, the Terminal class provides all the methods and functionality to maintain the list of clickable past commands and results, as specified in the project requirements.


#### Back End:
##### External:
* CommandParser
    - public void parse(String command)				
    - public void setLanguage(String language)			
    - public int getInfo()						
    - public Parameters getParameters()				
* Parameters
    - public void addParam(int paramType, String param)	
    - public Parameters getParameters()				

##### Internal:
* Parameters
    - public void addParam(int paramType, String param)
* CommandParser
    - public void parse(String command)

In the parse method we plan to throw a CommandDoesNotExist exception if the command does not exist, and then a DivideByZero Exception if they try to divide by zero.

Our backend external API is used to communicate the String command, and the result from that command will be given back to the front-end via its external API. And our backend internal API is for further extension, if future users want to extend the extent of our parser and the types of instructions we can handle. The external and internal API will probably only use Strings, doubles, and ints, and in the worst case scenario, switch cases. But otherwise they will have Lists and use the Parameters object. The Parameters class is an abstract class that warrants extension for each different type of command (turtle, math, boolean, etc.), as each different type of command will store objects of different types. A Parameters object contains a list of objects, so each extension contains a more specific type of object that aligns with a certain classification of commands. Since it is difficult to store all of this information in one class, extending it and dividing it into categories of commands is what we decided to do here.

### API Example Code

The user types 'fd 50' in the command window, and sees the turtle move in the display window leaving a trail, and the command is added to the environment's history

* When user hits “enter,” CommandLine.handleInput() and updateHistory() will be called
* This will call back-end’s CommandParser.parse(String input)
* Within that, CommandParser.inputCommand(String input) and CommandParser.parseCommand(String input) are called
* Then, still within CommandParser.parse(String input), the front end method UserInterface.moveTurtle(String direction, int amount) is called (the parameters are created from CommandParser.parseCommand(String input)

The user types '+ 1 2' in the command window, and sees the answer, 3, displayed next to the command in the history terminal.

* The user hits “enter”, and then calls CommandLine.handleInput() and updateHistory()
* That will then call the CommandParser.parse(String input), which is apart of the back end
* The parse method will then call the inputCommand(String input) and the parseCommand(input), which would determine that the instruction is SUM
* the calculated sum will then be given to the UserInterface.showResult(double result) which will display the sum into the terminal

The user clicks on the last line of code represented in the history panel, ‘LESSP 5 2’, the answer ‘0’ is displayed next to the command in the history terminal.

* When the user clicks the line of code, the terminal class will call CommandParser(String) with that command as the argument.
* The CommandParser will call the handleCommand() method of the correct Parameters subclass
* The overriden handleCommand() method will handle the math necessary to make the comparison and display the value in the terminal by calling the UserInterface.showResult() method.

The user types ‘GREATER? 7 5’ in the command window, and sees the answer, ‘1’ (which means that the first expression is greater than the second one), displayed next to the command in the history terminal.

* When user hits “enter,” CommandLine.handleInput() and CommandLine.updateHistory() will be called
* This will call back end’s CommandParser.parse(String input)
* Within that, CommandParser.inputCommand(String input) and CommandParser.parseCommand(String input) are called
* Then, still within CommandParser.parse(String input), the program will determine that the first expression is greater than the second expression and output the number ‘1’ on the screen.

Assuming the turtle is home. The user types in ‘XCOR’ in the command window, and sees the answer ‘0’ displayed in the History Terminal.

* When user hits “enter,” CommandLine.handleInput() and CommandLine.updateHistory() will be called
* This will call back end’s CommandParser.parse(String input)
* The CommandParser will call the handleCommand() method of the correct Parameters subclass
* The overriden handleCommand() method will take the value of the X position of the turtle, and call UserInterface.showResult(double result).
* In UserInterface, the showResult method will call Terminal’s show result Method, which will then display the answer 0.

### Design Considerations

One major discussion our group had was whether the front end or the back end call the actual commands that execute within our program. We concluded that since the back end is already parsing the commands entered by the user, it makes sense for that part of the program to call methods that work in the front end based on the newly parsed parameters. That way, there is no need to find a way to pass the results of the parse back to a front end class, which would be unnecessary and potentially detrimental to our design. In the back end, there were a few minor assumptions made to ensure that the design works, one of which is the ease of converting the command line input into the corresponding command in the correct properties file. Our other assumption is that we have a way to add to the Parameters file such that the front end is able to know what our commands mean, which is a feature that we will be developing.

### Team Responsibilities

The back end will be split into two parts: dealing with the parsing and adding to the parameters. Gaurav will be in charge of the parsing class which will determine what the instruction is. Virginia will be in charge of the parameters, which is an abstract class that will be called on when the instruction is determined.

The front-end will be split into image-based and text-based functionalities. Alan will be in charge of image (Display, Turtle, Help, Preferences and Error Handling), Joe will be in charge of Text (Command Line, Terminal, Workspace and UserDefined).
Both will update the User Interface facade class.
