package frontend;


import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

//public class TerminalView {
//    private static final double WIDTH = 250;
//    private static final double HEIGHT = 300;
//    private static final double COMMAND_WIDTH = 170;
//    private Label myTerminalLabel;
//    private TableView<TerminalEntry> myTableView;
//    
//    public TerminalView(TerminalEntryManager manager){
//        initTable(manager);
//        initLabel();
//    }
//
//    private void initTable (TerminalEntryManager manager) {
//        myTableView = new TableView<TerminalEntry>();
//        List<TableColumn<TerminalEntry, Object>> columns = makeColumns();         
//        myTableView.getColumns().addAll(columns);
//        myTableView.setItems(manager.getEntryList());
//        myTableView.getStyleClass().add("TABLEVIEWID"); //TODO deal with this
//        myTableView.setPrefSize(WIDTH, HEIGHT);
//    }
//
//    private List<TableColumn<TerminalEntry, Object>> makeColumns () {
//        List<TableColumn<TerminalEntry, Object>> columns = new ArrayList<TableColumn<TerminalEntry, Object>>();
//        TableColumn<TerminalEntry, Object> commandColumn = new TableColumn<TerminalEntry, Object>("Command"); //TODO resource file
//        commandColumn.setCellValueFactory(new PropertyValueFactory<TerminalEntry,Object>("command"));
//        commandColumn.setPrefWidth(COMMAND_WIDTH);
//        TableColumn<TerminalEntry, Object> resultColumn = new TableColumn<TerminalEntry, Object>("Result"); //TODO resource file
//        resultColumn.setCellValueFactory(new PropertyValueFactory<TerminalEntry,Object>("result"));
//        columns.add(commandColumn);
//        columns.add(resultColumn);
//        return columns;
//    }
//
//    private void initLabel () {
//        myTerminalLabel = new Label("Terminal"); //TODO set resource file
//        myTerminalLabel.getStyleClass().add("LABELID"); //TODO deal with this
//    }
//
//    public Node getMyTerminalLabel () {
//        return myTerminalLabel;
//    }
//
//
//    public Node getMyTableView () {
//        return myTableView;
//=======
import java.util.List;
import javafx.scene.control.TableColumn;


public class TerminalView extends ModuleView {
    private static final double WIDTH = 250;
    private static final double HEIGHT = 300;
    private static final double COMMAND_WIDTH = 170;
    private int commandColIndex = 0;
    
    
    public TerminalView(CommandLine command, EntryManager manager, String labelTitle, String[] columnTitles){
        super(manager, labelTitle, columnTitles);
        defineListener(command);
    }

    private void defineListener(CommandLine command){
        getMyTableView().getSelectionModel().selectedItemProperty().addListener((observableValue,oldValue,newValue)-> setCommand(command, newValue));
    }
    
    private void setCommand(CommandLine command, Entry newValue){
        if(getMyTableView().getSelectionModel().getSelectedItem()!=null){
            command.getTextField().setText((String) newValue.getFirstValue());
        }
    }
    @Override
    protected void setSizing () {
        getMyTableView().setPrefSize(WIDTH,HEIGHT);
        getMyTableView().getColumns().get(commandColIndex).setPrefWidth(COMMAND_WIDTH);
    }


}
