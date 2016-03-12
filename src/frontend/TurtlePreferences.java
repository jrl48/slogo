package frontend;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;


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
    private TextField penWidth = new TextField("PenWidth");
    private CheckBox penDash = new CheckBox("Dashed Line");
    private CheckBox penActive = new CheckBox("Pen Down");
    private List<Menu> myOptions = new ArrayList<Menu>(Arrays.asList(new Menu("Turtle Image"), new Menu("PenProperties")));
    private ArrayList<MenuItem> turtleImage = new ArrayList<MenuItem>(Arrays.asList(new CustomMenuItem(chooseImage)));
    private ArrayList<MenuItem> penProperties = new ArrayList<MenuItem>(Arrays.asList(new CustomMenuItem(penDash), new CustomMenuItem(penActive)));
    private List<Node> additionalPenProperties = new ArrayList<Node>(Arrays.asList(penWidth,penColor));
    private List<ArrayList<MenuItem>> myControls = new ArrayList<ArrayList<MenuItem>>(Arrays.asList(turtleImage,penProperties));
    
    public TurtlePreferences () {
        initDisplayPreferences();
    }
    
    private void initDisplayPreferences () {// potential issue with Stage s in future
        prefWindow = new ContextMenu();
        initFileChooser(new FileChooser());  
        addMorePenOptions(additionalPenProperties);
        initPenPrefrences();
        initOptions();
        prefWindow.getItems().addAll(myOptions);
    }
    
    
    
    private void initPenPrefrences () {
        penWidth.setOnAction(e->updatePenThickness());
    }

    private void updatePenThickness () {
        try{
            setPenWidth(Double.parseDouble(penWidth.getText()));
        }
        catch(ClassCastException e){
            ErrorMessage err = new ErrorMessage("Legit Value");
            err.showError();
            penWidth.setText(""+1.0);
        }
    }

    //Need to do this separately because of bug in Java FX Custom Cells
    private void addMorePenOptions (List<Node> list) {
        for(Node n : list){
            MenuItem item = new MenuItem();
            item.setGraphic(n);
            penProperties.add(item);
        }
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
    public void openPreferences(Node owner, double x, double y){
        prefWindow.show(owner, x, y);
    }

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
        if(penDash.isSelected()){
            return 10;
        }
        return 0;
    }
    public BooleanProperty isPenActive(){
        return penActive.selectedProperty();
    }
    public void setPenWidth(double width){
        penWidth.setText(""+width);
        updatePenThickness();
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
    
    public void setImageProperty(ImageView image){
        imageProperty.setValue(image.getImage());
    }

    public Button getButton () {
        return myDisplayButton;
    }
}
