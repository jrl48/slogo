package frontend;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Manages Observable List of strings to be used in Language Preference's combobox object
 * 
 * @author JoeLilien
 *
 */
public class LanguageManager {

    private List<String> myChoices;
    private ObservableList<String> myObservableChoices;

    public LanguageManager () {
        myChoices = new ArrayList<>();
        myObservableChoices = FXCollections.observableList(myChoices);
    }

    public void add (String choice) {
        myObservableChoices.add(choice);
    }
    
    public void addAll (List<String> list){
        myObservableChoices.addAll(list);
    }
    
    public void remove (String choice) {
        myObservableChoices.remove(choice);
    }

    public ObservableList<String> getChoiceList () {
        return myObservableChoices;
    }
}
