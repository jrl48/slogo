package frontend;

import java.util.ResourceBundle;
import javafx.util.converter.DoubleStringConverter;


/**
 * This class is used by WorkspaceViews's TableView as an object necessary to convert user entered
 * strings into doubles that will be stored as variables. It adds additional functionality to its
 * extended class by including error checking for inputs that are not numbers
 * 
 * @author Joe Lilien
 *
 */
public class CheckedDoubleStringConverter extends DoubleStringConverter {
    private ResourceBundle sceneResources =
            ResourceBundle.getBundle(UserInterface.DEFAULT_RESOURCE_PACKAGE + UserInterface.SCENE);

    /**
     * Converts string input into a double, throws an error to the user input is not a number
     * 
     * @param value
     */
    @Override
    public Double fromString (String value) {
        try {
            return super.fromString(value);
        }
        catch (NumberFormatException e) {
            ErrorMessage err = new ErrorMessage(sceneResources.getString("DOUBLE_CONVERSION_ERR"));
            err.showError();
            return 0.0;
        }
    }
}
