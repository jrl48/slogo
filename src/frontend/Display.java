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
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


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
    private ColorPicker penCol;
    
    // List of Lines that are being drawn by the turtle
    private ArrayList<Line> lines;
    
    public Display (DisplayPreferences dispPref) {
        this.penCol = dispPref.getPenColorPicker();
        initPane(dispPref);
    }
    
    private void initPane(DisplayPreferences dispPref)
    {
    	root = new Group();
        myPane = new Pane(root);
        myPane.getStyleClass().add(sceneResources.getString("DISPLAYID"));
        myPane.setPrefSize(WIDTH, HEIGHT);
        setPaneBinding(myPane, dispPref.getDispColorPicker());
        
        // Biuld turtle and initialize it at Logo's (0,0)
        myTurtle = new Turtle(dispPref.getImageProperty());
        myPane.getChildren().add(myTurtle.getBody());
        updateTurtleVisualPosition();
        
        // Initialize Lines array List
        lines = new ArrayList<Line>();
    }
    
    private void setPaneBinding(Pane pane, ColorPicker cp){
        ObjectProperty<Background> back = pane.backgroundProperty();
        back.bind(Bindings.createObjectBinding(()->{
            BackgroundFill fill = new BackgroundFill(cp.getValue(),CornerRadii.EMPTY,Insets.EMPTY);
            return new Background(fill);
        }, cp.valueProperty()));
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
    		newLine.setStroke(penCol.getValue());
    		lines.add( newLine );
    		myPane.getChildren().addAll(newLine);
    	}
    	
    	// When updating coordinates, compensate the X and Y because they reference the edge of the 
    	// image, not the center
    	myTurtle.setVisualCoordinates(newX, newY);
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
    
    //HAD TO ADD TO USE METHODS (we should consider passing turtle not display)
    public void setTurtleAngle(double angle) {
    	myTurtle.setAngle(angle);
    }
    
    public double getTurtleAngle() {
    	return myTurtle.getAngle();
    }
    
    public double getTurtleX() {
    	return myTurtle.getVisualX();
    }
    
    public double getTurtleY() {
    	return myTurtle.getVisualY();
    }
    
    public boolean isTurtlePenDown() {
    	return myTurtle.getPen();
    }
    
    public void turtlePenUp() {
    	myTurtle.putPenUp();
    }
    
    public void turtlePenDown() {
    	myTurtle.putPenDown();
    }
    
    public void hideTurtle()
    {
    	myTurtle.toggleVisibility(0);
    }
    
    public void showTurtle()
    {
    	myTurtle.toggleVisibility(1);
    }
    
    public boolean getTurtleVisibility()
    {
    	return myTurtle.isVisible();
    }
    
    public void clearDisplay()
    {
    	// Deletes all lines 
    	for ( int i = 0; i < lines.size(); i++ )
    	{
    		root.getChildren().remove(lines.get(i));
    	}
    	
    	lines.clear();
    }
}
