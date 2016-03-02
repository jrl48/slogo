package frontend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import backend.CommandParser;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;


public class LanguagePreferences {
    private ComboBox<String> myComboBox;
    private List<String> languages =
            new ArrayList<String>(Arrays.asList("Chinese", "English", "French", "German", "Italian",
                                                "Portuguese", "Russian", "Spanish"));
    private ResourceBundle sceneResources =
            ResourceBundle.getBundle(UserInterface.DEFAULT_RESOURCE_PACKAGE + UserInterface.SCENE);  

    public LanguagePreferences (LanguageManager manager,CommandParser parser) {
        init(manager,parser);
    }

    private void init (LanguageManager manager,CommandParser parser) {
        manager.addAll(languages);
        myComboBox = new ComboBox<String>(manager.getChoiceList());
        myComboBox.setPromptText(sceneResources.getString("LANGUAGES"));
        myComboBox.getStyleClass().add(sceneResources.getString("BUTTONID"));
        myComboBox.setOnAction(e->changeLanguage(parser, myComboBox.getValue()));
    }

    private void changeLanguage (CommandParser parser, String language) {
        parser.setLanguage(language);
    }

    public ComboBox<String> getComboBox () {
        return myComboBox;
    }
}
