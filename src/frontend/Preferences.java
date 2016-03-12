package frontend;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;

public abstract class Preferences {
    
    protected void addMorePenOptions (List<Node> nodelist, ArrayList<MenuItem> menulist) {
        for(Node n : nodelist){
            MenuItem item = new MenuItem();
            item.setGraphic(n);
            menulist.add(item);
        }
    }
    
}
