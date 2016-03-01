package frontend;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.util.converter.DoubleStringConverter;

public class CheckedDoubleStringConverter extends DoubleStringConverter{
    @Override
    public Double fromString(String value){
        try{
            return super.fromString(value);
        }
        catch(NumberFormatException e){//TODO do something better here
            ErrorMessage err = new ErrorMessage("Value Must be a Number");// TODO resource file
            err.showError();
            return 0.0;
        }
    }
}
