package frontend;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * This class contains the pane that opens up when the person wants to view and tweak any
 * of the turtles' parameters. 
 * 
 * @author Alan
 *
 */
public class TurtlePreferences {
    private ResourceBundle prefResources =
            ResourceBundle.getBundle(UserInterface.DEFAULT_RESOURCE_PACKAGE + "Pref");     
    private Button myDisplayButton;
    private ColorPicker penColor = new ColorPicker(Color.BLACK);
    private ObjectProperty<Image> imageProperty =
            new SimpleObjectProperty<Image>(new Image(getClass().getClassLoader()
                    .getResourceAsStream(UserInterface.DEFAULT_RESOURCE_PACKAGE + "turtle.png")));
    private Button chooseImage = new Button(prefResources.getString("IMAGE_CHOICE_TITLE"));  
    private ContextMenu prefWindow;
    private List<Menu> myOptions = new ArrayList<Menu>(Arrays.asList(new Menu("Turtle Image"), new Menu("PenProperties")));
    private ArrayList<MenuItem> turtleImage = new ArrayList<MenuItem>(Arrays.asList(new CustomMenuItem(chooseImage)));
    private ArrayList<MenuItem> penProperties = new ArrayList<MenuItem>(Arrays.asList(new CustomMenuItem(new Button("add stuff here"))));
    private List<ArrayList<MenuItem>> myControls = new ArrayList<ArrayList<MenuItem>>(Arrays.asList(turtleImage,penProperties));
    
    public TurtlePreferences () {
        initDisplayPreferences();
    }
    
    private void initDisplayPreferences () {
        prefWindow = new ContextMenu();
        initFileChooser(new FileChooser());  
        addPenColorPicker();
        initOptions();
        prefWindow.getItems().addAll(myOptions);
    }
    
    //Need to do this separately because of bug in Java FX color picker
    private void addPenColorPicker () {
        MenuItem cp = new MenuItem();
        cp.setGraphic(penColor);
        penProperties.add(cp);
    }

    private void initOptions(){
        for(int i=0;i<myOptions.size();i++){
            myOptions.get(i).getItems().addAll(myControls.get(i));
        }
    }

    private void initFileChooser (FileChooser imageChoice) {
        imageChoice.setTitle(prefResources.getString("IMAGE_CHOICE_TITLE")); 
        imageChoice.getExtensionFilters().add(new ExtensionFilter("Image Files","*.png","*.jpg","*.gif"));
        chooseImage.setOnAction(e->openImageChoice(imageChoice));
    }
    /**
     * Open the window.
     * @param owner
     * @param x
     * @param y
     */
    public void openPreferences(Node owner, double x, double y){
        prefWindow.show(owner, x, y);
    }

    /**
     * Lets the user pick an image to show as turtle.
     * Throws exception if not found.
     * @param imageChoice
     */
    private void openImageChoice (FileChooser imageChoice) {
        File file = imageChoice.showOpenDialog(prefWindow.getOwnerWindow());
        if (file != null) {
            try {
                String fileName = file.toURI().toURL().toString();
                imageProperty.setValue(new Image(fileName));
            }
            catch (MalformedURLException e) {
                System.err.println(e.getMessage());
            }

        }
    }

    public double getPenWidth(){
        return 0;
    }
    public double getDashLength(){
        return 0;
    }
    public Color getPenColor () {
        return penColor.getValue();
    }
    public void setPenColor(Color col){
        this.penColor.setValue(col);
    }

    public ObjectProperty<Image> getImageProperty () {
        return imageProperty;
    }   

    public Button getButton () {
        return myDisplayButton;
    }
}
