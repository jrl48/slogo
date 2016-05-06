package frontend;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


/**
 * PART OF ADDITION
 * 
 * Added view that keeps track of turtle Stats
 * 
 * @author JoeLilien
 *
 */
public class TurtleInformationView extends ModuleView {
    private static final double WIDTH = 250;
    private static final double HEIGHT = 150;
    private int turtleCol = 1;

    public TurtleInformationView (EntryManager manager, String labelTitle, String[] colTitles) {
        super(manager, labelTitle, colTitles);
        setCustomCells();
    }

    /**
     * Sets Cells to display imageview of turtle, rather than string representation of it
     */
    @SuppressWarnings("unchecked")
    private void setCustomCells () {
        ((TableColumn<Entry, Object>) getMyTableView().getColumns().get(turtleCol))
                .setCellFactory(c -> new TurtleInfoCell());
    }

    @Override
    protected void setSizing (TableView<Entry> table) {
        table.setPrefSize(WIDTH, HEIGHT);
    }

}
