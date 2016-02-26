package frontend;

import javafx.geometry.Pos;
import javafx.scene.control.Button;

public class ButtonFactory {
    private final static ButtonFactory myButtonFactory= new ButtonFactory();
    
    private ButtonFactory(){
        
    }
    
    public Button makeButton(String label){
        Button mybutton = new Button(label);
        return mybutton;
    }
    
    public static ButtonFactory getInstance(){
        return myButtonFactory;
    }
}
