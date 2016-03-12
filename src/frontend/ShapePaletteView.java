package frontend;

import javafx.scene.control.TableColumn;
import java.util.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * Bottom Level of ModuleView hierarchy, ShapePaletteView provides additional specifications for
 * tableView and displays all possible turtle shapes that can be set using the setShape command
 * 
 * @author JoeLilien
 *
 */
public class ShapePaletteView extends PaletteView {
    private int shapeColInd = 1;
    private List<Entry> myDefaults =
            new ArrayList<Entry>(Arrays
                    .asList(new StringObjectEntry("1", new ImageView(getImage("turtle.png"))),
                            new StringObjectEntry("2", new ImageView(getImage("turtleTest.png"))),
                            new StringObjectEntry("3", new ImageView(getImage("turtle_gif.gif"))),
                            new StringObjectEntry("4", new ImageView(getImage("red_square.jpg"))),
                            new StringObjectEntry("5", new ImageView(getImage("green_circle.png"))),
                            new StringObjectEntry("6", new ImageView(getImage("blue_triangle.png")))));

    /**
     * Constructor extends functionality of PaletteView constructor and initializes default values
     * to myDefaults list property, also calls on setCustomCells method that was overriden from
     * superclass
     * 
     * @param manager
     * @param title
     * @param colTitles
     */
    public ShapePaletteView (EntryManager manager, String title, String[] colTitles) {
        super(manager, title, colTitles);
        initDefaults(myDefaults, manager);
        setCustomCells();
    }

    /**
     * Returns a new image according the specified filename extension, method included for
     * readability of code
     * 
     * @param filename
     * @return
     */
    private Image getImage (String filename) {
        return new Image(getClass().getClassLoader()
                .getResourceAsStream(UserInterface.DEFAULT_RESOURCE_PACKAGE + filename));
    }

    /**
     * Sets CellFactory of Shape Column to ImageDisplayCells which will display a visual
     * representation of the image they hold
     */
    @Override
    @SuppressWarnings("unchecked")
    protected void setCustomCells () {
        ((TableColumn<Entry, Object>) getMyTableView().getColumns().get(shapeColInd))
                .setCellFactory(c -> new ImageDisplayCell());
    }

}
