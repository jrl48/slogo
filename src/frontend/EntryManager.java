package frontend;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 
 * 
 * @author JoeLilien
 *
 */
public class EntryManager {
    private List<Entry> myEntries;
    private ObservableList<Entry> myObservableEntries;
    
    public EntryManager(){
        myEntries = new ArrayList<Entry>();
        myObservableEntries= FXCollections.observableList(myEntries);
    }
    public void addEntry(Entry entry, Boolean overwrite){   
        if(overwrite){
            int count = 0;
            for(Entry e: myObservableEntries){
                if(entry.getFirstValue().equals(e.getFirstValue())){
                    myObservableEntries.set(count, entry);
                    return;
                }
                count++;
            }
            myObservableEntries.add(entry);
            
        }
        else{
            myObservableEntries.add(entry);
        }

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
    public String getString(Object value){
        for(Entry e:myObservableEntries){
            if (e.getSecondValue().equals(value)){
                return e.getFirstValue().toString();
            }
        }
        return null;
    }
    public Object contains(String value){
        for(Entry e:myObservableEntries){
        	String stringValue = (String) e.getFirstValue();
            if (stringValue.startsWith(value)){
                return e.getFirstValue();
            }
        }
        return null;
    }
    
    public void addAll(List<Entry> list, Boolean overwrite){
        for(Entry e:list){
            addEntry(e,overwrite);
        }
    }
    

}
