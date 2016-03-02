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
    private ColorPicker penColor = new ColorPicker(Color.BLACK);
    private ObjectProperty<Image> imageProperty = new SimpleObjectProperty<Image>(new Image(getClass().getClassLoader().getResourceAsStream(UserInterface.DEFAULT_RESOURCE_PACKAGE+"turtle.png")));
    private Button chooseImage = new Button("Set Turtle Image");
    
    public DisplayPreferences (Stage s) {
        initDisplayPreferences(s);
    }

    private void initDisplayPreferences (Stage s) {// potential issue with Stage s in future
        prefStage = new Stage(); // TODO add to css
        prefStage.setTitle("Preferences");
        prefStage.setResizable(false);
        myVBox = new VBox();
        myVBox.setAlignment(Pos.CENTER);
        addOptions(myVBox);
        prefScene = new Scene(myVBox, 200, 300, Color.BLACK);// TODO figure out how to make color
                                                             // show  // also magic numbers
        chooseImage.setOnAction(e->openImageChoice(s));
        
        prefStage.setScene(prefScene);
        imageChoice.setTitle("Set Turtle Image"); //TODO add resource file maybe CSS
        imageChoice.getExtensionFilters().add(new ExtensionFilter("Image Files","*.png","*.jpg","*.gif"));
        myDisplayButton = new Button("Preferences"); // TODO add resource file
        myDisplayButton.setOnAction(e -> openPreferences());
    }

    private void addOptions (VBox box) {        
        box.getChildren().add(chooseImage);
        box.getChildren().add(new Label("Choose Display Color")); //TODO resource file
        box.getChildren().add(dispColor);//TODO resource file
        box.getChildren().add(new Label("Choose Pen Color"));
        box.getChildren().add(penColor);
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
        if(!prefStage.isShowing()){
            prefStage.showAndWait();
        }
        else{
            prefStage.toFront();
        }
    }

    public Button getButton () {
        return myDisplayButton;
    }
}
