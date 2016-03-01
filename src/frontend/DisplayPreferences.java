package frontend;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class DisplayPreferences {
    private Button myDisplayButton;
    private Stage prefStage;
    private Scene prefScene;
    private VBox myVBox;
    private List<Node> prefList = new ArrayList<Node>();// TODO work this in
    private FileChooser imageChoice = new FileChooser();
    private ColorPicker dispColor = new ColorPicker(Color.WHITE);
    private ColorPicker penColor = new ColorPicker(Color.WHITE);
    private ObjectProperty<Image> imageProperty = new SimpleObjectProperty<Image>(new Image(getClass().getClassLoader().getResourceAsStream(UserInterface.DEFAULT_RESOURCE_PACKAGE+"turtle.png")));
    private CheckBox penVisibility = new CheckBox("Show Pen");
    private SimpleBooleanProperty isPenVisible = new SimpleBooleanProperty(true);
    private Button chooseImage = new Button("Set Turtle Image");
    
    public DisplayPreferences (Stage s) {
        initDisplayPreferences(s);
    }

    private void initDisplayPreferences (Stage s) {
        prefStage = new Stage(); // TODO add to css
        prefStage.setTitle("Preferences");
        prefStage.setResizable(false);
        prefStage.initModality(Modality.WINDOW_MODAL);
        prefStage.initOwner(s);
        myVBox = new VBox();
        myVBox.setAlignment(Pos.CENTER);
        addOptions(myVBox);
        prefScene = new Scene(myVBox, 200, 300, Color.BLACK);// TODO figure out how to make color
                                                             // show
        chooseImage.setOnAction(e->openImageChoice(s));
        
        prefStage.setScene(prefScene);
        imageChoice.setTitle("Set Turtle Image"); //TODO add resource file maybe CSS
        imageChoice.getExtensionFilters().add(new ExtensionFilter("Image Files","*.png","*.jpg","*.gif"));
        isPenVisible.bind(penVisibility.pressedProperty());
        myDisplayButton = new Button("Preferences"); // TODO add resource file
        myDisplayButton.setOnAction(e -> openPreferences());
    }

    private void addOptions (VBox box) {        
        box.getChildren().add(chooseImage);
        box.getChildren().add(new Label("Choose Display Color")); //TODO resource file
        box.getChildren().add(dispColor);//TODO resource file
        box.getChildren().add(new Label("Choose Pen Color"));
        box.getChildren().add(penColor);
        box.getChildren().add(penVisibility);  
    }
    private void openImageChoice(Stage s){
        File file = imageChoice.showOpenDialog(s);
        if (file != null) {
            try{
            String fileName = file.toURI().toURL().toString();            
            imageProperty.setValue(new Image(fileName));
            }
            catch(MalformedURLException e){
                e.printStackTrace();  //TODO: make this fix
            }
            
        }
    }
    
    public SimpleBooleanProperty getPenVisibility(){
        return isPenVisible;
    }
    public ColorPicker getDispColorPicker(){
        return dispColor;
    }
    public ColorPicker getPenColorPicker(){
        return penColor;
    }

    public ObjectProperty<Image> getImageProperty(){
        return imageProperty;
    }
    private void openPreferences () {
        prefStage.showAndWait();
    }

    public Button getButton () {
        return myDisplayButton;
    }
}
