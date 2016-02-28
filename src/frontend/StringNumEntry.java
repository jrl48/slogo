package frontend;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class StringNumEntry implements Entry {
    private SimpleStringProperty firstValue;
    private SimpleDoubleProperty secondValue;
    
    public StringNumEntry(String command, Integer result){
        this.firstValue = new SimpleStringProperty(command);
        this.secondValue = new SimpleDoubleProperty(result);
    }

    @Override
    public String getFirstValue () {
        return firstValue.get();
    }
    
    @Override
    public void setFirstValue (Object command) {
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
