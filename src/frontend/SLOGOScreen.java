package frontend;

import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

public class SLOGOScreen {   
    public static final double WIDTH = 1100;
    public static final double HEIGHT = 640;
    private static final double HBOX_X = WIDTH - 215;
    private static final double HBOX_Y = 30;
    private Scene myScene;
    private TabPane myTabPane;
    private ComboBox<Entry> mySavedWorkspaces;
    private EntryManager mySavedManager = new EntryManager();
    private Group root;    
    private ResourceBundle sceneResources =
            ResourceBundle.getBundle(UserInterface.DEFAULT_RESOURCE_PACKAGE + UserInterface.SCENE);
    private Button newWorkspace;
    private Button saveWorkspace;    
    private HBox myControls = new HBox();
    private List<GridPane> myUIPaneList = new ArrayList<GridPane>();
    
    public void init(Stage s){
        s.setTitle(sceneResources.getString("TITLE"));
        s.setResizable(false);
        root = new Group();        
        myScene = new Scene(root, WIDTH, HEIGHT, Color.SKYBLUE);          
        myScene.getStylesheets().add(UserInterface.DEFAULT_RESOURCE_PACKAGE + UserInterface.STYLESHEET);
        myTabPane = new TabPane();     
        myTabPane.setMaxWidth(WIDTH);
        root.getChildren().add(myTabPane);
        initHBox();
        saveWorkspace = makeButton("Save");
        initComboBox(s);
        newWorkspace = makeButton("+");
        initButtons(s);      
        addUserInterface(sceneResources.getString("WORKSPACE"), s,false,null);
        s.setScene(myScene);
    }
    private void initComboBox (Stage s) { //TODO probably make combobox facotry
        mySavedWorkspaces = new ComboBox<Entry>(mySavedManager.getEntryList());        
        mySavedWorkspaces.setPromptText("Saved Workspaces");
        mySavedWorkspaces.getStyleClass().add(sceneResources.getString("BUTTONID"));
        mySavedWorkspaces.setCellFactory(c-> new StringDisplayCell());
        mySavedWorkspaces.setOnAction(e->openSavedWorkspace(s,(String) mySavedWorkspaces.getSelectionModel().getSelectedItem().getFirstValue(),(GridPane) mySavedWorkspaces.getSelectionModel().getSelectedItem().getSecondValue()));
        myControls.getChildren().add(mySavedWorkspaces); 
    }
    private void openSavedWorkspace (Stage s, String title, GridPane UIPane) {
        if(myUIPaneList.contains(UIPane)){
            myTabPane.getSelectionModel().select(myUIPaneList.indexOf(UIPane));
        }
        else{
            addUserInterface(title,s,true,UIPane);
        }
        mySavedWorkspaces.setAccessibleText("null");
    }
    public void addUserInterface(String title, Stage s, Boolean isClosable,GridPane UIPane){
        if(UIPane==null){
            UserInterface UI = new UserInterface(s);
            UIPane = UI.getGridPane();
        }
        myUIPaneList.add(UIPane);
        Tab t = new Tab(title+" "+(myTabPane.getTabs().size()+1));
        t.setClosable(isClosable);        
        t.setContent(UIPane);
        t.setOnCloseRequest(e->{myUIPaneList.remove(myTabPane.getSelectionModel().getSelectedIndex());
                                                      mySavedWorkspaces.getSelectionModel().clearSelection();});
        myTabPane.getTabs().add(t);
        myTabPane.getSelectionModel().select(t);
    }
    
    private Button makeButton(String title){
        Button b = new Button(title);
        b.getStyleClass().add(sceneResources.getString("BUTTONID"));
        myControls.getChildren().add(b);
        return b;
    }

    private void initButtons(Stage s){
        newWorkspace.setOnAction(e->addUserInterface(sceneResources.getString("WORKSPACE"),s,true,null));
        saveWorkspace.setOnAction(e->saveUserInterface((GridPane) myTabPane.getSelectionModel().getSelectedItem().getContent()));
    }
    private void saveUserInterface (GridPane UIPane) {   
       String title = promptforTitle();
       if(title==null){
           return;
       }
       myTabPane.getSelectionModel().getSelectedItem().setText(title);
       mySavedManager.addEntry(new StringObjectEntry(title, UIPane),true);
    }
    private String promptforTitle () {
        TextPrompt myPrompt = new TextPrompt("SavedWorkspace "+(mySavedManager.getEntryList().size()+1),"Enter Workspace Title: ");
        myPrompt.show();
        if(myPrompt.getText()==null){
            return null;
        }
        else if(myPrompt.getText().equals("")){
            return myTabPane.getSelectionModel().getSelectedItem().getText();
        }
        return myPrompt.getText();
    }
    private void initHBox(){
        myControls = new HBox();
        myControls.setLayoutX(HBOX_X);
        myControls.setLayoutY(HBOX_Y);
        myControls.toFront();
        myControls.getStyleClass().add(sceneResources.getString("HBOXID"));
        root.getChildren().add(myControls);
    }
}
