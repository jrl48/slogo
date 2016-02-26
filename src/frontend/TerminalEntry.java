package frontend;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TerminalEntry implements Entry {
    private SimpleStringProperty Command;
    private SimpleIntegerProperty Result;
    
    public TerminalEntry(String command, Integer result){
        this.Command = new SimpleStringProperty(command);
        this.Result = new SimpleIntegerProperty(result);
    }

    public String getCommand () {
        return Command.get();
    }

    public void setCommand (String command) {
        this.Command.set(command);
    }

    public Integer getResult () {
        return Result.get();
    }

    public void setResult (Integer result) {
        this.Result.set(result);
    }
}
