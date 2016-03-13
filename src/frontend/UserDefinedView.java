package frontend;

import javafx.scene.control.TableView;

/**
 * This class contains the View for the user-defined functions to be used in Slogo
 * 
 * @author JoeLilien
 *
 */
public class UserDefinedView extends ModuleView{
    private static final double WIDTH = 250;
    private static final double HEIGHT = 350;
    private static final double COMMAND_WIDTH = 200;
    private int valueColInd = 1;

    
    public UserDefinedView(CommandLine command, EntryManager manager, String labelTitle, String[] columnTitles){
        super(manager, labelTitle, columnTitles);
        defineListener(command);        
    }

    @Override
    protected String getClickableString (Entry newValue) {
        return (String) newValue.getFirstValue();
    }
    
    @Override
    protected void setSizing (TableView<Entry> table) {
        table.setPrefSize(WIDTH, HEIGHT);
        table.getColumns().get(valueColInd).setPrefWidth(COMMAND_WIDTH); 
    }
}
