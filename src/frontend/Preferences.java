package frontend;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;


/**
 * Abstract Class defines and implements methods common to all preference objects, which are meant
 * to allow user dynamically control preferences
 * 
 * @author JoeLilien
 *
 */
public abstract class Preferences {

    /**
     * Applies a set List of Nodes to the Graphic property of Menu Items and adds them to the desired list
     * 
     * @param nodelist
     * @param menulist
     */
    protected void addMoreOptions (List<Node> nodelist, ArrayList<MenuItem> menulist) {
        for (Node n : nodelist) {
            MenuItem item = new MenuItem();
            item.setGraphic(n);
            menulist.add(item);
        }
    }

    /**
     * Automatically Updates Menu according to desired functionality defined by list inputs
     * 
     * @param options
     * @param subOptionsLists
     */
    protected void initOptions (List<Menu> options, List<ArrayList<MenuItem>> subOptionsLists) {
        for (int i = 0; i < options.size(); i++) {
            options.get(i).getItems().addAll(subOptionsLists.get(i));
        }
    }

}
