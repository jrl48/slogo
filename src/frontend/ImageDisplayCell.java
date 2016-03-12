package frontend;

import javafx.scene.control.TableCell;
import javafx.scene.image.ImageView;


/**
 * Custom cell set to display a visual representation of the ImageView object that is its value, to
 * be used in ShapePaletteView's TableView
 * 
 * @author JoeLilien
 *
 */
public class ImageDisplayCell extends TableCell<Entry, Object> {
    private static final double WIDTH = 30;
    private static final double HEIGHT = 30;

    public ImageDisplayCell () {
        super();
    }

    /**
     * Overrides updateItem method to display visual representation of ImageView instead of object
     * location in memory. Handles this differently if obj is a Turtle or ImageView, but made more
     * sense to add one if instanceof statement than to write an essentially identical class
     * 
     * @param obj
     * @param empty
     */
    @Override
    protected void updateItem (Object obj, boolean empty) {
        super.updateItem(obj, empty);
        if (empty || obj == null) {
            setText(null);
            setGraphic(null);
            return;
        }
        ImageView turtle = new ImageView();
        if (obj instanceof Turtle) {
            turtle.imageProperty().bind(((Turtle) obj).getBody().imageProperty());
        }
        else {
            turtle = (ImageView) obj;
        }
        turtle.setFitWidth(WIDTH);
        turtle.setFitHeight(HEIGHT);
        setGraphic(turtle);
    }

}
