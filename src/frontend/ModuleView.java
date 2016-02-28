package frontend;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public abstract class ModuleView {

    private static final String[] entryValues = new String[] { "FirstValue", "SecondValue" };
    private Label myLabel;
    private TableView<Entry> myTableView;
    private List<TableColumn<Entry, Object>> myColumnsList;

    public ModuleView (EntryManager manager, String labelTitle, String[] colTitles) {
        initTable(manager, colTitles);
        initLabel(labelTitle);
    }

    private void initTable (EntryManager manager, String[] colTitles) {
        myTableView = new TableView<Entry>();
        List<TableColumn<Entry, Object>> columns = makeColumns(colTitles, entryValues);// TODO add
                                                                                       // resource
                                                                                       // file
        myTableView.getColumns().addAll(columns);
        myTableView.setItems(manager.getEntryList());
        myTableView.getStyleClass().add("TABLEVIEWID"); // TODO deal with this
        setSizing(columns);
    }

    protected abstract void setSizing (List<TableColumn<Entry, Object>> columns); // TODO implement
                                                                                  // this method

    private List<TableColumn<Entry, Object>> makeColumns (String[] colTitles, String[] values) {
        myColumnsList = new ArrayList<TableColumn<Entry, Object>>();
        for (int i = 0; i < colTitles.length; i++) {
            TableColumn<Entry, Object> col =
                    new TableColumn<Entry, Object>(colTitles[i]); // TODO resource file
            col.setCellValueFactory(new PropertyValueFactory<Entry, Object>(values[i]));
            myColumnsList.add(col);
        }
        return myColumnsList;
    }

    private void initLabel (String labelTitle) {
        myLabel = new Label(labelTitle); // TODO set resource file
        myLabel.getStyleClass().add("LABELID"); // TODO deal with this
    }

    public Label getMyLabel () {
        return myLabel;
    }

    public TableView<Entry> getMyTableView () {
        return myTableView;
    }
    
    public List<TableColumn<Entry,Object>> getColumnsList(){
        return myColumnsList;
    }

}
