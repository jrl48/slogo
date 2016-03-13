package frontend;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


/**
 * View of the pane that will manage all active and non-active turtles in the simulation.
 * 
 * @author JoeLilien
 *
 */
public class TurtleManagerView extends ModuleView {
    private static final double WIDTH = 250;
    private static final double HEIGHT = 150;
    private static final double ID_WIDTH = 215;
    private int idInd = 0;

    public TurtleManagerView (EntryManager manager, String labelTitle, String[] colTitles) {
        super(manager, labelTitle, colTitles);
        setCustomCells();
    }

    /**
     * Sets Cells to display imageview of turtle, rather than string representation of it
     */
    @SuppressWarnings("unchecked")
    private void setCustomCells () {
        ((TableColumn<Entry, Object>) getMyTableView().getColumns().get(1))
                .setCellFactory(c -> new ImageDisplayCell());
    }

    @Override
    protected void setSizing (TableView<Entry> table) {
        table.setPrefSize(WIDTH, HEIGHT);
        table.getColumns().get(idInd).setMinWidth(ID_WIDTH);
    }

}
