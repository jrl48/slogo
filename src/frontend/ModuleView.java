package frontend;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public abstract class ModuleView {

    private ResourceBundle sceneResources =
            ResourceBundle.getBundle(UserInterface.DEFAULT_RESOURCE_PACKAGE + UserInterface.SCENE);   
    private static final String[] entryValues = new String[] { "FirstValue", "SecondValue" };
    private Label myLabel;
    private TableView<Entry> myTableView;

    public ModuleView (EntryManager manager, String labelTitle, String[] colTitles) {
        initTable(manager, colTitles);
        initLabel(labelTitle);
    }

    private void initTable (EntryManager manager, String[] colTitles) {
        myTableView = new TableView<Entry>();
        List<TableColumn<Entry, Object>> columns = makeColumns(colTitles, entryValues);
        myTableView.getColumns().addAll(columns);
        myTableView.setItems(manager.getEntryList());
        myTableView.getStyleClass().add(sceneResources.getString("TABLEVIEWID")); 
        setSizing();
    }

    protected abstract void setSizing (); 
                                                                                  
    

    protected void defineListener(CommandLine command){
        getMyTableView().getSelectionModel().selectedItemProperty().addListener((observableValue,oldValue,newValue)-> setCommand(command, newValue));
    }
    
    private void setCommand(CommandLine command, Entry newValue){
        if(getMyTableView().getSelectionModel().getSelectedItem()!=null){
            command.getTextField().setText(getClickableString(newValue));
        }
    }
    
    protected String getClickableString (Entry newValue) {
        return (String) newValue.getFirstValue();
    }

    private List<TableColumn<Entry, Object>> makeColumns (String[] colTitles, String[] values) {
        List<TableColumn<Entry,Object>> colList = new ArrayList<TableColumn<Entry, Object>>();
        for (int i = 0; i < colTitles.length; i++) {
            TableColumn<Entry, Object> col =
                    new TableColumn<Entry, Object>(colTitles[i]); // TODO resource file
            col.setCellValueFactory(new PropertyValueFactory<Entry, Object>(values[i]));
            colList.add(col);
        }
        return colList;
    }

    private void initLabel (String labelTitle) {
        myLabel = new Label(labelTitle);
        myLabel.getStyleClass().add(sceneResources.getString("LABELID"));
    }

    public Label getMyLabel () {
        return myLabel;
    }

    public TableView<Entry> getMyTableView () {
        return myTableView;
    }
    

}
