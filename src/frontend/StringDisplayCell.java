package frontend;

import javafx.scene.control.ListCell;
import javafx.stage.Stage;

/**
 * Table cell, that holds an entry
 * @author JoeLilien
 *
 */
public class StringDisplayCell extends ListCell<Entry> {

    @Override
    protected void updateItem (Entry item, boolean empty) {
        super.updateItem(item, empty);
        setText(item == null ? "" : (String) item.getFirstValue());
    }

}
