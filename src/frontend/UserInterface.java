package frontend;

import java.util.ResourceBundle;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class UserInterface {
    private final static UserInterface myUserInterface = new UserInterface();
    public static final String DEFAULT_RESOURCE_PACKAGE = "resources/frontendResources/";    
    private static final String SCENE = "Scene";
    private static final String STYLESHEET = "custom.css";
    private static final String BUTTONLABELS = "ButtonLabels";    
    private ResourceBundle sceneResources =
            ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + SCENE);
    private ResourceBundle buttonResources =
            ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + BUTTONLABELS);
    public static final double WIDTH = 900;
    public static final double HEIGHT = 600;
    private Scene myScene;
    private Group root;

    private UserInterface () {
    }

    public static UserInterface getUserInterface () {
        return myUserInterface;
    }

    public void init (Stage s) {
        s.setTitle(sceneResources.getString("TITLE"));
        s.setResizable(false);
        root = new Group();
        myScene = new Scene(root, WIDTH, HEIGHT, Color.SKYBLUE);
        myScene.getStylesheets().add(DEFAULT_RESOURCE_PACKAGE + STYLESHEET);
        s.setScene(myScene);
    }
}
