package frontend;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public abstract class ModuleView {

    private ResourceBundle sceneResources =
            ResourceBundle.getBundle(UserInterface.DEFAULT_RESOURCE_PACKAGE + UserInterface.SCENE);   
    private static final String[] entryValues = new String[] { "FirstValue", "SecondValue" };
    private TableView<Entry> myTableView;
    private String title;


    public ModuleView (EntryManager manager, String title, String[] colTitles) {
        initTable(manager, colTitles);
        this.title = title;
//        initTitledPane(title);
    }

    private void initTable (EntryManager manager, String[] colTitles) {
        myTableView = new TableView<Entry>();
        List<TableColumn<Entry, Object>> columns = makeColumns(colTitles, entryValues);
        myTableView.getColumns().addAll(columns);
        myTableView.setItems(manager.getEntryList());
        myTableView.setPrefWidth(300);//TODO magic vallue
        myTableView.getStyleClass().add(sceneResources.getString("TABLEVIEWID")); 
        setSizing(myTableView);
    }

    protected abstract void setSizing (TableView<Entry> table); 
                                                                                  
    

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

//    private void initTitledPane (String title) {
//        myTitledPane = new TitledPane(title,getMyTableView());
//        myTitledPane.getStyleClass().add(sceneResources.getString("LABELID"));
//    }

    public String getTitle(){
        return title;
    }

    public TableView<Entry> getMyTableView () {
        return myTableView;
    }


}
