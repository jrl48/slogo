package frontend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class UserInterface {
    public static final String DEFAULT_RESOURCE_PACKAGE = "resources/frontendResources/";
    private static final String SCENE = "Scene";
    private static final String STYLESHEET = "custom.css";
    private static final String BUTTONLABELS = "ButtonLabels";
    private ResourceBundle sceneResources =
            ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + SCENE);
    private ResourceBundle buttonResources =
            ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + BUTTONLABELS);
    public static final double WIDTH = 1100;
    public static final double HEIGHT = 600;
    private Scene myScene;
    private Group root;
    private GridPane myGridPane;

    // Components
    private Display myDisplay;
    private CommandLine myCommandLine;
    private TerminalView myTerminal;
    private WorkspaceView myWorkspace;
    private UserDefinedView myUserDefined;
    private EntryManager myTerminalManager;
    private EntryManager myCommandManager;
    private EntryManager myWorkspaceManager;

    public UserInterface () {
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

    private void initModules () {
        myDisplay = new Display();
        myTerminalManager = new EntryManager();
        myCommandManager = new EntryManager();
        myWorkspaceManager = new EntryManager();
        myCommandLine = new CommandLine(myTerminalManager);
        // TODO: Take this out! FOR DEBUGGING ONLY
    	myCommandLine.setDisplay(myDisplay);
    	// -----------------------------
    	
        myTerminal = new TerminalView(myCommandLine, myTerminalManager, "Terminal", new String[]{"Command","Result"}); //TODO resource file
        myWorkspace = new WorkspaceView(myWorkspaceManager, "Workspace", new String[]{"Variable","Value"});
        myUserDefined = new UserDefinedView(myCommandLine,myCommandManager, "User Defined Commands", new String[]{"Command", "Value"});//TODO Resource file

    }

    private GridPane makeGridPane () {
        myGridPane = new GridPane();
        myGridPane.getStyleClass().add(sceneResources.getString("GRIDPANEID"));

        myGridPane.add(myDisplay.getPane(), 1, 1);
        myGridPane.add(myCommandLine.getTextField(), 1, 2, 1, 5);
        myGridPane.add(makeHBox(new ArrayList<Node>(Arrays.asList(myCommandLine.getButton()))), 2, 6);
        myGridPane.add(makeVBox(new ArrayList<Node>(Arrays.asList(myTerminal.getMyLabel(), myTerminal.getMyTableView(),
            myWorkspace.getMyLabel(), myWorkspace.getMyTableView()))), 2, 1,2,5);
        myGridPane.add(makeVBox(new ArrayList<Node>(Arrays.asList(myUserDefined.getMyLabel(),myUserDefined.getMyTableView()))), 4, 1);
        return myGridPane;
    }
    
    //TODO combine these into one method
    private Node makeVBox (List<Node> items) {
        VBox myVBox = new VBox();
        myVBox.getStyleClass().add("VBOXID"); // TODO deal with this and set insets
        myVBox.getChildren().addAll(items);
        return myVBox;
    }

    private Node makeHBox (List<Node> items) {
        HBox myHBox = new HBox();
        myHBox.getStyleClass().add("HBOXID"); // TODO deal with this
        myHBox.getChildren().addAll(items);
        return myHBox;
    }

}
