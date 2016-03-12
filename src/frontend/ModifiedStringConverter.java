package frontend;

import javafx.util.StringConverter;

public class ModifiedStringConverter extends StringConverter<Entry>{

    @Override
    public Entry fromString (String arg0) {
        return null;
    }

    @Override
    public String toString (final Entry entry) {
        return "Saved Workspaces";
    }
    
}