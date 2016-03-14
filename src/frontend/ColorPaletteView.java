package frontend;

import javafx.scene.control.TableColumn;
import java.util.*;
import javafx.scene.paint.Color;


/**
 * Bottom Level of ModuleView hierarchy, ColorPaletteView provides additional specifications for
 * tableView and displays all possible Colors that can be set using the Display commands
 * 
 * @author JoeLilien
 *
 */
public class ColorPaletteView extends PaletteView {

    private int colorColInd = 1;
    private List<Entry> myDefaults =
            new ArrayList<>(Arrays.asList(new StringObjectEntry("1", Color.WHITE),
                                               new StringObjectEntry("2", Color.RED),
                                               new StringObjectEntry("3", Color.ORANGE),
                                               new StringObjectEntry("4", Color.YELLOW),
                                               new StringObjectEntry("5", Color.GREEN),
                                               new StringObjectEntry("6", Color.BLUE),
                                               new StringObjectEntry("7", Color.MAGENTA),
                                               new StringObjectEntry("8", Color.BLACK)));

    /**
     * Constructor extends functionality of PaletteView constructor and initializes default values
     * to myDefaults list property, also calls on setCustomCells method that was overriden from
     * superclass and sets action response of table
     * 
     * @param manager
     * @param title
     * @param colTitles
     */
    public ColorPaletteView (EntryManager manager,
                             String title,
                             String[] colTitles,
                             DisplayPreferences display) {
        super(manager, title, colTitles);
        initDefaults(myDefaults, manager);
        setCustomCells();
        defineListener(display);
    }

    /**
     * Sets action response of table to update color of display based on selected row
     * 
     * @param display
     */
    private void defineListener (DisplayPreferences display) {
        getMyTableView().getSelectionModel().selectedItemProperty()
                .addListener( (observableValue, oldValue, newValue) -> updateColor(display,
                                                                                   newValue));
    }

    /**
     * Updates value of color picker that is bound to the fill property of the Display Pane so that
     * it updates automatically when a new color is selected by user input
     * 
     * @param display
     * @param newValue
     */
    private void updateColor (DisplayPreferences display, Entry newValue) {
        if (getMyTableView().getSelectionModel().getSelectedItem() != null) {
            Color col = (Color) newValue.getSecondValue();
            display.setDisplayColor(col);
        }

    }

    /**
     * Sets CellFactory of Shape Column to ColorDisplayCells which will display a visual
     * representation of the Color they hold
     */
    @Override
    @SuppressWarnings("unchecked")
    protected void setCustomCells () {
        ((TableColumn<Entry, Color>) getMyTableView().getColumns().get(colorColInd))
                .setCellFactory(c -> new ColorDisplayCell());
    }

}
