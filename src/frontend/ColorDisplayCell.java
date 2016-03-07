package frontend;

import javafx.scene.control.TableCell;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ColorDisplayCell extends TableCell<Entry,Color>{
    public ColorDisplayCell(){
        super();
    }

    @Override
    protected void updateItem(Color col, boolean empty){
        super.updateItem(col, empty);
        if (empty || col == null) {
            setText(null);
            setGraphic(null);
            return;
        }
        setText(null);
        this.setTooltip(new Tooltip(" [r="+(int)(col.getRed()*255)+",b="+(int)(col.getBlue()*255)+",g="+(int)(col.getGreen()*255)+"]"));
        Rectangle colRect = new Rectangle(8.0, 8.0, col);//TODO magic number
        setGraphic(colRect);
    }
}
