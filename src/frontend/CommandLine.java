package frontend;

import java.util.ResourceBundle;
import backend.CommandParser;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;

<<<<<<< HEAD
/**
 * This class contains the UI pane that will serve as main input to the user.
 * SLogo's text commands are input here. 
 *  
 * @author JoeLilien + AlanCavalcanti
 *
 */
public class CommandLine {    
	private ResourceBundle sceneResources =
			ResourceBundle.getBundle(UserInterface.DEFAULT_RESOURCE_PACKAGE + UserInterface.SCENE);
	private static final double WIDTH = 500;
	private static final double HEIGHT = 35;
	private TextArea myTextField;
	private Button myGoButton;

	// Submitting code pressing SHIFT+ENTER
	private boolean shiftPressed = false;
	private boolean enterPressed = false;


	public CommandLine(CommandParser parser, EntryManager terminal, EntryManager command, EntryManager workspace, EntryManager color, EntryManager shape ){
		initCommandLine(parser, terminal, command, workspace, color, shape);

	}

	/**
	 * Initialize method, redirected from the constructor.
	 * Ties in all the UI panes affected by the commandLine's inputs.
	 * 
	 * @param parser
	 * @param terminal
	 * @param command
	 * @param workspace
	 * @param color
	 * @param shape
	 */
	private void initCommandLine(CommandParser parser, EntryManager terminal, EntryManager command, EntryManager workspace, EntryManager color, EntryManager shape){
		myTextField = new TextArea();       
		myTextField.getStyleClass().add(sceneResources.getString("COMMANDLINEID"));
		myTextField.setPrefSize(WIDTH, HEIGHT);
		myGoButton = new Button(sceneResources.getString("GOBUTTON")); 
		myGoButton.getStyleClass().add(sceneResources.getString("BUTTONID"));
		myGoButton.setOnAction(e -> enterCommand(parser, terminal, command, workspace, color, shape));           
		myTextField.setOnKeyPressed(e -> keyPressed(e.getCode(), parser, terminal, command, workspace, color, shape, true));
		myTextField.setOnKeyReleased(e -> keyPressed(e.getCode(), parser, terminal, command, workspace, color, shape, false));
	}

	/**
	 * Main bridge between Back and Frontend. After confirming an input, the string is sent to the
	 * backend for parsing. 
	 * 
	 * This is activated either by the GO button, or if the user presses shift + enter
	 * 
	 * @param parser
	 * @param terminal
	 * @param command
	 * @param workspace
	 * @param color
	 * @param shape
	 */
	private void enterCommand(CommandParser parser, EntryManager terminal, EntryManager command, EntryManager workspace, EntryManager color, EntryManager shape)
	{
		shiftPressed = false;
		enterPressed = false;

		if ( !myTextField.getText().isEmpty() )
		{
			parser.parse(myTextField.getText(), terminal, command, workspace, color, shape, true, true);
			myTextField.clear();
		}

	}
	
	/**
	 * Key handler, listens for a change in any key pressed. 
	 * If they correspond to either Shift or Enter, lift a flag.
	 * If both flags are raised, input the command, as if it were a "GO" button press.
	 * 
	 * @param code
	 * @param parser
	 * @param terminal
	 * @param command
	 * @param workspace
	 * @param color
	 * @param shape
	 * @param beingPressed
	 */
	private void keyPressed(KeyCode code, CommandParser parser, EntryManager terminal, EntryManager command, EntryManager workspace, EntryManager color, EntryManager shape, boolean beingPressed)

	{
		if ( code == KeyCode.SHIFT )
			shiftPressed = beingPressed;

		else if ( code ==  KeyCode.ENTER)
			enterPressed = beingPressed;

		if ( enterPressed && shiftPressed )
			enterCommand(parser, terminal, command, workspace,color,shape);

	}

	public TextArea getTextField(){
		return myTextField;
	}
	public Node getButton(){
		return myGoButton;
	}    
=======

/**
 * Holds text area object that allows for users to input commands and sends the input to the command
 * parser of the backend upon user prompt, to be placed in the UserInterface
 * 
 * @author JoeLilien
 *
 */
public class CommandLine {
    private ResourceBundle sceneResources =
            ResourceBundle.getBundle(UserInterface.DEFAULT_RESOURCE_PACKAGE + UserInterface.SCENE);
    private static final double WIDTH = 500;
    private static final double HEIGHT = 35;
    private TextArea myTextField;
    private Button myGoButton;

    // Submitting code pressing SHIFT+ENTER
    private boolean shiftPressed = false;
    private boolean enterPressed = false;

    public CommandLine (CommandParser parser,
                        EntryManager terminal,
                        EntryManager command,
                        EntryManager workspace) {
        initCommandLine(parser, terminal, command, workspace);

    }

    /**
     * Initializes CommandLine Text Area Object and Sets action response of sending string input to
     * backend to be parsed
     * 
     * @param parser
     * @param terminal
     * @param command
     * @param workspace
     * @param color
     * @param shape
     */
    private void initCommandLine (CommandParser parser,
                                  EntryManager terminal,
                                  EntryManager command,
                                  EntryManager workspace) {
        myTextField = new TextArea();
        myTextField.getStyleClass().add(sceneResources.getString("COMMANDLINEID"));
        myTextField.setPrefSize(WIDTH, HEIGHT);
        myGoButton = new Button(sceneResources.getString("GOBUTTON"));
        myGoButton.getStyleClass().add(sceneResources.getString("BUTTONID"));
        myGoButton.setOnAction(e -> enterCommand(parser, terminal, command, workspace));
        myTextField.setOnKeyPressed(e -> keyPressed(e.getCode(), parser, terminal, command,
                                                    workspace, true));
        myTextField.setOnKeyReleased(e -> keyPressed(e.getCode(), parser, terminal, command,
                                                     workspace, false));
    }

    /**
     * Method called upon user input, sends text field value to Command Parser so it can be parsed
     * 
     * @param parser
     * @param terminal
     * @param command
     * @param workspace
     * @param color
     * @param shape
     */
    private void enterCommand (CommandParser parser,
                               EntryManager terminal,
                               EntryManager command,
                               EntryManager workspace) {
        shiftPressed = false;
        enterPressed = false;

        if (!myTextField.getText().isEmpty()) {
            parser.parse(myTextField.getText(), terminal, command, workspace, true, true);
            myTextField.clear();
        }

    }

    /**
     * Method Initializes and allows for shift-enter functionallity as a shortcut for pressing go
     * 
     * @param code
     * @param parser
     * @param terminal
     * @param command
     * @param workspace
     * @param color
     * @param shape
     * @param beingPressed
     */
    private void keyPressed (KeyCode code,
                             CommandParser parser,
                             EntryManager terminal,
                             EntryManager command,
                             EntryManager workspace,                         
                             boolean beingPressed)

    {
        if (code == KeyCode.SHIFT)
            shiftPressed = beingPressed;

        else if (code == KeyCode.ENTER)
            enterPressed = beingPressed;

        if (enterPressed && shiftPressed)
            enterCommand(parser, terminal, command, workspace);

    }

    public TextArea getTextField () {
        return myTextField;
    }

    public Node getButton () {
        return myGoButton;
    }
>>>>>>> master

}
