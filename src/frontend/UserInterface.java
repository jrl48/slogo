
package frontend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import backend.CommandParser;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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
    public static final String SCENE = "Scene";
    public static final String STYLESHEET = "custom.css";
    private ResourceBundle sceneResources =
            ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + SCENE);
    private GridPane myGridPane;

    // Components
    private CommandParser myCommandParser;
    private Display myDisplay;
    private CommandLine myCommandLine;
    private TerminalView myTerminal;
    private WorkspaceView myWorkspace;
    private UserDefinedView myUserDefined;
    private EntryManager myTerminalManager;
    private EntryManager myCommandManager;
    private EntryManager myWorkspaceManager;
    private LanguageManager myLanguageManager;
    private LanguagePreferences myLanguagePreferences;
    private DisplayPreferences myDisplayPreferences;
    private HTMLopener myHTMLopener;   
    private Button newWorkButton;

    public UserInterface (Stage s, Button newWorkButton) {
        this.newWorkButton = newWorkButton;
        init(s);
    }

    public void init (Stage s) {
        initModules(s);               
        Tab tab1 = new Tab();
        tab1.setContent(makeGridPane());
    }

    private void initModules (Stage primaryStage) {
        myDisplayPreferences = new DisplayPreferences(primaryStage);
        myDisplay = new Display(myDisplayPreferences);
        myTerminalManager = new EntryManager();
        myCommandManager = new EntryManager();
        myWorkspaceManager = new EntryManager();
        myLanguageManager = new LanguageManager();
    	myCommandParser = new CommandParser(myDisplay);
        myCommandLine = new CommandLine(myCommandParser, myTerminalManager, myCommandManager, myWorkspaceManager);
        myTerminal = new TerminalView(myCommandLine, myTerminalManager, sceneResources.getString("TERMINAL"), new String[]{sceneResources.getString("TERMINAL_1"),sceneResources.getString("TERMINAL_2")});
        myWorkspace = new WorkspaceView(myWorkspaceManager, sceneResources.getString("WORKSPACE"), new String[]{sceneResources.getString("WORKSPACE_1"),sceneResources.getString("WORKSPACE_2")});
        myUserDefined = new UserDefinedView(myCommandLine,myCommandManager, sceneResources.getString("USERCOMMANDS"), new String[]{sceneResources.getString("USERCOMMANDS_1"), sceneResources.getString("USERCOMMANDS_2")});
        myLanguagePreferences = new LanguagePreferences(myLanguageManager,myCommandParser);
        myHTMLopener = new HTMLopener();
    }

    private GridPane makeGridPane () {
        myGridPane = new GridPane();
        myGridPane.getStyleClass().add(sceneResources.getString("GRIDPANEID"));
        myGridPane.add(myDisplay.getPane(), 1, 1);
        myGridPane.add(myCommandLine.getTextField(), 1, 2, 1, 6);
        myGridPane.add(makeBox(new HBox(), sceneResources.getString("HBOXID"),new ArrayList<Node>(Arrays.asList(myCommandLine.getButton(),myDisplayPreferences.getButton(),myLanguagePreferences.getComboBox(),myHTMLopener.getButton(),newWorkButton))), 2, 6,3,6);
        myGridPane.add(makeBox(new VBox(), sceneResources.getString("VBOXID"),new ArrayList<Node>(Arrays.asList(myTerminal.getMyLabel(), myTerminal.getMyTableView(),
            myWorkspace.getMyLabel(), myWorkspace.getMyTableView()))), 2, 1,2,5);
        myGridPane.add(makeBox(new VBox(), sceneResources.getString("VBOXID"),new ArrayList<Node>(Arrays.asList(myUserDefined.getMyLabel(),myUserDefined.getMyTableView()))), 4, 1);
        return myGridPane;
    }
    
    private Node makeBox (Pane box, String cssID, List<Node> items) {
        Pane myBox = box;
        myBox.getStyleClass().add(cssID); 
        myBox.getChildren().addAll(items);
        return myBox;
    }
    public GridPane getGridPane(){
        return myGridPane;
    }

}
