package frontend.turtle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import frontend.Display;
import frontend.TurtlePreferences;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;


/**
 * One implementation of the Turtles.
 * Turtle will be talking to the Backend, and maintaining its own crucial information.
 * 
 * 
 * @author JoeLilien + AlanCavalcanti
 *
 */
public class SingleTurtle implements Turtle {
    private ImageView body;
    private double x = 0.0;
    private double y = 0.0;
    private BooleanProperty pen = new SimpleBooleanProperty(false);
    private Pane myPane;
    private static final double WIDTH = 30;
    private static final double HEIGHT = 30;
    private static final double DEFAULT_TURTLE_SIZE = 30;
    private static final double DISPLAY_WIDTH = Display.WIDTH;
    private static final double DISPLAY_HEIGHT = Display.HEIGHT;
    private static final String DEGREE = "\u00b0";
    private boolean isActive;
    private static final double DASH_LENGTH = 25.0; 
    private static final double TRANSPARENT = 0.5;
    
    private StringProperty turtStatsProp = new SimpleStringProperty();
    private TurtlePreferences myPreferences = new TurtlePreferences();
    private double angle;
    private ArrayList<ImageView> stamps;
    
    // Animation!!
    private AnimationController animationController;

    public SingleTurtle (Pane myPane, AnimationController animationController) {
        this.myPane = myPane;
        this.body = new ImageView();
        Bindings.bindBidirectional(this.body.imageProperty(), myPreferences.getImageProperty());
        body.setFitWidth(WIDTH);
        body.setFitHeight(HEIGHT);
        pen.bindBidirectional(myPreferences.isPenActive());
        isActive = true;
        this.animationController = animationController;
        stamps = new ArrayList<>();
        
        angle = 0;
        
        updateTurtleVisualPosition();
        setMouseActions();
    }
    
    /**
     * Mouse handler, to treat selection
     */
    private void setMouseActions () {
        initStatsTooltip();
        body.setOnMouseClicked(e->handleClick(e));
        body.setCursor(Cursor.HAND);
    }

    /**
     * Click Handler, opens up turtle preference screen
     * @param e
     */
    private void handleClick (MouseEvent e) {
        if(e.getButton().equals(MouseButton.SECONDARY)){
            myPreferences.openPreferences(body, e.getScreenX(), e.getScreenY());
        }
        else{
            setActive(!isActive());
        }
    }

    /**
     * Binds the tooltip to the Turtle's Imageview
     */
    private void initStatsTooltip () {
        Tooltip t = new Tooltip();
        t.textProperty().bind(turtStatsProp);
        t.setOnShowing(e->updateTurtleStatsProp(turtleStats()));
        Tooltip.install(body, t);
    }

    /**
     * Updates the turtle's stats
     * @param turtStats
     */
    private void updateTurtleStatsProp(String turtStats){
        turtStatsProp.setValue(turtStats);
    }
    
    /** 
     * Gives out a string of the turtle's stats
     * @return
     */
    private String turtleStats(){
        StringBuilder turtStats = new StringBuilder();
        turtStats.append(String.format("Position = [%.2f, %.2f]%n",getTurtleX(),getTurtleY()));
        turtStats.append(String.format("Heading = %.2f",getTurtleAngle())+DEGREE+"\n");
        turtStats.append("PenDown = "+isTurtlePenDown()+"\n");
        return turtStats.toString();
    }


    /**
     * Calculate the end of the turtle's trajectory, and pass it as an Action to the Animation Controller
     * But, the internal values of the new positions are stored in the class, so other actions
     * can use the updated values (even before the turtle updates its visual coordinates through animation)
     * 
     * Obs: Toroidal implementation (while buggy) had begun to be implemented, but was dropped.
     * Thus the huge commentted out section
     * @param length
     */
    public void moveTurtleForward (double length) {
        double deltaX = Math.sin(getTurtleAngle() * Math.PI / 180) * length;
        double deltaY = Math.cos(getTurtleAngle() * Math.PI / 180) * length;
        
        // If pen is down, create a new action and bind a new line
        if ( pen.get() )
        {
        	Line newLine = new Line();
        	setLineStyle(newLine);
        	myPane.getChildren().add(newLine);       
        	animationController.addTurtleToMove(this, 
        			new ArrayList<Double>(Arrays.asList(getTurtleX(), getTurtleY())),
        			new ArrayList<Double>(Arrays.asList(getTurtleX() + deltaX, getTurtleY() + deltaY)),
        			newLine);
        }
        // If not, just create new action
        else
        	animationController.addTurtleToMove(this, 
        			new ArrayList<Double>(Arrays.asList(getTurtleX(), getTurtleY())),
        			new ArrayList<Double>(Arrays.asList(getTurtleX() + deltaX, getTurtleY() + deltaY)));
        
        
        setCoordinates(getTurtleX() + deltaX, getTurtleY() + deltaY);
    }
    
    /**
     * Set the linestyle used by the turtle
     * @param line
     */
    private void setLineStyle(Line line){
        line.setStroke(myPreferences.getPenColor());
        line.setStrokeWidth(myPreferences.getPenWidth());
        if(myPreferences.isDashed()){
            line.getStrokeDashArray().add(DASH_LENGTH);
        }
    }
     
