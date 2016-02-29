package frontend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;


public class LanguagePreferences {
    private ComboBox<String> myComboBox;
    private List<String> languages =
            new ArrayList<String>(Arrays.asList("Chinese", "English", "French", "German", "Italian",
                                                "Portuguese", "Russian", "Spanish"));

    public LanguagePreferences (LanguageManager manager,CommandLine command) {
        init(manager,command);
    }

    private void init (LanguageManager manager,CommandLine command) {
        manager.addAll(languages);
        myComboBox = new ComboBox<String>(manager.getChoiceList());
        myComboBox.setPromptText("Languages");//TODO resource file
        myComboBox.setOnAction(e->changeLanguage(command, myComboBox.getValue()));
    }

    private void changeLanguage (CommandLine command, String language) {
        command.setLanguage(language);
        System.out.println("New Language is "+language);
    }

    public ComboBox<String> getComboBox () {
        return myComboBox;
    }
}
