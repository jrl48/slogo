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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;


public class DisplayPreferences {
    private static final double WIDTH = 200;
    private static final double HEIGHT = 300;
    private ResourceBundle prefResources =
            ResourceBundle.getBundle(UserInterface.DEFAULT_RESOURCE_PACKAGE + "Pref");   
    private ResourceBundle sceneResources =
            ResourceBundle.getBundle(UserInterface.DEFAULT_RESOURCE_PACKAGE + UserInterface.SCENE);   
    private Button myDisplayButton;
    private ColorPicker dispColor = new ColorPicker(Color.WHITE);
    private ColorPicker penColor = new ColorPicker(Color.BLACK);
    private ObjectProperty<Image> imageProperty =
            new SimpleObjectProperty<Image>(new Image(getClass().getClassLoader()
                    .getResourceAsStream(UserInterface.DEFAULT_RESOURCE_PACKAGE + "turtle.png")));
    private Button chooseImage = new Button(prefResources.getString("IMAGE_CHOICE_TITLE"));
    private List<Node> prefList =
            new ArrayList<Node>(Arrays.asList(chooseImage, new Label(prefResources.getString("DISPLAY_CHOICE_LABEL")),
                                              dispColor, new Label(prefResources.getString("PEN_CHOICE_LABEL")), penColor));

    public DisplayPreferences (Stage primaryStage) {
        initDisplayPreferences(primaryStage);
    }

    private void initDisplayPreferences (Stage primaryStage) {// potential issue with Stage s in future
        Stage prefStage = new Stage();
        prefStage.setTitle(prefResources.getString("PREFERENCES_TITLE"));
        prefStage.setResizable(false);
        Scene prefScene = new Scene(initVBox(new VBox(),prefList), WIDTH, HEIGHT);// TODO figure out how to make color
        prefScene.getStylesheets().add(UserInterface.DEFAULT_RESOURCE_PACKAGE + UserInterface.STYLESHEET);
        initFileChooser(new FileChooser(),primaryStage);              
        prefStage.setScene(prefScene);
        myDisplayButton = new Button(sceneResources.getString("PREFBUTTON")); 
        myDisplayButton.getStyleClass().add(sceneResources.getString("BUTTONID"));
        myDisplayButton.setOnAction(e -> openPreferences(prefStage));
    }

    private void initFileChooser (FileChooser imageChoice, Stage primaryStage) {
        imageChoice.setTitle(prefResources.getString("IMAGE_CHOICE_TITLE")); 
        imageChoice.getExtensionFilters().add(new ExtensionFilter("Image Files","*.png","*.jpg","*.gif"));
        chooseImage.setOnAction(e->openImageChoice(imageChoice,primaryStage));
    }

    private VBox initVBox (VBox box, List<Node> list) {
        box.getStyleClass().add(prefResources.getString("VBOXID"));
        box.getChildren().addAll(list);
        return box;
    }

    private void openImageChoice (FileChooser imageChoice, Stage primaryStage) {
        File file = imageChoice.showOpenDialog(primaryStage);
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

    public ColorPicker getDispColorPicker () {
        return dispColor;
    }

    public ColorPicker getPenColorPicker () {
        return penColor;
    }

    public ObjectProperty<Image> getImageProperty () {
        return imageProperty;
    }

    private void openPreferences (Stage prefStage) {
        if (!prefStage.isShowing()) {
            prefStage.showAndWait();
        }
        else {
            prefStage.toFront();
        }
    }

    public Button getButton () {
        return myDisplayButton;
    }
}
