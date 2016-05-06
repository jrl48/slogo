package frontend;

import frontend.turtle.Turtle;
import javafx.scene.control.TableCell;
import javafx.scene.image.ImageView;


/**
 * Custom cell set to display a visual representation of the ImageView object that is its value, to
 * be used in ShapePaletteView's TableView
 * 
 * @author JoeLilien
 *
 */
public class ImageDisplayCell extends CustomDisplayCell {
    private static final double WIDTH = 30;
    private static final double HEIGHT = 30;

    public ImageDisplayCell () {
        super();
    }
    
    @Override
    protected void handleUpdate (Object obj) {
        ImageView turtle = new ImageView();
        handleUpdate(turtle, obj);
        turtle.setFitWidth(WIDTH);
        turtle.setFitHeight(HEIGHT);
        setGraphic(turtle);        
    }

    protected void handleUpdate (ImageView image, Object obj) {   
        image = (ImageView) obj;
    }
    

}
