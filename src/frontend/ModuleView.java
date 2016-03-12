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
 */
public abstract class ModuleView {

    private static final double WIDTH = 300;
    private ResourceBundle sceneResources =
            ResourceBundle.getBundle(UserInterface.DEFAULT_RESOURCE_PACKAGE + UserInterface.SCENE);
    private final String[] entryValues = new String[] { sceneResources.getString("FIRSTVALUE"),
                                                        sceneResources.getString("SECONDVALUE") };
    private TableView<Entry> myTableView;
    private String title;

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
        this.title = title;
    }

    /**
     * Initialize tableView object according to parameters
     * 
     * @param manager
     * @param colTitles
     */
    private void initTable (EntryManager manager, String[] colTitles) {
        myTableView = new TableView<Entry>();
        List<TableColumn<Entry, Object>> columns = makeColumns(colTitles, entryValues);
        myTableView.getColumns().addAll(columns);
        myTableView.setItems(manager.getEntryList());
        myTableView.setPrefWidth(WIDTH);
        myTableView.getStyleClass().add(sceneResources.getString("TABLEVIEWID"));
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
        List<TableColumn<Entry, Object>> colList = new ArrayList<TableColumn<Entry, Object>>();
        for (int i = 0; i < colTitles.length; i++) {
            TableColumn<Entry, Object> col =
                    new TableColumn<Entry, Object>(colTitles[i]); 
            col.setCellValueFactory(new PropertyValueFactory<Entry, Object>(values[i]));
            colList.add(col);
        }
        return colList;
    }

    public String getTitle () {
        return title;
    }

    public TableView<Entry> getMyTableView () {
        return myTableView;
    }

}
