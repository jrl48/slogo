package frontend;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.paint.Color;

/**
 * View of the pane that will manage all active and non-active turtles in the simulation.
 * @author JoeLilien
 *
 */
public class TurtleManagerView extends ModuleView{
    private static final double WIDTH = 250;
    private static final double HEIGHT = 150;
    
    public TurtleManagerView (EntryManager manager, String labelTitle, String[] colTitles) {
        super(manager, labelTitle, colTitles);
        setCustomCells();
    }
    
    @SuppressWarnings("unchecked")
    private void setCustomCells () {
        ((TableColumn<Entry,Object>) getMyTableView().getColumns().get(1)).setCellFactory(c->new ImageDisplayCell());
    }

    @Override
    protected void setSizing (TableView<Entry> table) {
        table.setPrefSize(WIDTH, HEIGHT);
        
    }

}
