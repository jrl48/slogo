package frontend;

import javafx.scene.Node;
import javafx.scene.control.TableCell;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class ImageDisplayCell extends TableCell<Entry, Object> {
    private static final double WIDTH = 30;
    private static final double HEIGHT = 30;

    public ImageDisplayCell () {
        super();
    }

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
