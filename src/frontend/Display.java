package frontend;

import javafx.scene.Node;
import javafx.scene.layout.Pane;


//public class Display {
//    private Pane myPane;
//    private static final double WIDTH = 450;
//    private static final double HEIGHT = 450;

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
    private Turtle turtle;
    
    // List of Lines that are being drawn by the turtle
    private ArrayList<Line> lines;
    
    public Display () {
        initPane();
    }
    
//    private void initPane(){
//        myPane = new Pane();
//        myPane.getStyleClass().add(UserInterface.sceneResources.getString("DISPLAYID"));
//        myPane.setPrefSize(WIDTH, HEIGHT);
//    }
//    public Node getPane () {
//        return myPane;
//    }
    private void initPane()
    {
    	root = new Group();
        myPane = new Pane(root);
        myPane.getStyleClass().add(sceneResources.getString("DISPLAYID"));
        myPane.setPrefSize(WIDTH, HEIGHT);
        
        // Biuld turtle and initialize it at Logo's (0,0)
        turtle = new Turtle();
        myPane.getChildren().add(turtle.getBody());
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
    	double newX = WIDTH/2 + turtle.getX();
    	double newY = HEIGHT/2 - turtle.getY();
    	
    	if ( turtle.penIsDown() )
    	{
    		Line newLine = new Line(turtle.getVisualX(), turtle.getVisualY(), newX, newY);  
    		lines.add( newLine );
    		myPane.getChildren().add(newLine);
    	}
    	
    	// When updating coordinates, compensate the X and Y because they reference the edge of the 
    	// image, not the center
    	turtle.setVisualCoordinates(newX, newY);
    }
    
    public void toggleTurtlePen()
    {
    	turtle.togglePen();
    }
    
    public void setTurtleCoordinates(double newX, double newY)
    {
    	turtle.setCoordinates(newX, newY);
    	updateTurtleVisualPosition();
    }
    
    public void moveTurtleForward(double length)
    {
    	double deltaX = Math.sin(turtle.getAngle() * Math.PI / 180) * length;
    	double deltaY = Math.cos(turtle.getAngle() * Math.PI / 180) * length;
    	
    	turtle.setCoordinates(turtle.getX() + deltaX, turtle.getY() + deltaY);
    	updateTurtleVisualPosition();
    }
    
    // Turn the turtle, in DEGREES!
    public void turnTurtle(double angle)
    {
    	turtle.rotate(angle);
    }
}
