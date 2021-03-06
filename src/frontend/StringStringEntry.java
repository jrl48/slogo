package frontend;

import javafx.beans.property.SimpleStringProperty;


/**
 * Implementation of entry, holds two strings. Contains the methods of the interface being
 * implemented.
 * 
 * @author JoeLilien
 *
 */
public class StringStringEntry implements Entry {
    private SimpleStringProperty firstValue;
    private SimpleStringProperty secondValue;

    public StringStringEntry (String firstVal, String secondVal) {
        this.firstValue = new SimpleStringProperty(firstVal);
        this.secondValue = new SimpleStringProperty(secondVal);
    }

    @Override
    public String getFirstValue () {
        return this.firstValue.get();
    }

    @Override
    public void setFirstValue (Object command) {
        this.firstValue.set((String) command);
    }

    @Override
    public String getSecondValue () {
        return this.secondValue.get();
    }

    @Override
    public void setSecondValue (Object result) {
        this.secondValue.set((String) result);
    }

}
