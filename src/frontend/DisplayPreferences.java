package frontend;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class DisplayPreferences {
    private Button myDisplayButton;
    private Stage prefStage;
    private Scene prefScene;
    private VBox myVBox;
    private ColorPicker dispColor = new ColorPicker(Color.WHITE);
    private ColorPicker penColor = new ColorPicker(Color.WHITE);
    private CheckBox penVisibility = new CheckBox("Show Pen");
    private SimpleBooleanProperty isPenVisible = new SimpleBooleanProperty(true);
    
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
        prefStage.setScene(prefScene);
        isPenVisible.bind(penVisibility.pressedProperty());
        myDisplayButton = new Button("Preferences"); // TODO add resource file
        myDisplayButton.setOnAction(e -> openPreferences());
    }

    private void addOptions (VBox box) {
        box.getChildren().add(new Label("Choose Display Color")); //TODO resource file
        box.getChildren().add(dispColor);//TODO resource file
        box.getChildren().add(new Label("Choose Pen Color"));
        box.getChildren().add(penColor);
        box.getChildren().add(penVisibility);  
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

    private void openPreferences () {
        prefStage.showAndWait();
    }

    public Button getButton () {
        return myDisplayButton;
    }
}
