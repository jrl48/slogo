package frontend;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class StringStringEntry implements Entry{
    private SimpleStringProperty firstValue;
    private SimpleStringProperty secondValue;   
    
    public StringStringEntry(String firstVal, String secondVal){
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
