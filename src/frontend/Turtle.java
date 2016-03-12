package frontend;

import java.util.List;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;


import javafx.scene.image.ImageView;

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
