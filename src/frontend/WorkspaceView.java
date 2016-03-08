package frontend;

//
//import javafx.scene.Node;
//import javafx.scene.control.Label;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//
//public class WorkspaceView {
//    private static final double WIDTH = 250;
//    private static final double HEIGHT = 200;
//    private static final double COMMAND_WIDTH = 170;
//    private Label myTerminalLabel;
//    private TableView<TerminalEntry> myTableView;
//    
//    public WorkspaceView(){
//        initWorkspaceView();
//    }
//    
//    private void initWorkspaceView(){
//        initLabel();
//        initTable();        
//    }
//
//    @SuppressWarnings("unchecked")
//    private void initTable () {
//        myTableView = new TableView<TerminalEntry>();
//        TableColumn<TerminalEntry, String> commandColumn = new TableColumn<TerminalEntry, String>("Name"); //TODO resource file
//        commandColumn.setPrefWidth(COMMAND_WIDTH);
//        TableColumn<TerminalEntry, Integer> resultColumn = new TableColumn<TerminalEntry, Integer>("Value"); //TODO resource file
//        myTableView.getColumns().addAll(commandColumn,resultColumn);
//        myTableView.getStyleClass().add("TABLEVIEWID"); //TODO deal with this
//        myTableView.setPrefSize(WIDTH, HEIGHT);
//    }
//
//    private void initLabel () {
//        myTerminalLabel = new Label("Workspace"); //TODO set resource file
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

import java.util.List;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.NumberStringConverter;


public class WorkspaceView extends ModuleView {
    private static final double WIDTH = 250;
    private static final double HEIGHT = 150;
    private static final double VAR_WIDTH = 170;
    private static final double VAL_WIDTH = 80;
    private int variableColIndex = 0;
    private int valueColIndex = 1;

    @SuppressWarnings("unchecked")
    public WorkspaceView (EntryManager manager, String labelTitle, String[] columnTitles) {
        super(manager, labelTitle, columnTitles);
        getMyTableView().setEditable(true);
        try{
        makeEditable(((TableColumn<Entry,String>) getMyTableView().getColumns().get(variableColIndex)),
                     ((TableColumn<Entry,Double>) getMyTableView().getColumns().get(valueColIndex)));
        }
        catch(ClassCastException e){
            System.err.println("Class Cast Exception: " +e.getMessage());
        }
    }

    private void makeEditable (TableColumn<Entry,String> colOne, TableColumn<Entry,Double> colTwo){
        colOne.setCellFactory(TextFieldTableCell.forTableColumn());
        colOne.setOnEditCommit(e->setVar(e.getNewValue()));
        colTwo.setCellFactory(TextFieldTableCell.forTableColumn(new CheckedDoubleStringConverter()));
        colTwo.setOnEditCommit(e->setValue(e.getNewValue()));
    }

    private void setValue (Double newValue) { 
        ((StringNumEntry)getMyTableView().getSelectionModel().getSelectedItem()).setSecondValue(newValue);       
    }

    private void setVar(String newValue){
        ((StringNumEntry)getMyTableView().getSelectionModel().getSelectedItem()).setFirstValue(newValue);
    }
    @Override
    protected void setSizing (TableView<Entry> table) { 
        table.setPrefSize(WIDTH, HEIGHT);
        table.getColumns().get(variableColIndex).setPrefWidth(VAR_WIDTH);
        table.getColumns().get(valueColIndex).setPrefWidth(VAL_WIDTH);
    }

}
