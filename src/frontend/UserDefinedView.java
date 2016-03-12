package frontend;

import javafx.scene.control.TableView;

public class UserDefinedView extends ModuleView{
    private static final double WIDTH = 250;
    private static final double HEIGHT = 350;
    private static final double COMMAND_WIDTH = 90;
    private static final double VAL_WIDTH = 160;
    private int commandColInd = 0;
    private int valueColInd = 1;

    
    public UserDefinedView(CommandLine command, EntryManager manager, String labelTitle, String[] columnTitles){
        super(manager, labelTitle, columnTitles);
        //getMyTitledPane().setExpanded(false);
        defineListener(command);        
    }

    @Override
    protected String getClickableString (Entry newValue) {
        return (String) newValue.getFirstValue();
    }
    
    @Override
    protected void setSizing (TableView<Entry> table) { //TODO fix this bug
        table.setPrefSize(WIDTH, HEIGHT);
        table.getColumns().get(commandColInd).setPrefWidth(COMMAND_WIDTH);
        table.getColumns().get(valueColInd).setPrefWidth(VAL_WIDTH);
    }
}
