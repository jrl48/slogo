package frontend;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TerminalEntry {
    private SimpleStringProperty command;
    private SimpleIntegerProperty result;
    
    public TerminalEntry(String command, Integer result){
        this.command = new SimpleStringProperty(command);
        this.result = new SimpleIntegerProperty(result);
    }

    public String getCommand () {
        return command.get();
    }

    public void setCommand (String command) {
        this.command.set(command);
    }

    public Integer getResult () {
        return result.get();
    }

    public void setResult (Integer result) {
        this.result.set(result);
    }
}
