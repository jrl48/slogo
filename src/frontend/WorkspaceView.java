package frontend;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;


/**
 * Displays all active global variables in the workspace
 * 
 * @author JoeLilien
 *
 */
public class WorkspaceView extends ModuleView {
    private static final double WIDTH = 250;
    private static final double HEIGHT = 150;
    private static final double VAR_WIDTH = 80;
    private static final double VAL_WIDTH = 215;
    private int variableColIndex = 0;
    private int valueColIndex = 1;

    @SuppressWarnings("unchecked")
    public WorkspaceView (EntryManager manager, String labelTitle, String[] columnTitles) {
        super(manager, labelTitle, columnTitles);
        getMyTableView().setEditable(true);
        try{
        makeEditable(((TableColumn<Entry,String>) getMyTableView().getColumns().get(variableColIndex)),
                     ((TableColumn<Entry,Double>) getMyTableView().getColumns().get(valueColIndex)));
        }
        catch(ClassCastException e){
            System.err.println("Class Cast Exception: " +e.getMessage());
        }
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
    protected void setSizing (TableView<Entry> table) { 
        table.setPrefSize(WIDTH, HEIGHT);
        table.getColumns().get(variableColIndex).setMinWidth(VAR_WIDTH);
        table.getColumns().get(valueColIndex).setMinWidth(VAL_WIDTH);
    }

}
