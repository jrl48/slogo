package frontend;

import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class UserInterface {
    private final static UserInterface myUserInterface = new UserInterface();
    public static final String DEFAULT_RESOURCE_PACKAGE = "resources/frontendResources/";    
    private static final String SCENE = "Scene";
    private static final String STYLESHEET = "custom.css";
    private static final String BUTTONLABELS = "ButtonLabels";    
    public static final ResourceBundle sceneResources =
            ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + SCENE);
    public static final ResourceBundle buttonResources =
            ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + BUTTONLABELS);
    public static final double WIDTH = 800;
    public static final double HEIGHT = 600;
    private Scene myScene;
    private Group root;
    private GridPane myGridPane;
    
    // Components
    private Display myDisplay;
    private CommandLine myCommandLine;
    
    private UserInterface () {
    }

    public static UserInterface getUserInterface () {
        return myUserInterface;
    }

    public void init (Stage s) {
        initModules();
        s.setTitle(sceneResources.getString("TITLE"));
        s.setResizable(false);
        root = new Group();
        myScene = new Scene(root, WIDTH, HEIGHT, Color.SKYBLUE);
        myScene.getStylesheets().add(DEFAULT_RESOURCE_PACKAGE + STYLESHEET);
        root.getChildren().add(makeGridPane());
        s.setScene(myScene);
    }
    
    private void initModules(){
        myDisplay = new Display();
        myCommandLine = new CommandLine();
    }
    
    private GridPane makeGridPane(){
        myGridPane = new GridPane();    
        myGridPane.getStyleClass().add(sceneResources.getString("GRIDPANEID"));
        
        myGridPane.add(myDisplay.getPane(), 1, 1);         
        myGridPane.add(myCommandLine.getTextField(), 1, 2);
        
     // Title in column 3, row 1
        Text chartTitle = new Text("Current Year");
        chartTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        myGridPane.add(chartTitle, 2, 0);
        
        return myGridPane;
    }


    
}
