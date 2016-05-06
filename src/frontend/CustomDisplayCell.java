package frontend;

import javafx.scene.control.TableCell;


public abstract class CustomDisplayCell extends TableCell<Entry, Object> {

    public CustomDisplayCell () {
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
        handleUpdate(obj);
    }

    protected abstract void handleUpdate (Object obj);

}
