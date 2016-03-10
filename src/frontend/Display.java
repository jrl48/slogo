package frontend;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
// public class Display {
// private Pane myPane;
// private static final double WIDTH = 450;
// private static final double HEIGHT = 450;
import javafx.scene.shape.Line;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


public class Display {
    private ResourceBundle sceneResources =
            ResourceBundle.getBundle(UserInterface.DEFAULT_RESOURCE_PACKAGE + UserInterface.SCENE);
    private Pane myPane;
    private Group root;
    private static final double WIDTH = 500;
    private static final double HEIGHT = 450;
    private MultipleTurtles myTurtle;


    public Display (DisplayPreferences dispPref, EntryManager turtleManager) {
        initPane(dispPref);
    }

    private void initPane (DisplayPreferences dispPref) {
        root = new Group();
        myPane = new Pane(root);
        myPane.getStyleClass().add(sceneResources.getString("DISPLAYID"));
        myPane.setPrefSize(WIDTH, HEIGHT);
        setPaneBinding(myPane, dispPref.getDispColorPicker());

        // Build turtle and initialize it at Logo's (0,0)

    }

    private void setPaneBinding (Pane pane, ColorPicker cp) {
        ObjectProperty<Background> back = pane.backgroundProperty();
        back.bind(Bindings.createObjectBinding( () -> {
            BackgroundFill fill =
                    new BackgroundFill(cp.getValue(), CornerRadii.EMPTY, Insets.EMPTY);
            return new Background(fill);
        } , cp.valueProperty()));
    }

    public Pane getPane () {
        return myPane;
    }


}
