package frontend;

import java.util.List;
import javafx.scene.control.TableColumn;

public class WorkspaceView extends ModuleView{
    private static final double WIDTH = 250;
    private static final double HEIGHT = 200;
    private static final double VAR_WIDTH = 195;
    private static final double VAL_WIDTH = 80;    
    private int variableColIndex = 0;
    private int valueColIndex = 1;

    
    public WorkspaceView(EntryManager manager, String labelTitle, String[] columnTitles){
        super(manager, labelTitle, columnTitles);
        manager.addEntry(new StringIntEntry("x",2));
        manager.addEntry(new StringIntEntry("y",3));
        manager.addEntry(new StringIntEntry("fix it",2));
        manager.addEntry(new StringIntEntry("ya",2));
        getMyTableView().setEditable(true);
    }      

//    private void makeEditable () {
//        getColumnsList().get(variableColIndex).setOnEditCommit(e->handle(CellEditEvent<StringIntEntry,String>));
//    }

    @Override
    protected void setSizing (List<TableColumn<Entry, Object>> columns) {
        getMyTableView().setPrefSize(WIDTH, HEIGHT);      
        columns.get(variableColIndex).setPrefWidth(VAR_WIDTH);
        //columns.get(valueColIndex).setPrefWidth(VAL_WIDTH);
    }


}
