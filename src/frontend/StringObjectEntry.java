package frontend;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;


/**
 * Implementation of the entry interface, contains two values as an entry.
 * 
 * @author JoeLilien
 *
 */
public class StringObjectEntry implements Entry{
    private SimpleStringProperty firstValue = new SimpleStringProperty();
    private SimpleObjectProperty<Object> secondValue;
    
    public StringObjectEntry(String firstVal, Object secondVal){
        this.firstValue = new SimpleStringProperty(firstVal);
        this.secondValue = new SimpleObjectProperty<Object>(secondVal);
    }
    public SimpleStringProperty getFirstValueProperty(){
        return firstValue;
    }
    @Override
    public String getFirstValue () {
        return firstValue.get();
    }

    @Override
    public void setFirstValue (Object command) {
        firstValue.set((String) command);
    }

    @Override
    public Object getSecondValue () {
        return secondValue.get();
    }

    @Override
    public void setSecondValue (Object result) {
        secondValue.set(result);
    }

}
