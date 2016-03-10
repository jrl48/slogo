package frontend;

import javafx.beans.property.ObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class MultipleTurtles {
    private EntryManager turtleManager;
    private Pane myDisplayPane;
    
    public MultipleTurtles (ObjectProperty<Image> imageProperty, EntryManager turtleManager, Pane displayPane) {
        this.turtleManager = turtleManager;
        this.myDisplayPane = displayPane;
        Turtle turtleOne = new SingleTurtle(imageProperty);
        turtleManager.addEntry(new StringObjectEntry("Turtle 1", turtleOne), false);
    }
}
