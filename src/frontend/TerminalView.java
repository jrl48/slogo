package frontend;

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
            command.getTextField().setText(newValue.getCommand());
        }
    }
    @Override
    protected void setSizing (List<TableColumn<Entry, Object>> columns) {
        getMyTableView().setPrefSize(WIDTH,HEIGHT);
        columns.get(commandColIndex).setPrefWidth(COMMAND_WIDTH);
    }


}
