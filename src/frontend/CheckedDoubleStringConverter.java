package frontend;

import java.util.ResourceBundle;
import javafx.util.converter.DoubleStringConverter;

public class CheckedDoubleStringConverter extends DoubleStringConverter{
    private ResourceBundle sceneResources =
            ResourceBundle.getBundle(UserInterface.DEFAULT_RESOURCE_PACKAGE + UserInterface.SCENE);
    @Override
    public Double fromString(String value){
        try{
            return super.fromString(value);
        }
        catch(NumberFormatException e){
            ErrorMessage err = new ErrorMessage(sceneResources.getString("DOUBLE_CONVERSION_ERR"));
            err.showError();
            return 0.0;
        }
    }
}
