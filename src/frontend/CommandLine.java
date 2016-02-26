package frontend;

import javafx.geometry.Pos;
//import backend.CommandParser;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class CommandLine {
    private static final double WIDTH = 500;
    private static final double HEIGHT = 35;
    private TextArea myTextField;
    private Button myGoButton;
    
    public CommandLine(){
        initCommandLine();
    }
    
    private void initCommandLine(){
        myTextField = new TextArea();       
        myTextField.getStyleClass().add(UserInterface.sceneResources.getString("COMMANDLINEID"));
        myTextField.setPrefSize(WIDTH, HEIGHT);
        myGoButton = new Button("GO"); //TODO use resource file
        myGoButton.setOnAction(e -> enterCommand());
    }
    
    private void enterCommand(){
        myTextField.getText(); //TODO Do Something here
        myTextField.clear();
    }
    
    public Node getTextField(){
        return myTextField;
    }
    public Node getButton(){
        return myGoButton;
    }    
}
