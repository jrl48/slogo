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
        
        // Build turtle and initialize it at Logo's (0,0)
        myTurtle = new Turtle(dispPref.getImageProperty());
        myPane.getChildren().add(myTurtle.getBody());
        updateTurtleVisualPosition(false);
        
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
    public void updateTurtleVisualPosition(boolean overridePen)
    {
    	double newX = WIDTH/2 + myTurtle.getX();
    	double newY = HEIGHT/2 - myTurtle.getY();
    	
    	if ( myTurtle.penIsDown() && !overridePen)
    	{
    		Line newLine = new Line(myTurtle.getVisualX(), myTurtle.getVisualY(), newX, newY); 
    		newLine.setStroke(penCol.getValue());
    		lines.add( newLine );
    		myPane.getChildren().add(newLine);
    	}
    	
    	// When updating coordinates, compensate the X and Y because they reference the edge of the 
    	// image, not the center
    	myTurtle.setVisualCoordinates(newX, newY);
    }
    
    
    public void setTurtleCoordinates(double newX, double newY)
    {
    	myTurtle.setCoordinates(newX, newY);
    	updateTurtleVisualPosition(false);
    }
    
    public void moveTurtleForward(double length)
    {
    	double deltaX = Math.sin(myTurtle.getAngle() * Math.PI / 180) * length;
    	double deltaY = Math.cos(myTurtle.getAngle() * Math.PI / 180) * length;
    	
    	// TODO:BUG: Large inputs crash program
    	boolean flag = false;
    	do 
    	{
    		flag = false;
    		
    		for ( int multiplier : new int[] {-1, 1} )
    		{
    			if ( multiplier * (myTurtle.getX() + deltaX) > WIDTH/2 )
    			{
    				double amountWalked = multiplier * WIDTH/2 - myTurtle.getX();
    				double deltaPrime = amountWalked / Math.tan(myTurtle.getAngle() * Math.PI / 180);

    				myTurtle.setCoordinates(multiplier * WIDTH/2, myTurtle.getY() + deltaPrime);
    				updateTurtleVisualPosition(false);
    				deltaX -= amountWalked;
    				deltaY -= deltaPrime;

    				myTurtle.setCoordinates(-multiplier * WIDTH/2, myTurtle.getY() + deltaPrime);

    				updateTurtleVisualPosition(true);
    				
    				flag = true;
    			}

    			if ( multiplier * (myTurtle.getY() + deltaY) > HEIGHT/2 )
    			{
    				double amountWalked = multiplier * HEIGHT/2 - myTurtle.getY();
    				double deltaPrime = amountWalked * Math.tan(myTurtle.getAngle() * Math.PI / 180);

    				myTurtle.setCoordinates( myTurtle.getX() + deltaPrime, multiplier * HEIGHT/2);
    				updateTurtleVisualPosition(false);
    				deltaY -= amountWalked;
    				deltaX -= deltaPrime;

    				myTurtle.setCoordinates(myTurtle.getX() + deltaPrime, -multiplier * HEIGHT/2);
    				updateTurtleVisualPosition(true);
    				
    				flag = true;
    			}
    		}
    	} while ( flag );
    	
    	myTurtle.setCoordinates(myTurtle.getX() + deltaX, myTurtle.getY() + deltaY);
    	updateTurtleVisualPosition(false);
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
    	return myTurtle.getX();
    }
    
    public double getTurtleY() {
    	return myTurtle.getY();
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
    	for ( Line toBeDeleted : lines )
    	{
    		myPane.getChildren().remove(toBeDeleted);
    	}
    	
    	lines.clear();
    }
}
