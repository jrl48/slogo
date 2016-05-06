package frontend;

import frontend.turtle.Turtle;
import javafx.scene.control.Label;

public class TurtleInfoCell extends CustomDisplayCell{

    @Override
    protected void handleUpdate (Object obj) {
        Turtle turtle = (Turtle) obj;
        setGraphic(new Label(turtle.turtleStats()));
    }

}
