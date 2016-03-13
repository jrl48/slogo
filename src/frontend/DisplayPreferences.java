package frontend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;


/**
 * The Display preferences class consists of the Menu that gives control to the user over
 * some of the Display's tweaks. Extends Preferences.
 * 
 * @author JoeLilien
 *
 */
public class DisplayPreferences extends Preferences {
    private ResourceBundle prefResources =
            ResourceBundle.getBundle(UserInterface.DEFAULT_RESOURCE_PACKAGE + "Pref");
    private ResourceBundle sceneResources =
            ResourceBundle.getBundle(UserInterface.DEFAULT_RESOURCE_PACKAGE + UserInterface.SCENE);
    private ColorPicker dispColor = new ColorPicker(Color.WHITE);
    private Menu prefMenu = new Menu(prefResources.getString("PREFERENCES_TITLE"));
    private MenuBar myMenuBar = new MenuBar();

    // List of Menu Options in Menu
    private List<Menu> myOptions =
            new ArrayList<>(Arrays
                    .asList(new Menu(prefResources.getString("DISPLAY_CHOICE_LABEL"))));

    // Lists of Options Specific to Pen Properties Settings (need both because of bug inherit in
    // javafx.customCell)
    private ArrayList<MenuItem> displaySubOptions = new ArrayList<>(Arrays.asList());
    private List<Node> displayOptionNodes = new ArrayList<>(Arrays.asList(dispColor));

    // Combined List of All SubOptions Lists
    private List<ArrayList<MenuItem>> myControls =
            new ArrayList<>(Arrays.asList(displaySubOptions));

    public DisplayPreferences () {
        initDisplayPreferences();
        super.addMoreOptions(displayOptionNodes, displaySubOptions);
        super.initOptions(myOptions, myControls);
    }

    /**
     * Sets Background color of display to that chosen by user, throws an error if the user tries to
     * enter and undefined palette index
     * 
     * @param col
     */
    public void setDisplayColor (Color col) {
        if (col != null) {
            dispColor.setValue(col);
        }
        else {
            ErrorMessage err = new ErrorMessage(prefResources.getString("UNDEFINED_PALETTE_ERR"));
            err.showError();
        }
    }
    
    private void initDisplayPreferences () {
        prefMenu.getItems().addAll(myOptions);
        myMenuBar = new MenuBar();
        myMenuBar.getStyleClass().add(sceneResources.getString("BUTTONID"));
    }

    public MenuBar getMenu () {
        return this.myMenuBar;
    }

    public ColorPicker getDispColorPicker () {
        return dispColor;
    }

}
