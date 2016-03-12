package frontend;

import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;

public class ShapePaletteView extends ModuleView {

    public ShapePaletteView (EntryManager manager, String title, String[] colTitles) {        
        super(manager, title, colTitles);
        getMyTableView().setMaxWidth(120);//TODO magic value
        
    }

    @Override
    protected void setSizing (TableView<Entry> table) {
        table.setPrefHeight(200);
    }

}
