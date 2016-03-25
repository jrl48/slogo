// This entire file is part of my masterpiece.
// Joe Lilien
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

    private List<Entry> myDefaults = new ArrayList<>();
    private int myColorColInd = 1;
    private ResourceBundle myDefaultResources =
            ResourceBundle.getBundle(UserInterface.DEFAULT_RESOURCE_PACKAGE + "ColorDefaults");

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
        generateDefaultsList(myDefaultResources);
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
     * representation of the Color they hold. Note: although cast is unchecked, this is the only
     * location in which the customized color display cells are to be used, so this method is not at
     * risk of causing any errors
     */
    @Override
    @SuppressWarnings("unchecked")
    protected void setCustomCells () {
        ((TableColumn<Entry, Color>) getMyTableView().getColumns().get(myColorColInd))
                .setCellFactory(c -> new ColorDisplayCell());
    }

    /**
     * Implementation of abstract method, uses reflection to generate a new StringObjectEntry based
     * on String arguments and add it to myDefaults List
     */
    @Override
    protected void addToDefaultsList (String index, String value) {
        try {
            myDefaults.add(new StringObjectEntry(index,
                                                 (Color) Color.class.getField(value).get(null)));
        }
        catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException
                | SecurityException e) {
            ErrorMessage err =
                    new ErrorMessage(super.getSceneResources().getString("INVALID_DEFAULT"));
            err.showError();
        }
    }

}