    /**
     * Updates an existing Line (used for animation)
     * @param line
     */
    public void updateLine(Line line, List<Double> newStartCoordinates, List<Double> newEndCoordinates)
    {
    	line.setStartX(DISPLAY_WIDTH / 2 + newStartCoordinates.get(0));
        line.setStartY(DISPLAY_HEIGHT / 2 - newStartCoordinates.get(1));
        
        line.setEndX(DISPLAY_WIDTH / 2 + newEndCoordinates.get(0));
        line.setEndY(DISPLAY_HEIGHT / 2 - newEndCoordinates.get(1));
    }
    
    /**
     * Clears all lines in the display (not only the ones created by the turtle)
     */
    public void clearDisplay () {
        // Deletes all lines
    	Iterator<Node> nodeIterator = myPane.getChildren().iterator();
    	
    	while ( nodeIterator.hasNext() )
    	{
    		Node node = nodeIterator.next();
    		
        	if (node.getClass() == Line.class)
        	{
        		nodeIterator.remove();
        	}

    	}
    }

    /**
     * Stamps an image of the turtle to the display. Simple copies a new ImageView with the turtle's
     * current parameters
     */
    @Override
    public void stamp () {
    	ImageView newStamp = new ImageView();
    	newStamp.setImage(body.getImage());
    	newStamp.setLayoutX(DISPLAY_WIDTH / 2 + getTurtleX());
    	newStamp.setLayoutY(DISPLAY_WIDTH / 2 + getTurtleY());
    	newStamp.setRotate(angle);
    	stamps.add(newStamp);
    	myPane.getChildren().add(newStamp);
    }

    /**
     * Goes through the stamp array list and takes them off the root's Node children.
     */
    @Override
    public int clearStamps () 
    {	
    	int num = 0;
    	
    	for ( ImageView stamp: stamps ){
    		myPane.getChildren().remove(stamp);
    		num = 1;
    	}
    	
        return num;
    }
    
    
    
    
    /**
     * ===================
     * GETTERS AND SETTERS
     * ===================
     */
    
    public double getTurtleX () {
        return x;
    }

    public double getTurtleY () {
        return y;
    }
    
    public boolean isActive () {
        return this.isActive;
    }

    public void setActive (boolean isActive) {
        this.isActive = isActive;
        if(isActive){
            toggleVisibility(1);
        }
        else{
            toggleVisibility(TRANSPARENT);
        }
    }

    public double getTurtleAngle () {
        return angle;
    }

    public void turnTurtle (double angle) 
    {
    	animationController.addTurtleToTurn(this, this.angle, this.angle + angle, (angle > 0));
    	this.angle += angle;
    }

    public void setTurtleAngle (double angle) 
    {
    	animationController.addTurtleToTurn(this, this.angle, angle, (angle > 0));
    	this.angle = angle;
    }
    
    public void setVisualAngle ( double angle )
    {
    	body.setRotate(angle);
    }

    public void turtlePenDown () {
        pen.set(true);
    }

    public void turtlePenUp () {
        pen.set(false);
    }

    public boolean isTurtlePenDown () {
        return pen.get();
    }

    public void hideTurtle () {
        body.setVisible(false);
    }

    public void showTurtle () {
        body.setVisible(true);
    }

    public boolean getTurtleVisibility () {
        return (body.getOpacity() == 1);
    }

    public void setTurtleCoordinates(double x, double y){
    	setCoordinates(x,y);
    	updateTurtleVisualPosition();
    }

    /**
     * Get the coordinates as list, instead of one by one.
     */
    public List<Double> getCoordinates() 
    {
    	return new ArrayList<>(Arrays.asList(x, y));
    }
    
    /**
     * ImageView Body Getter
     */
    public ImageView getBody () {
        return body;
    }

    @Override
    public TurtlePreferences getPreferences () {
        return myPreferences;
    }

    private void setVisualCoordinates (double newX, double newY) {
        body.setX(newX - DEFAULT_TURTLE_SIZE / 2);
        body.setY(newY - DEFAULT_TURTLE_SIZE / 2);
    }

    private void toggleVisibility (double newOpacity) {
        body.setOpacity(newOpacity);
    }
    
    private void setCoordinates (double x, double y) {        
        this.x = x;
        this.y = y;
    }

    public void updateTurtleVisualPosition (List<Double> newPosition) {
    	double newX = DISPLAY_WIDTH / 2 + newPosition.get(0);
    	double newY = DISPLAY_HEIGHT / 2 - newPosition.get(1);

        // When updating coordinates, compensate the X and Y because they reference the edge of the
        // image, not the center
        setVisualCoordinates(newX, newY);
    }

    // Takes current Turtle's Logo's (x,y) position and update the ImageView's javafx (x,y)
    public void updateTurtleVisualPosition () 
    {
    	updateTurtleVisualPosition(new ArrayList<Double>(Arrays.asList(getTurtleX(), getTurtleY())));
    }
}
