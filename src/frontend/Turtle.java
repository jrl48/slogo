package frontend;

import java.util.List;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;


import javafx.scene.image.ImageView;

/**
 * The main "protagonist" of the program. Is in interface form because multiple turtles can be implemented, 
 * and single and multiple turtles have commmon traits.
 * @author JoeLilien + AlanCavalcanti
 *
 */
public interface Turtle {

    public ImageView getBody ();

    public void setTurtleCoordinates (double x, double y);
    
    public void setTurtleCoordinates (List newCoordinates);
    
    public List getCoordinates();

    public double getTurtleX ();

    public double getTurtleY ();

    public double getTurtleAngle ();

    public void turnTurtle (double angle);

    public void setTurtleAngle (double angle);

    public void turtlePenDown ();

    public void turtlePenUp ();

    public boolean isTurtlePenDown ();

    public boolean getTurtleVisibility ();
    
    public void hideTurtle();
    
    public void showTurtle();
    
    public boolean isActive();
    
    public void setActive(boolean isActive);
    
    public void stamp();
    
    public int clearStamps();
    
    public TurtlePreferences getPreferences();    

}
