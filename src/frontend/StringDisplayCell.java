package frontend;

import javafx.scene.control.ListCell;
import javafx.stage.Stage;


public class StringDisplayCell extends ListCell<Entry> {
    public StringDisplayCell (SLOGOScreen screen, Stage s) {
  //      this.setOnMouseClicked(e -> handle(screen,s));
    }

//    private void handle (SLOGOScreen screen, Stage s) {
//        screen.openSavedWorkspace(s, (String) this.getItem().getFirstValue(),((UserInterface) this.getItem().getSecondValue()).getGridPane());
//    }

    @Override
    protected void updateItem (Entry item, boolean empty) {
        super.updateItem(item, empty);
        setText(item == null ? "" : (String) item.getFirstValue());
    }

}
