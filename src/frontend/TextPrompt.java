package frontend;

import javafx.scene.control.TextInputDialog;


/**
 * Prompt for a short text.
 * 
 * **Note, javafx imports sometimes do not work, must be deleted and imported again to fix issue**
 * 
 * @author JoeLilien
 *
 */
public class TextPrompt {
    private TextInputDialog myPrompt;

    public TextPrompt (String defaultVal, String message) {
        myPrompt = new TextInputDialog(defaultVal);
        myPrompt.setHeaderText(message);
    }

    public void show () {
        myPrompt.showAndWait();
    }

    public String getText () {
        return myPrompt.getResult();
    }

}
