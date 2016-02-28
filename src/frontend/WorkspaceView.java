package frontend;

import java.util.List;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.NumberStringConverter;


public class WorkspaceView extends ModuleView {
    private static final double WIDTH = 250;
    private static final double HEIGHT = 200;
    private static final double VAR_WIDTH = 200;
    private static final double VAL_WIDTH = 80;
    private int variableColIndex = 0;
    private int valueColIndex = 1;

    @SuppressWarnings("unchecked")
    public WorkspaceView (EntryManager manager, String labelTitle, String[] columnTitles) {
        super(manager, labelTitle, columnTitles);
        manager.addEntry(new StringNumEntry("x", 2));
        manager.addEntry(new StringNumEntry("y", 3));
        manager.addEntry(new StringNumEntry("fix it", 2));
        manager.addEntry(new StringNumEntry("ya", 2));
        setSizing();
        getMyTableView().setEditable(true);
        makeEditable(((TableColumn<Entry,String>) getMyTableView().getColumns().get(variableColIndex)),
                     ((TableColumn<Entry,Double>) getMyTableView().getColumns().get(valueColIndex)));
    }

    private void makeEditable (TableColumn<Entry,String> colOne, TableColumn<Entry,Double> colTwo){
        colOne.setCellFactory(TextFieldTableCell.forTableColumn());
        colOne.setOnEditCommit(e->setVar(e.getNewValue()));
        colTwo.setCellFactory(TextFieldTableCell.forTableColumn(new CheckedDoubleStringConverter()));
        colTwo.setOnEditCommit(e->setValue(e.getNewValue()));
    }

    private void setValue (Double newValue) { 
        ((StringNumEntry)getMyTableView().getSelectionModel().getSelectedItem()).setSecondValue(newValue);       
    }

    private void setVar(String newValue){
        ((StringNumEntry)getMyTableView().getSelectionModel().getSelectedItem()).setFirstValue(newValue);
    }
    @Override
    protected void setSizing () {
        getMyTableView().setPrefSize(WIDTH, HEIGHT);
        getMyTableView().getColumns().get(variableColIndex).setPrefWidth(VAR_WIDTH);
        getMyTableView().getColumns().get(valueColIndex).setPrefWidth(VAL_WIDTH);
    }

}
