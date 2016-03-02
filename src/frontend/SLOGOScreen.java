package frontend;

import java.util.ResourceBundle;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SLOGOScreen {   
    public static final double WIDTH = 1100;
    public static final double HEIGHT = 630;
    private Scene myScene;
    private TabPane myTabPane;
    private Group root;    
    private ResourceBundle sceneResources =
            ResourceBundle.getBundle(UserInterface.DEFAULT_RESOURCE_PACKAGE + UserInterface.SCENE);
    private Button newWorkspace = new Button("New Workspace");
    
    public void init(Stage s){
        s.setTitle(sceneResources.getString("TITLE")); //TODO put in css
        s.setResizable(false);
        root = new Group();        
        myScene = new Scene(root, WIDTH, HEIGHT, Color.SKYBLUE);       
        myScene.getStylesheets().add(UserInterface.DEFAULT_RESOURCE_PACKAGE + UserInterface.STYLESHEET);
        myTabPane = new TabPane(); 
        addUserInterface("Workspace 1", s);
        initButton(s);
        root.getChildren().add(myTabPane);
        s.setScene(myScene);
    }
    public void addUserInterface(String title, Stage s){
        Tab t = new Tab(title);
        System.out.println(newWorkspace);
        UserInterface UI = new UserInterface(s, newWorkspace);
        t.setContent(UI.getGridPane());
        myTabPane.getTabs().add(t);
    }

    private void initButton(Stage s){
        newWorkspace.setOnAction(e->addUserInterface("Workspace",s));
    }
}
