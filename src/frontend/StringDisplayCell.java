package frontend;

import javafx.scene.control.ListCell;
import javafx.stage.Stage;


public class StringDisplayCell extends ListCell<Entry> {
    public StringDisplayCell (SLOGOScreen screen, Stage s) {
        //TODO fix constructor
    }

    @Override
    protected void updateItem (Entry item, boolean empty) {
        super.updateItem(item, empty);
        setText(item == null ? "" : (String) item.getFirstValue());
    }

}
