package frontend;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class StringIntEntry implements Entry {
    private SimpleStringProperty firstValue;
    private SimpleIntegerProperty secondValue;
    
    public StringIntEntry(String command, Integer result){
        this.firstValue = new SimpleStringProperty(command);
        this.secondValue = new SimpleIntegerProperty(result);
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
    public Integer getSecondValue () {
        return secondValue.get();
    }
    
    @Override
    public void setSecondValue (Object result) {
        this.secondValue.set((Integer) result);
    }
}
