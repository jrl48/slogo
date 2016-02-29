package frontend;

import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;


public class Display {
    private static final String DEFAULT_RESOURCE_PACKAGE = "resources/frontendResources/";
    private static final String SCENE = "Scene";
    private ResourceBundle sceneResources =
            ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + SCENE);
    private Pane myPane;
    private Group root;
    private static final double WIDTH = 450;
    private static final double HEIGHT = 450;
    private Turtle myTurtle;
    
    // List of Lines that are being drawn by the turtle
    private ArrayList<Line> lines;
    
    public Display () {
        initPane();
    }
    
    private void initPane()
    {
    	root = new Group();
        myPane = new Pane(root);
        myPane.getStyleClass().add(sceneResources.getString("DISPLAYID"));
        myPane.setPrefSize(WIDTH, HEIGHT);
        
        // Biuld turtle and initialize it at Logo's (0,0)
        myTurtle = new Turtle();
        myPane.getChildren().add(myTurtle.getBody());
        updateTurtleVisualPosition();
        
        // Initialize Lines array List
        lines = new ArrayList<Line>();
    }
    
    public Node getPane () {
        return myPane;
    }
    
    
    // Takes current Turtle's Logo's (x,y) position and update the ImageView's javafx (x,y)
    public void updateTurtleVisualPosition()
    {
    	double newX = WIDTH/2 + myTurtle.getX();
    	double newY = HEIGHT/2 - myTurtle.getY();
    	
    	if ( myTurtle.penIsDown() )
    	{
    		Line newLine = new Line(myTurtle.getVisualX(), myTurtle.getVisualY(), newX, newY);  
    		lines.add( newLine );
    		myPane.getChildren().add(newLine);
    	}
    	
    	// When updating coordinates, compensate the X and Y because they reference the edge of the 
    	// image, not the center
    	myTurtle.setVisualCoordinates(newX, newY);
    }
    
    public void toggleTurtlePen()
    {
    	myTurtle.togglePen();
    }
    
    public void setTurtleCoordinates(double newX, double newY)
    {
    	myTurtle.setCoordinates(newX, newY);
    	updateTurtleVisualPosition();
    }
    
    public void moveTurtleForward(double length)
    {
    	double deltaX = Math.sin(myTurtle.getAngle() * Math.PI / 180) * length;
    	double deltaY = Math.cos(myTurtle.getAngle() * Math.PI / 180) * length;
    	
    	myTurtle.setCoordinates(myTurtle.getX() + deltaX, myTurtle.getY() + deltaY);
    	updateTurtleVisualPosition();
    }
    
    // Turn the turtle, in DEGREES!
    public void turnTurtle(double angle)
    {
    	myTurtle.rotate(angle);
    }
}
