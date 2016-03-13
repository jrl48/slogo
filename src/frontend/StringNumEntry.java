package frontend;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;


/**
 * Implementation of Entry designed to hold a string as the firstValue and a Number as the second,
 * Used by WorkspaceView
 * 
 * @author JoeLilien
 *
 */
public class StringNumEntry implements Entry {
    private SimpleStringProperty firstValue;
    private SimpleDoubleProperty secondValue;

    public StringNumEntry (String command, double result) {
        this.firstValue = new SimpleStringProperty(command);
        this.secondValue = new SimpleDoubleProperty(result);
    }

    @Override
    public String getFirstValue () {
        return firstValue.get();
    }

    @Override
    public void setFirstValue (Object command) { // TODO throw err
        this.firstValue.set((String) command);
    }

    @Override
    public Double getSecondValue () {
        return secondValue.get();
    }

    @Override
    public void setSecondValue (Object result) {
        this.secondValue.set((Double) result);
    }
}
