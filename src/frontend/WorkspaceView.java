package frontend;

import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class WorkspaceView extends ModuleView{
    private static final double WIDTH = 250;
    private static final double HEIGHT = 200;

    
    public WorkspaceView(EntryManager manager, String labelTitle, String[] columnTitles){
        super(manager, labelTitle, columnTitles);
    }      

    @Override
    protected void setSizing (List<TableColumn<Entry, Object>> columns) {
        getMyTableView().setPrefSize(WIDTH, HEIGHT);        
    }


}
