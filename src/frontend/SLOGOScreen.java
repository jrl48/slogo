package frontend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * Main class that controls stage and all objects at play during program Runtime. Also controls
 * functionality for saving and creating additional workspaces.
 * 
 * **Note: This class is not meant to be extended very much, if changes are desired in the
 * user interface appearance or functionality, they should most likely be made in the UserInterface
 * class**
 * 
 * @author JoeLilien
 *
 */
public class SLOGOScreen {
    public static final double WIDTH = 880;
    public static final double HEIGHT = 640;
    private static final double HBOX_X = WIDTH - 245;
    private static final double HBOX_Y = 30;
    private Scene myScene;
    private TabPane myTabPane;
    private Group root;
    private EntryManager mySavedManager = new EntryManager();
    private ComboBox<Entry> mySavedWorkspaces = new ComboBox<Entry>(mySavedManager.getEntryList());
    private ResourceBundle sceneResources =
            ResourceBundle.getBundle(UserInterface.DEFAULT_RESOURCE_PACKAGE + UserInterface.SCENE);
    private Button newWorkspace = new Button(sceneResources.getString("NEWBUTTON"));
    private Button saveWorkspace = new Button(sceneResources.getString("SAVEBUTTON"));
    private HBox myControls = new HBox();
    private List<GridPane> myUIPaneList = new ArrayList<GridPane>();
    private String buttonID = "BUTTONID";

    // List of controls to be added to scene to allow saving functionality
    private List<Node> mySaveControls =
            new ArrayList<Node>(Arrays.asList(saveWorkspace, mySavedWorkspaces, newWorkspace));

    public void init (Stage s) {
        s.setTitle(sceneResources.getString("TITLE"));
        s.setResizable(false);
        root = new Group();
        myScene = new Scene(root, WIDTH, HEIGHT, Color.SKYBLUE);
        myScene.getStylesheets()
                .add(UserInterface.DEFAULT_RESOURCE_PACKAGE + UserInterface.STYLESHEET);
        initTabPane();
        initHBox();
        initComboBox(s);
        initButtons();
        addUserInterface(sceneResources.getString("WORKSPACE"), false, null);
        s.setScene(myScene);
    }

    private void initTabPane () {
        myTabPane = new TabPane();
        myTabPane.setMaxWidth(WIDTH);
        root.getChildren().add(myTabPane);
    }

    /**
     * Initializes combobox and sets functionality
     * 
     * **Note: slight bug here, combobox setOnAction relies on a change in selection, so if only one
     * UI is saved, it will only be able to opened once, this should have been fixed but for a bug
     * inherent in the javafx combobox selection model**
     * 
     * @param s
     */
    private void initComboBox (Stage s) {
        mySavedWorkspaces.setPromptText(sceneResources.getString("SAVEBOX"));
        mySavedWorkspaces.getStyleClass().add(sceneResources.getString(buttonID));
        mySavedWorkspaces.setConverter(new ModifiedStringConverter());
        mySavedWorkspaces.setCellFactory(c -> new StringDisplayCell());
        mySavedWorkspaces
                .setOnAction(e -> openSavedWorkspace(s,
                                                     (String) mySavedWorkspaces.getSelectionModel()
                                                             .getSelectedItem().getFirstValue(),
                                                     (GridPane) mySavedWorkspaces
                                                             .getSelectionModel().getSelectedItem()
                                                             .getSecondValue()));
    }

    /**
     * Opens desired saved workspace, called upon action input in combobox
     * 
     * @param s
     * @param title
     * @param UIPane
     */
    public void openSavedWorkspace (Stage s, String title, GridPane UIPane) {
        if (myUIPaneList.contains(UIPane)) {
            myTabPane.getSelectionModel().select(myUIPaneList.indexOf(UIPane));
        }
        else {
            addUserInterface(title, true, UIPane);
        }
    }

    /**
     * Creation of new user interface
     * 
     * @param title
     * @param s
     * @param isClosable
     * @param UIPane
     */
    private void addUserInterface (String title, Boolean isClosable, GridPane UIPane) {
        if (UIPane == null) {
            UserInterface UI = new UserInterface();
            UIPane = UI.getGridPane();
            title = title + " " + (myTabPane.getTabs().size() + 1);
        }
        myUIPaneList.add(UIPane);
        Tab t = new Tab(title);
        t.setClosable(isClosable);
        t.setContent(UIPane);
        t.setOnCloseRequest(e -> {
            myUIPaneList.remove(myTabPane.getSelectionModel().getSelectedIndex());
        });
        myTabPane.getTabs().add(t);
        myTabPane.getSelectionModel().select(t);
    }

    /**
     * Sets action response for New and Save Workspace buttons. Admittedly, this is not very
     * extendable, but it is not meant to be, as additional functionality should be added in
     * UserInterface class
     * 
     * @param s
     */
    private void initButtons () {
        newWorkspace.getStyleClass().add(sceneResources.getString(buttonID));
        newWorkspace.setOnAction(e -> addUserInterface(sceneResources.getString("WORKSPACE"),
                                                       true, null));
        saveWorkspace.getStyleClass().add(sceneResources.getString(buttonID));
        saveWorkspace.setOnAction(e -> saveUserInterface((GridPane) myTabPane.getSelectionModel()
                .getSelectedItem().getContent()));

    }

    private void saveUserInterface (GridPane UIPane) {
        String title = promptforTitle();
        if (title == null) {
            return;
        }
        myTabPane.getSelectionModel().getSelectedItem().setText(title);
        mySavedManager.addEntry(new StringObjectEntry(title, UIPane), true);
    }

    /**
     * Opens a textpromt to ask user for a title for the newly saved UI, initalizes with default
     * value of "Saved Workspace #"
     * 
     * 
     * 
     * @return
     */
    private String promptforTitle () {
        TextPrompt myPrompt =
                new TextPrompt(sceneResources.getString("SAVE") +
                               (mySavedManager.getEntryList().size() + 1),
                               sceneResources.getString("PROMPT"));
        myPrompt.show();
        if (myPrompt.getText() == null) {
            return null;
        }
        else if (myPrompt.getText().equals("")) {
            return myTabPane.getSelectionModel().getSelectedItem().getText();
        }
        return myPrompt.getText();
    }

    private void initHBox () {
        myControls = new HBox();
        myControls.setLayoutX(HBOX_X);
        myControls.setLayoutY(HBOX_Y);
        myControls.toFront();
        myControls.getStyleClass().add(sceneResources.getString("HBOXID"));
        myControls.getChildren().addAll(mySaveControls);
        root.getChildren().add(myControls);
    }
}
