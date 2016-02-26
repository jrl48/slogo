package frontend;

import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.layout.Pane;


public class Display {
    private static final String DEFAULT_RESOURCE_PACKAGE = "resources/frontendResources/";
    private static final String SCENE = "Scene";
    private ResourceBundle sceneResources =
            ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + SCENE);
    private Pane myPane;
    private static final double WIDTH = 450;
    private static final double HEIGHT = 450;
    
    public Display () {
        initPane();
    }
    
    private void initPane(){
        myPane = new Pane();
        myPane.getStyleClass().add(sceneResources.getString("DISPLAYID"));
        myPane.setPrefSize(WIDTH, HEIGHT);
    }
    public Node getPane () {
        return myPane;
    }
}
