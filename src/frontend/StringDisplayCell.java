package frontend;

import javafx.scene.control.ListCell;

public class StringDisplayCell extends ListCell<Entry> {
    public StringDisplayCell(){
    }
    
    @Override 
    protected void updateItem(Entry item, boolean empty) {
        super.updateItem(item, empty);      
        setText(item == null ? "" : (String) item.getFirstValue());      
    }

}
