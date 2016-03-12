package frontend;

import java.util.ResourceBundle;
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
public class DisplayPreferences {   
    private ResourceBundle prefResources =
            ResourceBundle.getBundle(UserInterface.DEFAULT_RESOURCE_PACKAGE + "Pref");   
    private ResourceBundle sceneResources =
            ResourceBundle.getBundle(UserInterface.DEFAULT_RESOURCE_PACKAGE + UserInterface.SCENE);      
    private ColorPicker dispColor = new ColorPicker(Color.WHITE);   
    private Menu prefMenu;
    private MenuBar myMenuBar;
    
    public DisplayPreferences () {
        initDisplayPreferences();
    }
    
    public void setDisplayColor(Color col){
        dispColor.setValue(col);
    }
    
    private void initDisplayPreferences () { 
       prefMenu = new Menu("Preferences");       
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
