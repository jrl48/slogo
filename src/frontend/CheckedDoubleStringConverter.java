package frontend;

import javafx.util.converter.DoubleStringConverter;

public class CheckedDoubleStringConverter extends DoubleStringConverter{
    @Override
    public Double fromString(String value){
        try{
            return super.fromString(value);
        }
        catch(NumberFormatException e){//TODO do something better here
            return 0.0;
        }
    }
}
