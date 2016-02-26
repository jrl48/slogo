package frontend;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TerminalEntryManager {
    private List<TerminalEntry> myEntries;
    private ObservableList<TerminalEntry> myObservableEntries;
    
    public TerminalEntryManager(){
        myEntries = new ArrayList<TerminalEntry>();
        myObservableEntries= FXCollections.observableList(myEntries);
    }
    public void addEntry(TerminalEntry entry){
        myObservableEntries.add(entry);
    }
    public void removeEntry(TerminalEntry entry){
        myObservableEntries.remove(entry);
    }
    public ObservableList<TerminalEntry> getEntryList(){
        return myObservableEntries;
    }
}
