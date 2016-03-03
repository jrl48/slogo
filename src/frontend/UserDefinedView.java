package frontend;


import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class UserDefinedView extends ModuleView{
    private static final double WIDTH = 250;
    private static final double HEIGHT = 350;
    private static final double COMMAND_WIDTH = 170;
    private int commandColInd = 0;

    
    public UserDefinedView(CommandLine command, EntryManager manager, String labelTitle, String[] columnTitles){
        super(manager, labelTitle, columnTitles);
        defineListener(command);        
    }

    @Override
    protected String getClickableString (Entry newValue) {
        return (String) newValue.getSecondValue();
    }
    
    @Override
    protected void setSizing () {
        getMyTableView().setPrefSize(WIDTH, HEIGHT);
        getMyTableView().getColumns().get(commandColInd).setPrefWidth(COMMAND_WIDTH);
    }


}
