package frontend;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;


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
    private void initDisplayPreferences () {// potential issue with Stage s in future 
       prefMenu = new Menu("Preferences");       
       Menu item = new Menu("Set Display Color");
       MenuItem cpItem = new MenuItem();
       cpItem.setGraphic(dispColor);
       item.getItems().add(cpItem);
       prefMenu.getItems().add(item);
       myMenuBar = new MenuBar();
       myMenuBar.getStyleClass().add(sceneResources.getString("BUTTONID"));
    }

    public MenuBar getMenu(){
        return this.myMenuBar;
    }
    public ColorPicker getDispColorPicker () {
        return dispColor;
    }

}
