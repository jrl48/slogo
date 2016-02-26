package frontend;

import javafx.scene.Node;
import javafx.scene.layout.Pane;


public class Display {
    private Pane myPane;
    private static final double WIDTH = 450;
    private static final double HEIGHT = 450;
    
    public Display () {
        initPane();
    }
    
    private void initPane(){
        myPane = new Pane();
        myPane.getStyleClass().add(UserInterface.sceneResources.getString("DISPLAYID"));
        myPane.setPrefSize(WIDTH, HEIGHT);
    }
    public Node getPane () {
        return myPane;
    }
}
