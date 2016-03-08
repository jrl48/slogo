package frontend;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ColorPaletteView extends ModuleView{

    public ColorPaletteView (EntryManager manager, String title, String[] colTitles) {
        super(manager, title, colTitles);        
        initDefaults(manager);
        setCellDisplay();
        getMyTableView().setMaxWidth(120);//TODO magic value

    }

    private void initDefaults (EntryManager manager) {
        manager.addEntry(new StringObjectEntry((""+(manager.getEntryList().size()+1)),Color.WHITE), true);
        manager.addEntry(new StringObjectEntry((""+(manager.getEntryList().size()+1)),Color.RED), true);
        manager.addEntry(new StringObjectEntry((""+(manager.getEntryList().size()+1)),Color.ORANGE), true);
        manager.addEntry(new StringObjectEntry((""+(manager.getEntryList().size()+1)),Color.YELLOW), true);
        manager.addEntry(new StringObjectEntry((""+(manager.getEntryList().size()+1)),Color.GREEN), true);
        manager.addEntry(new StringObjectEntry((""+(manager.getEntryList().size()+1)),Color.BLUE), true);
        manager.addEntry(new StringObjectEntry((""+(manager.getEntryList().size()+1)),Color.MAGENTA), true);
        manager.addEntry(new StringObjectEntry((""+(manager.getEntryList().size()+1)),Color.BLACK), true);
    }
    
    @SuppressWarnings("unchecked")  //TODO figure out how to check
    private void setCellDisplay(){    
        ((TableColumn<Entry,Color>) getMyTableView().getColumns().get(1)).setCellFactory(c-> new ColorDisplayCell());        
    }

    @Override
    protected void setSizing (TableView<Entry> table) {
        table.setPrefHeight(200);//TODO magic number
    }

}
