package frontend;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class ShapePaletteView extends ModuleView {

    public ShapePaletteView (EntryManager manager, String title, String[] colTitles) {        
        super(manager, title, colTitles);
        getMyTableView().setMaxWidth(120);//TODO magic value
        initDefaults(manager);
        setCustomCells();
    }
    
    @SuppressWarnings("unchecked")
    private void setCustomCells () {
        ((TableColumn<Entry,Object>) getMyTableView().getColumns().get(1)).setCellFactory(c->new ImageDisplayCell());
    }
    
    private void initDefaults(EntryManager manager){
        manager.addEntry(new StringObjectEntry((""+(manager.getEntryList().size()+1)),new ImageView(new Image(getClass().getClassLoader()
                                                                                                .getResourceAsStream(UserInterface.DEFAULT_RESOURCE_PACKAGE + "turtle.png")))), false);
        manager.addEntry(new StringObjectEntry((""+(manager.getEntryList().size()+1)),new ImageView(new Image(getClass().getClassLoader()
                                                                                                              .getResourceAsStream(UserInterface.DEFAULT_RESOURCE_PACKAGE + "turtleTest.png")))), false);
        manager.addEntry(new StringObjectEntry((""+(manager.getEntryList().size()+1)),new ImageView(new Image(getClass().getClassLoader()
                                                                                                              .getResourceAsStream(UserInterface.DEFAULT_RESOURCE_PACKAGE + "turtle_gif.gif")))), false);
        manager.addEntry(new StringObjectEntry((""+(manager.getEntryList().size()+1)),new ImageView(new Image(getClass().getClassLoader()
                                                                                                              .getResourceAsStream(UserInterface.DEFAULT_RESOURCE_PACKAGE + "red_square.jpg")))), false);
        manager.addEntry(new StringObjectEntry((""+(manager.getEntryList().size()+1)),new ImageView(new Image(getClass().getClassLoader()
                                                                                                              .getResourceAsStream(UserInterface.DEFAULT_RESOURCE_PACKAGE + "green_circle.png")))), false);
        manager.addEntry(new StringObjectEntry((""+(manager.getEntryList().size()+1)),new ImageView(new Image(getClass().getClassLoader()
                                                                                                              .getResourceAsStream(UserInterface.DEFAULT_RESOURCE_PACKAGE + "blue_triangle.png")))), false);
    }
    @Override
    protected void setSizing (TableView<Entry> table) {
        table.setPrefHeight(200);
    }

}
