// Ignore this for now!

package frontend;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class LineObject 
{	
	private ArrayList<Shape> line;
	private static final double DOT_SPACING = 3;
	private static final double DASH_SPACING = 2;
	private static final double DASH_LENGTH = 3;
	private static final double ERROR_MARGIN = 1;
	
	private double initX;
	private double initY;
	private double endX;
	private double endY;
	private double width;
	private LineType lineType;
	
	public enum LineType
	{
		SOLID, DOTTED, DASHED
	}
	
	public LineObject( LineType lineType, double initX, double initY, double endX, double endY, double width )
	{
		line = new ArrayList<Shape>();
		
		this.lineType = lineType;
		this.initX = initX;
		this.initY = initY;
		this.endX = endX;
		this.endY = endY;
		this.width = width;
	}
	
	public void drawLine()
	{
		
	}
	
	private void createLine()
	{
		// double length = Math.sqrt(Math.pow((initX - endX), 2) + Math.pow((initY - endY),2) );
		double stepX = (endX - initX) / ( DOT_SPACING + width);
		double stepY = (endY - initY) / ( DOT_SPACING + width);
		
		double i = initX;
		double j = initY;
		
		while ( Math.abs(i - endX) > ERROR_MARGIN && Math.abs(j - endY) > ERROR_MARGIN )
		{
			line.add(e)
		}
	}

	private Shape createShape(double x, double y)
	{
		Shape newShape;
		
		switch ( lineType )
		{
		case DOTTED:
			newShape = new Circle(x, y, width);
			breal;
		case DASHED:
			newShape = new Line(x, y, )
		}
		else 
	}
}
