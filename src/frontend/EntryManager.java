package frontend;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EntryManager {
    private List<Entry> myEntries;
    private ObservableList<Entry> myObservableEntries;
    
    public EntryManager(){
        myEntries = new ArrayList<Entry>();
        myObservableEntries= FXCollections.observableList(myEntries);
    }
    public void addEntry(Entry entry){
        myObservableEntries.add(entry);
    }
    public void removeEntry(Entry entry){
        myObservableEntries.remove(entry);
    }
    public ObservableList<Entry> getEntryList(){
        return myObservableEntries;
    }
    public Object getValue(String firstEntryVal){
        for(Entry e:myObservableEntries){
            if (e.getFirstValue().equals(firstEntryVal)){
                return e.getSecondValue();
            }
        }
        return null;
    }
}
