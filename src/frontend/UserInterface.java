
package frontend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import backend.CommandParser;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class UserInterface {
    public static final String DEFAULT_RESOURCE_PACKAGE = "resources/frontendResources/";
    public static final String SCENE = "Scene";
    public static final String STYLESHEET = "custom.css";
    private ResourceBundle sceneResources =
            ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + SCENE);
    private GridPane myGridPane;
    
    // Component Lists
    private List<Node> myButtonsList;
    private List<Node> myFirstColList;

    // Components
    private CommandParser myCommandParser;
    private Display myDisplay;
    private CommandLine myCommandLine;
    private TerminalView myTerminal;
    private WorkspaceView myWorkspace;
    private UserDefinedView myUserDefined;
    private TurtleManagerView myTurtleManagerView;
    private ColorPaletteView myColorView;
    private ShapePaletteView myShapeView;
    private EntryManager myTerminalManager;
    private EntryManager myCommandManager;
    private EntryManager myWorkspaceManager;
    private EntryManager myTurtleManager;
    private EntryManager myColorManager;
    private EntryManager myShapeManager;
    private LanguageManager myLanguageManager;
    private LanguagePreferences myLanguagePreferences;
    private DisplayPreferences myDisplayPreferences;
    private HTMLopener myHTMLopener;   
    private MultipleTurtles myTurtles;
	private AnimationController myAnimationController;
    
	// Animation Parameters
	private static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	
    public UserInterface (Stage s) {
        init(s);
       
    }

    public void init (Stage s) {
        initModules(s);               
        Tab tab1 = new Tab();
        tab1.setContent(makeGridPane());
    }

    private void initModules (Stage primaryStage) {
        myDisplayPreferences = new DisplayPreferences(primaryStage);
        myTerminalManager = new EntryManager();
        myCommandManager = new EntryManager();
        myWorkspaceManager = new EntryManager();
        myTurtleManager = new EntryManager();
        myColorManager = new EntryManager();
        myShapeManager = new EntryManager();
        myLanguageManager = new LanguageManager();
        myAnimationController = new AnimationController();
        myDisplay = new Display(myDisplayPreferences,myTurtleManager);
        myTurtles = new MultipleTurtles(myDisplayPreferences.getImageProperty(), myTurtleManager, myDisplay.getPane(), myAnimationController);
    	myCommandParser = new CommandParser(myTurtles);
        myCommandLine = new CommandLine(myCommandParser, myTerminalManager, myCommandManager, myWorkspaceManager);
        myTerminal = new TerminalView(myCommandLine, myTerminalManager, sceneResources.getString("TERMINAL"), new String[]{sceneResources.getString("TERMINAL_1"),sceneResources.getString("TERMINAL_2")});
        myWorkspace = new WorkspaceView(myWorkspaceManager, sceneResources.getString("WORKSPACE"), new String[]{sceneResources.getString("WORKSPACE_1"),sceneResources.getString("WORKSPACE_2")});
        myTurtleManagerView = new TurtleManagerView(myTurtleManager, "Active Turtles", new String[]{"ID","Turtle"});
        myUserDefined = new UserDefinedView(myCommandLine,myCommandManager, sceneResources.getString("USERCOMMANDS"), new String[]{sceneResources.getString("USERCOMMANDS_1"), sceneResources.getString("USERCOMMANDS_2")});
        myColorView = new ColorPaletteView(myColorManager, "Color Palette", new String[]{"Index","Color"});
        myShapeView = new ShapePaletteView(myShapeManager, "Palettes", new String[]{"Index","Shape"});
        myLanguagePreferences = new LanguagePreferences(myLanguageManager,myCommandParser);
        myHTMLopener = new HTMLopener();
        
        /* Animation Initialization */
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                e -> myAnimationController.step());
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
      
    }

    private GridPane makeGridPane () {
        myGridPane = new GridPane();
        myGridPane.getStyleClass().add(sceneResources.getString("GRIDPANEID"));
        myGridPane.add(myDisplay.getPane(), 1, 1);
        myGridPane.add(myCommandLine.getTextField(), 1, 2, 1, 6);
        initComponentLists();
        myGridPane.add(makeBox(new HBox(), sceneResources.getString("HBOXID"),myButtonsList,false), 2, 6,3,6);
        myGridPane.add(makeBox(new VBox(), sceneResources.getString("VBOXID"),myFirstColList,true), 2, 1,2,5);
       // myGridPane.add(makeBox(new VBox(), sceneResources.getString("VBOXID"),mySecondColList), 4, 1,4,5);
        return myGridPane;
    }
    
    private void initComponentLists () {        
        myButtonsList = new ArrayList<Node>(Arrays.asList(myCommandLine.getButton(),myDisplayPreferences.getButton(),myLanguagePreferences.getComboBox(),myHTMLopener.getButton()));
        myFirstColList = new ArrayList<Node>(Arrays.asList(makePane(myTerminal.getTitle(),myTerminal.getMyTableView(),true),
                                                           makePane(myWorkspace.getTitle(),myWorkspace.getMyTableView(),true),
                                                           makePane(myUserDefined.getTitle(),myUserDefined.getMyTableView(),false),
                                                           makePane(myTurtleManagerView.getTitle(),myTurtleManagerView.getMyTableView(),false),
                                                           makePane(myShapeView.getTitle(),sideBySideTable(myColorView.getMyTableView(),myShapeView.getMyTableView()),false)));
    }
    
    private Node sideBySideTable (TableView<Entry> myTableView, TableView<Entry> myTableView2) {
        GridPane gp = new GridPane();
        gp.setHgap(10.0);//TODO css
        gp.add(myTableView, 0, 0);
        gp.add(myTableView2, 1, 0);
        return gp;
    }

    private Node makePane(String title, Node content, Boolean isExpanded){
        TitledPane pane = new TitledPane(title,content);
        pane.getStyleClass().add(sceneResources.getString("LABELID"));
        pane.setExpanded(isExpanded);
        pane.setMinWidth(297);//TODO magic numb
        return pane;
    }
    private Node makeBox (Pane box, String cssID, List<Node> items, Boolean scrollable) {        
        Pane myBox = box;
        myBox.getStyleClass().add(cssID); 
        myBox.getChildren().addAll(items);
        if(scrollable){
            ScrollPane scroll = new ScrollPane(myBox);
            scroll.setMaxHeight(510);//TODO magic number
            scroll.setMinWidth(300);//TODO magic number
            return scroll;
        }
        return myBox;
    }
    public GridPane getGridPane(){
        return myGridPane;
    }

}
