package frontend;

import java.util.List;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


/**
 * This abstract class is at the second level of the ModuleView Hierarchy. It styles and provides
 * method functionality common to any paletteView object, in this case a ColorPalatte and a
 * ShapePalette. The purpose of this class is to maintain good organization and avoid duplicate
 * code.
 * 
 * @author JoeLilien
 *
 */
public abstract class PaletteView extends ModuleView {
    private static final double WIDTH = 120;
    private static final double HEIGHT = 250;

    /**
     * Constructor for PaletteView, simply takes in same params and performs same task as ModuleView
     * Constructor
     * 
     * @param manager
     * @param title
     * @param colTitles
     */
    protected PaletteView (EntryManager manager, String title, String[] colTitles) {
        super(manager, title, colTitles);
    }

    /**
     * Initializes default values in PaletteView's EntryManager to a given list input, defined by
     * sublcasses
     * 
     * @param defaults
     * @param manager
     */
    protected void initDefaults (List<Entry> defaults, EntryManager manager) {
        manager.addAll(defaults, true);
    }

    /**
     * To be implemented according to needs of each subclass, implementation should set cell
     * factories of desired columns to use custom cell objects
     */
    protected abstract void setCustomCells ();

    /**
     * Sets standard sizing for TableView and individual columns
     * 
     * @param table
     */
    @Override
    protected void setSizing (TableView<Entry> table) {
        table.setMaxWidth(WIDTH);
        table.setPrefHeight(HEIGHT);
        for (TableColumn<Entry, ?> col : table.getColumns()) {
            col.setPrefWidth(WIDTH / 2);
        }
    }

}
