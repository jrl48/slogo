package frontend;

//import backend.CommandParser;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class CommandLine {
    private static final double WIDTH = 500;
    private static final double HEIGHT = 35;
    private TextField myTextField;
    
    public CommandLine(){
        initCommandLine();
    }
    
    private void initCommandLine(){
        myTextField = new TextField();       
        myTextField.getStyleClass().add(UserInterface.sceneResources.getString("COMMANDLINEID"));
        myTextField.setPrefSize(WIDTH, HEIGHT);
        myTextField.setOnKeyPressed(e-> handleKeyInput(e.getCode()));
    }
    private void handleKeyInput(KeyCode code){
        if(code.equals(KeyCode.ENTER)){
           //CommandParser.getParser().parse(myTextField.getText());
           myTextField.clear();
        }
    }
    public Node getTextField(){
        return myTextField;
    }
}
