package frontend;

import javafx.scene.control.TableCell;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 * Custom cell set to display a visual representation of the Color object that is its value, to be
 * used in ColorPaletteView's TableView
 * 
 * @author JoeLilien
 *
 */
public class ColorDisplayCell extends TableCell<Entry, Color> {
    private static final double SIZE = 8;

    public ColorDisplayCell () {
        super();
    }

    /**
     * Overrides updateItem method to display visual representation of color instead of object
     * location in memory
     * 
     * @param col
     * @param empty
     */
    @Override
    protected void updateItem (Color col, boolean empty) {
        super.updateItem(col, empty);
        if (empty || col == null) {
            setText(null);
            setGraphic(null);
            return;
        }
        setText(null);
        this.setTooltip(new Tooltip(" [r=" + (int) (col.getRed() * 255) + ",b=" +
                                    (int) (col.getBlue() * 255) + ",g=" +
                                    (int) (col.getGreen() * 255) + "]"));
        Rectangle colRect = new Rectangle(SIZE, SIZE, col);
        setGraphic(colRect);
    }
}
