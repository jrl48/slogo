package frontend;

import javafx.scene.image.ImageView;


public interface Turtle {

    public ImageView getBody ();

    public void setCoordinates (double x, double y);

    public boolean penIsDown ();

    // Both visual getters return the center of the turtle's ImageView body
    public double getVisualX ();

    public double getVisualY ();

    public double getX ();

    public double getY ();

    public double getSize ();

    public void setVisualCoordinates (double newX, double newY);

    public void togglePen ();

    public double getAngle ();

    public void rotate (double angle);

    // HAVE TO ADD TO USE METHODS
    public void setAngle (double angle);

    public void putPenDown ();

    public void putPenUp ();

    public boolean getPen ();

    public void toggleVisibility (double newOpacity);

    public boolean isVisible ();

}
