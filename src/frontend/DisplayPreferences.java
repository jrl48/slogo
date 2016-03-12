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
 * The Display preferences class consists of the Pane that gives control to the user over
 * some of the Display's tweaks.
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
    private List<MenuItem> items = new ArrayList<MenuItem>(Arrays.asList(new Menu(prefResources.getString("DISPLAY_CHOICE_LABEL"))));
    private List<Node> choices = new ArrayList<Node>(Arrays.asList(dispColor));
    private MenuBar myMenuBar = new MenuBar(prefMenu);
    
    public DisplayPreferences () {
        initDisplayPreferences();
    }
    

    public void setDisplayColor(Color col){
    	if(col != null){
            dispColor.setValue(col);	
    	}
    }
    
    private void initDisplayPreferences () { 
       Menu item = new Menu("Set Display Color");
       MenuItem cpItem = new MenuItem();
       cpItem.setGraphic(dispColor);
       item.getItems().add(cpItem);
       prefMenu.getItems().add(item);
       myMenuBar = new MenuBar(prefMenu);
       myMenuBar.getStyleClass().add(sceneResources.getString("BUTTONID"));
    }

    public MenuBar getMenu(){
        return this.myMenuBar;
    }
    public ColorPicker getDispColorPicker () {
        return dispColor;
    }

}
