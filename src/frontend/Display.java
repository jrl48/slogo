package frontend;

import javafx.scene.layout.Pane;

import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class Display {
    private ResourceBundle sceneResources =
            ResourceBundle.getBundle(UserInterface.DEFAULT_RESOURCE_PACKAGE + UserInterface.SCENE);
    private Pane myPane;
    private Group root;
    public static final double WIDTH = 500;
    public static final double HEIGHT = 450;

    public Display (DisplayPreferences dispPref, EntryManager turtleManager) {
        initPane(dispPref);
    }

    private void initPane (DisplayPreferences dispPref) {
        root = new Group();
        myPane = new Pane(root);
        myPane.getStyleClass().add(sceneResources.getString("DISPLAYID"));
        myPane.setPrefSize(WIDTH, HEIGHT);
        setPaneBinding(myPane, dispPref.getDispColorPicker());
    }

    private void setPaneBinding (Pane pane, ColorPicker cp) {
        ObjectProperty<Background> back = pane.backgroundProperty();
        back.bind(Bindings.createObjectBinding( () -> {
            BackgroundFill fill =
                    new BackgroundFill(cp.getValue(), CornerRadii.EMPTY, Insets.EMPTY);
            return new Background(fill);
        } , cp.valueProperty()));
    }

    public void setBackgroundColor(Color col){
        myPane.setBackground(new Background(new BackgroundFill(col,CornerRadii.EMPTY,Insets.EMPTY)));
    }
    public Pane getPane () {
        return myPane;
    }


}
