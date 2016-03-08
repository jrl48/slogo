package frontend;

import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;

public class TurtleManagerView extends ModuleView{
    private static final double WIDTH = 250;
    private static final double HEIGHT = 150;
    
    public TurtleManagerView (EntryManager manager, String labelTitle, String[] colTitles) {
        super(manager, labelTitle, colTitles);
       // getMyTitledPane().setExpanded(false);
    }
    

    private void openOptions () {
       
    }

    @Override
    protected void setSizing (TableView<Entry> table) {
        table.setPrefSize(WIDTH, HEIGHT);
        
    }

}
