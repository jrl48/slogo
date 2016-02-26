package frontend;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class WorkspaceView {
    private static final double WIDTH = 250;
    private static final double HEIGHT = 200;
    private static final double COMMAND_WIDTH = 170;
    private Label myTerminalLabel;
    private TableView<TerminalEntry> myTableView;
    
    public WorkspaceView(){
        initWorkspaceView();
    }
    
    private void initWorkspaceView(){
        initLabel();
        initTable();        
    }

    @SuppressWarnings("unchecked")
    private void initTable () {
        myTableView = new TableView<TerminalEntry>();
        TableColumn<TerminalEntry, String> commandColumn = new TableColumn<TerminalEntry, String>("Name"); //TODO resource file
        commandColumn.setPrefWidth(COMMAND_WIDTH);
        TableColumn<TerminalEntry, Integer> resultColumn = new TableColumn<TerminalEntry, Integer>("Value"); //TODO resource file
        myTableView.getColumns().addAll(commandColumn,resultColumn);
        myTableView.getStyleClass().add("TABLEVIEWID"); //TODO deal with this
        myTableView.setPrefSize(WIDTH, HEIGHT);
    }

    private void initLabel () {
        myTerminalLabel = new Label("Workspace"); //TODO set resource file
        myTerminalLabel.getStyleClass().add("LABELID"); //TODO deal with this
    }

    public Node getMyTerminalLabel () {
        return myTerminalLabel;
    }


    public Node getMyTableView () {
        return myTableView;
    }


}
