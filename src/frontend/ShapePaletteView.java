package frontend;

import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.paint.Color;

public class ShapePaletteView extends ModuleView {

    public ShapePaletteView (EntryManager manager, String title, String[] colTitles) {        
        super(manager, title, colTitles);
        getMyTableView().setMaxWidth(120);//TODO magic value
        initDefaults(manager);
    }
    
    private void initDefaults(EntryManager manager){
        manager.addEntry(new StringObjectEntry((""+(manager.getEntryList().size()+1)),Color.WHITE), true);
        manager.addEntry(new StringObjectEntry((""+(manager.getEntryList().size()+1)),Color.RED), true);
        manager.addEntry(new StringObjectEntry((""+(manager.getEntryList().size()+1)),Color.ORANGE), true);
        manager.addEntry(new StringObjectEntry((""+(manager.getEntryList().size()+1)),Color.YELLOW), true);
        manager.addEntry(new StringObjectEntry((""+(manager.getEntryList().size()+1)),Color.GREEN), true);
        manager.addEntry(new StringObjectEntry((""+(manager.getEntryList().size()+1)),Color.BLUE), true);
        manager.addEntry(new StringObjectEntry((""+(manager.getEntryList().size()+1)),Color.MAGENTA), true);
        manager.addEntry(new StringObjectEntry((""+(manager.getEntryList().size()+1)),Color.BLACK), true);
    }
    @Override
    protected void setSizing (TableView<Entry> table) {
        table.setPrefHeight(200);
    }

}
