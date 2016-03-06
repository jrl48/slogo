package frontend;

import java.util.ResourceBundle;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

public class SLOGOScreen {   
    public static final double WIDTH = 1100;
    public static final double HEIGHT = 630;
    private static final double BUTTON_X = WIDTH - 20;
    private Scene myScene;
    private TabPane myTabPane;
    private ComboBox<Entry> mySavedWorkspaces;
    private EntryManager mySavedManager = new EntryManager();
    private Group root;    
    private ResourceBundle sceneResources =
            ResourceBundle.getBundle(UserInterface.DEFAULT_RESOURCE_PACKAGE + UserInterface.SCENE);
    private Button newWorkspace = new Button("+");
    
    public void init(Stage s){
        s.setTitle(sceneResources.getString("TITLE"));
        s.setResizable(false);
        root = new Group();        
        myScene = new Scene(root, WIDTH, HEIGHT, Color.SKYBLUE);          
        myScene.getStylesheets().add(UserInterface.DEFAULT_RESOURCE_PACKAGE + UserInterface.STYLESHEET);
        myTabPane = new TabPane();     
        myTabPane.setPrefWidth(BUTTON_X);
        initComboBox(s);
        addUserInterface(sceneResources.getString("WORKSPACE"), s,false,new UserInterface(s,mySavedWorkspaces));
        initButton(s);      
        root.getChildren().add(myTabPane);
        s.setScene(myScene);
    }
    private void initComboBox (Stage s) { //TODO probably make combobox facotry
        mySavedWorkspaces = new ComboBox<Entry>(mySavedManager.getEntryList());
        //
        mySavedManager.addEntry(new StringObjectEntry("workspace", new UserInterface(s,mySavedWorkspaces)));
        mySavedWorkspaces.setPromptText("Saved Workspaces");
        mySavedWorkspaces.getStyleClass().add(sceneResources.getString("BUTTONID"));
        mySavedWorkspaces.setCellFactory(c-> new StringDisplayCell());
        mySavedWorkspaces.setOnAction(e->addUserInterface(sceneResources.getString("WORKSPACE"),s,true,(UserInterface) mySavedManager.getValue((String) mySavedWorkspaces.getSelectionModel().getSelectedItem().getFirstValue())));
    }
    public void addUserInterface(String title, Stage s, Boolean isClosable,UserInterface UI){
        if(UI==null){
            UI = new UserInterface(s,mySavedWorkspaces);
        }
        Tab t = new Tab(title+" "+(myTabPane.getTabs().size()+1));
        t.setClosable(isClosable);        
        t.setContent(UI.getGridPane());
        myTabPane.getTabs().add(t);
        myTabPane.getSelectionModel().select(t);
    }

    private void initButton(Stage s){
        newWorkspace.getStyleClass().add(sceneResources.getString("BUTTONID"));
        newWorkspace.setOnAction(e->addUserInterface(sceneResources.getString("WORKSPACE"),s,true,new UserInterface(s,mySavedWorkspaces)));
        newWorkspace.setLayoutX(BUTTON_X);
        root.getChildren().add(newWorkspace);
    }
}
