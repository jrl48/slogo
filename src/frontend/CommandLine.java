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
        
    public CommandLine(EntryManager manager){
        initCommandLine(manager);
    }
    
    private void initCommandLine(EntryManager manager){
        myTextField = new TextArea();       
        myTextField.getStyleClass().add(sceneResources.getString("COMMANDLINEID"));
        myTextField.setPrefSize(WIDTH, HEIGHT);
        myGoButton = new Button("GO"); //TODO use resource file
        myGoButton.setOnAction(e -> enterCommand(manager));
    }
    
    private void enterCommand(EntryManager manager){
        manager.addEntry(new StringIntEntry(myTextField.getText(),0)); //TODO Do Something Different here
        myTextField.clear();
    }
    
    public TextArea getTextField(){
        return myTextField;
    }
    public Node getButton(){
        return myGoButton;
    }    
}
