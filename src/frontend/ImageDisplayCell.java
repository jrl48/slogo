package frontend;

import javafx.scene.Node;
import javafx.scene.control.TableCell;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ImageDisplayCell extends TableCell<Entry,Object>{
    public ImageDisplayCell(){
        super();
    }

    @Override
    protected void updateItem(Object obj, boolean empty){
        super.updateItem(obj, empty);
        if (empty || obj == null) {
            setText(null);
            setGraphic(null);
            return;
        }
        if (obj instanceof Turtle){
            ImageView turtle = new ImageView(((Turtle) obj).getBody().getImage());
            setGraphic(turtle);
        }
        else{
            setGraphic((ImageView) obj);
        }
    }

}
