package frontend;

import frontend.turtle.Turtle;
import javafx.scene.image.ImageView;

public class TurtleDisplayCell extends ImageDisplayCell{
    
    @Override
    protected void handleUpdate(ImageView image, Object obj) {
        Turtle turtle = (Turtle) obj;
        image.imageProperty().bindBidirectional(turtle.getBody().imageProperty());
        this.setContextMenu(turtle.getPreferences().getContextMenu());
    }

}
