package frontend;

import java.util.ResourceBundle;
import javafx.util.StringConverter;


/**
 * Custom String Converter, necessary for use by SavedWorkspaces combobox in SLOGOScreen
 * 
 * @author JoeLilien
 *
 */
public class ModifiedStringConverter extends StringConverter<Entry> {

    private ResourceBundle sceneResources =
            ResourceBundle.getBundle(UserInterface.DEFAULT_RESOURCE_PACKAGE + UserInterface.SCENE);

    @Override
    public Entry fromString (String arg0) {
        return null;
    }

    /**
     * Set Displayed text to constant, defined in resource file
     */
    @Override
    public String toString (final Entry entry) {
        return sceneResources.getString("SAVEBOX");
    }

}
