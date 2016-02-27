package frontend;

import java.util.ResourceBundle;
import javafx.geometry.Pos;
//import backend.CommandParser;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class CommandLine {
    private static final String DEFAULT_RESOURCE_PACKAGE = "resources/frontendResources/";
    private static final String SCENE = "Scene";
    private ResourceBundle sceneResources =
            ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + SCENE);
    private static final double WIDTH = 500;
    private static final double HEIGHT = 35;
    private TextArea myTextField;
    private Button myGoButton;
    
    // Submitting code pressing SHIFT+ENTER
    private boolean shiftPressed;
    private boolean enterPressed;
    
    // TODO: Take this out! FOR DEBUGGING ONLY
    private Display display;
    // --------------------------------
        

    public CommandLine(EntryManager manager){
        initCommandLine(manager);

    }
    
    private void initCommandLine(EntryManager manager){
        myTextField = new TextArea();       
        myTextField.getStyleClass().add(sceneResources.getString("COMMANDLINEID"));
        myTextField.setPrefSize(WIDTH, HEIGHT);
        myGoButton = new Button("GO"); //TODO use resource file
        myGoButton.setOnAction(e -> enterCommand(manager));
        shiftPressed = false;
        enterPressed = false;
        
        myTextField.setOnKeyPressed(e -> keyPressed(e.getCode(), manager, true));
        myTextField.setOnKeyReleased(e -> keyPressed(e.getCode(), manager, false));
    }
    
    private void enterCommand(EntryManager manager){
    	if ( !myTextField.getText().isEmpty() )
    	{
    		manager.addEntry(new StringIntEntry(myTextField.getText(),0)); //TODO Do Something here
        	
    		// TODO: Take this out! FOR DEBUGGING ONLY
        	if ( myTextField.getText().equals("fd") )
        		display.moveTurtleForward(20);
        	else if ( myTextField.getText().equals("pen") )
        		display.toggleTurtlePen();
        	else if ( myTextField.getText().equals("turn right") )
        		display.turnTurtle(90);
        	else if ( myTextField.getText().equals("turn left") )
        		display.turnTurtle(-90);
        	// -----------------------------
        	
    		myTextField.clear();
    	}
    }
    
    private void keyPressed(KeyCode code, EntryManager manager, boolean beingPressed)
    {
    	if ( code == KeyCode.SHIFT )
    		shiftPressed = beingPressed;
    	
    	else if ( code ==  KeyCode.ENTER)
    		enterPressed = beingPressed;
    	
    	if ( enterPressed && shiftPressed )
    		enterCommand(manager);
    	
    }
    
  
    
    public TextArea getTextField(){
        return myTextField;
    }
    public Node getButton(){
        return myGoButton;
    }    
    
    // TODO: Take this out! FOR DEBUGGING ONLY
    public void setDisplay(Display display)
    {
    	this.display = display;
    }
    // -------------------------------------
}
