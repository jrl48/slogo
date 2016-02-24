package frontend;

import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
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
        myScene = new Scene(root, WIDTH, HEIGHT);
        myScene.getStylesheets().add(DEFAULT_RESOURCE_PACKAGE + STYLESHEET);
        root.getChildren().add(makeGridPane());
        s.setScene(myScene);
    }
    
    private GridPane makeGridPane(){
        GridPane myGridPane = new GridPane();
        myGridPane.setHgap(10);
        myGridPane.setVgap(10);
        myGridPane.setPadding(new Insets(0, 10, 0, 10));
        myGridPane.setGridLinesVisible(true);
        
        Text category = new Text("Sales:");
        category.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        myGridPane.add(category, 1, 0); 

        // Title in column 3, row 1
        Text chartTitle = new Text("Current Year");
        chartTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        myGridPane.add(chartTitle, 2, 0);

        // Subtitle in columns 2-3, row 2
        Text chartSubtitle = new Text("Goods and Services");
        myGridPane.add(chartSubtitle, 1, 1, 2, 1);
        return myGridPane;
    }
}