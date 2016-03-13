package frontend.turtle;

import java.util.List;

import frontend.TurtlePreferences;
import javafx.scene.image.ImageView;

/**
 * The main "protagonist" of the program. Is in interface form because multiple turtles can be implemented, 
 * and single and multiple turtles have commmon traits.
 * 
 * @author JoeLilien + AlanCavalcanti
 *
 */
public interface Turtle {

	ImageView getBody ();

	void setTurtleCoordinates (double x, double y);

	List<Double> getCoordinates();

	double getTurtleX ();

	double getTurtleY ();

	double getTurtleAngle ();

	void turnTurtle (double angle);

	void setTurtleAngle (double angle);

	void turtlePenDown ();

	void turtlePenUp ();

	boolean isTurtlePenDown ();

	boolean getTurtleVisibility ();

	void hideTurtle();

	void showTurtle();

	boolean isActive();

	void setActive(boolean isActive);

	void stamp();

	int clearStamps();

	TurtlePreferences getPreferences();    

}
