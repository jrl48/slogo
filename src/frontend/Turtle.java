package frontend;
import methodInterfaces.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Turtle
{
	private ImageView body;
	private double x;
	private double y;
	private boolean pen;
	
	private final double DEFAULT_TURTLE_SIZE = 30;
	
	public Turtle()
	{
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(UserInterface.DEFAULT_RESOURCE_PACKAGE+"turtle.png")); //TODO fix this up
		body = new ImageView(image);
		x = 0;
		y = 0;
		pen = false;
	}
	
	public ImageView getBody()
	{
		return body;
	}
	
	public void setCoordinates(double x, double y )
	{
		this.x = x;
		this.y = y;
	}
	
	public boolean penIsDown()
	{
		return pen;
	}
	
	// Both visual getters return the center of the turtle's ImageView body
	public double getVisualX()
	{
		return body.getX() + DEFAULT_TURTLE_SIZE / 2 ;
	}
	
	public double getVisualY()
	{
		return body.getY() + DEFAULT_TURTLE_SIZE / 2 ;
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}
	
	public void setNewImage()
	{
		// TODO: Scale it to DEFAULT TURTLE SIZE!
	}
	
	public double getSize()
	{
		return DEFAULT_TURTLE_SIZE;
	}
	
	public void setVisualCoordinates(double newX, double newY)
	{
		body.setX(newX - DEFAULT_TURTLE_SIZE/2);
		body.setY(newY - DEFAULT_TURTLE_SIZE/2);
	}
	
	public void togglePen()
	{
		pen = !pen;
	}
	
	public double getAngle()
	{
		return body.getRotate();
	}
	
	public void rotate(double angle)
	{
		body.setRotate(body.getRotate() + angle);
	}
	
	//HAVE TO ADD TO USE METHODS
	public void setAngle(double angle) {
		body.setRotate(angle);
	}
	
	public void putPenDown(){
		pen = true;
	}
	
	public void putPenUp(){
		pen = false;
	}
}
