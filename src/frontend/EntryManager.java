package frontend;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * This is the driver behind all of the Table functionality of the UserInterface. EntryManager
 * controls and keeps track of an observable list of entry objects that can be customized as the
 * desired by the user. All of the ModuleView objects in the front end are controlled by an
 * EntryManager object
 * 
 * Note**** Given more time, we would like to have reduced the number of for loops written through
 * the use of reflection*****
 * 
 * @author JoeLilien
 *
 */
public class EntryManager {
    private List<Entry> myEntries;
    private ObservableList<Entry> myObservableEntries;
    private ResourceBundle sceneResources =
            ResourceBundle.getBundle(UserInterface.DEFAULT_RESOURCE_PACKAGE + UserInterface.SCENE);

    public EntryManager () {
        myEntries = new ArrayList<Entry>();
        myObservableEntries = FXCollections.observableList(myEntries);
    }

    /**
     * Adds given entry to the Manager's Observable List, if Overwrite is set to true, will replace
     * existing entry with new one, according to a match in the firstValue of the entry
     * 
     * @param entry
     * @param overwrite
     */
    public void addEntry (Entry entry, Boolean overwrite) {
        if (overwrite) {
            int count = 0;
            for (Entry e : myObservableEntries) {
                if (entry.getFirstValue().equals(e.getFirstValue())) {
                    myObservableEntries.set(count, entry);
                    return;
                }
                count++;
            }
            myObservableEntries.add(entry);
        }
        else {
            myObservableEntries.add(entry);
        }
    }

    public void removeEntry (Entry entry) {
        myObservableEntries.remove(entry);
    }

    public ObservableList<Entry> getEntryList () {
        return myObservableEntries;
    }

    /**
     * Returns SecondValue of entry that has a first value that matches the string input
     * 
     * @param firstEntryVal
     * @return
     */
    public Object getValue (String firstEntryVal) {
        for (Entry e : myObservableEntries) {
            if (e.getFirstValue().equals(firstEntryVal)) {
                return e.getSecondValue();
            }
        }
        return null;
    }

    /**
     * Returns String (firstValue of entry) that is associated with given object (secondValue of
     * entry)
     * 
     * @param value
     * @return
     */
    public String getString (Object value) {
    	System.out.println("RWER");
    	System.out.println(value);
        for (Entry e : myObservableEntries) {
        	System.out.println(e.getSecondValue());
            if (e.getSecondValue().equals(value)) {
            	System.out.println("WEREWRWER");
                return e.getFirstValue().toString();
            }
        }
        return null;
    }

    /**
     * Checks if String value is contained in the Manager, and returns that string if so, null
     * otherwise
     * 
     * @param value
     * @return
     */
    public Object contains (String value) {
        for (Entry e : myObservableEntries) {
            String stringValue = (String) e.getFirstValue();
            if (stringValue.startsWith(value)) {
                return e.getFirstValue();
            }
        }
        return null;
    }

    public void addAll (List<Entry> list, Boolean overwrite) {
        for (Entry e : list) {
            addEntry(e, overwrite);
        }
    }

    /**
     * Adds new Entry to the list according to desired properties, overwrite set to true
     * Throws an error if the index is not valid
     * 
     * @param index
     * @param value
     */
    public void set (int index, Object value) {
        try {
            addEntry(new StringObjectEntry("" + index, value), true);
        }
        catch (IndexOutOfBoundsException e) {
            ErrorMessage err = new ErrorMessage(sceneResources.getString("INVALID_IND_ERR"));
            err.showError();
        }
    }

}
