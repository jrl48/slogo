// This entire file is part of my masterpiece.
// Joe Lilien
package frontend;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * Defines style, general structure, and shared methods for all table objects in interface. This
 * class is to be extended any time a new table is to be added to the interface, and additional case
 * specific functionality can be added to the subclass
 * 
 * @author JoeLilien
 *
 *         I wanted to include this hierarchy in my code masterpiece because it exemplifies an
 *         extremely important contributor to my program's frontend flexibility. Most analysis of
 *         this hierarchy is covered in the written portion of the Analysis, but an important update
 *         to note is that the default colors for the ColorPaletteView (included in masterpiece)
 *         are now read in from a resource properties file, using reflection. This allows for easy
 *         extension without any violation of the open closed policy. The resource file used is
 *         ColorDefaults.properties.
 *
 */
public abstract class ModuleView {

    private static final double WIDTH = 300;
    private ResourceBundle mySceneResources =
            ResourceBundle.getBundle(UserInterface.DEFAULT_RESOURCE_PACKAGE + UserInterface.SCENE);
    private String[] myEntryValues = new String[] { mySceneResources.getString("FIRSTVALUE"),
                                                    mySceneResources.getString("SECONDVALUE") };
    private TableView<Entry> myTableView;
    private String myTitle;

    /**
     * Constructor for module view, generates a TableView item with number of columns equal to the
     * number of items in the colTitles array
     * 
     * @param manager
     * @param title
     * @param colTitles
     */
    public ModuleView (EntryManager manager, String title, String[] colTitles) {
        initTable(manager, colTitles);
        this.myTitle = title;
    }

    /**
     * Initialize tableView object according to parameters
     * 
     * @param manager
     * @param colTitles
     */
    private void initTable (EntryManager manager, String[] colTitles) {
        myTableView = new TableView<>();
        List<TableColumn<Entry, Object>> columns = makeColumns(colTitles, myEntryValues);
        myTableView.getColumns().addAll(columns);
        myTableView.setItems(manager.getEntryList());
        myTableView.setPrefWidth(WIDTH);
        myTableView.getStyleClass().add(mySceneResources.getString("TABLEVIEWID"));
        setSizing(myTableView);
    }

    /**
     * To be overridden by subclasses according to specific table and column sizing desired. Should
     * set prefHeight and table and prefWidth of columns as desired
     * 
     * @param table
     */
    protected abstract void setSizing (TableView<Entry> table);

    /**
     * Defines a listener on the table view that allows for automatic population of commandLine text
     * area according to user input
     * 
     * @param command
     */
    protected void defineListener (CommandLine command) {
        getMyTableView().getSelectionModel().selectedItemProperty()
                .addListener( (observableValue, oldValue, newValue) -> setCommand(command,
                                                                                  newValue));
    }

    /**
     * Method called on change detected in the TableView, if defineListener method has been called
     * by subclass, sets text of commandLine's text area to that of tableView entry clicked
     * 
     * @param command
     * @param newValue
     */
    private void setCommand (CommandLine command, Entry newValue) {
        if (getMyTableView().getSelectionModel().getSelectedItem() != null) {
            command.getTextField().setText(getClickableString(newValue));
        }
    }

    protected String getClickableString (Entry newValue) {
        return (String) newValue.getFirstValue();
    }

    /**
     * Makes and returns a list of columns to be added to the TableView. Automatically generates
     * correct number and column style according to length of colTitles input and values in both
     * arrays
     * 
     * @param colTitles
     * @param values
     * @return
     */
    private List<TableColumn<Entry, Object>> makeColumns (String[] colTitles, String[] values) {
        List<TableColumn<Entry, Object>> colList = new ArrayList<>();
        for (int i = 0; i < colTitles.length; i++) {
            TableColumn<Entry, Object> col =
                    new TableColumn<>(colTitles[i]);
            col.setCellValueFactory(new PropertyValueFactory<Entry, Object>(values[i]));
            colList.add(col);
        }
        return colList;
    }

    public String getTitle () {
        return myTitle;
    }

    public TableView<Entry> getMyTableView () {
        return myTableView;
    }

    protected ResourceBundle getSceneResources () {
        return this.mySceneResources;
    }

}
