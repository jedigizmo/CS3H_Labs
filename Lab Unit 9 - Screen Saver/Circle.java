/**
 *  Circle.java
 *  Represents the point and color of a circle to be drawn. The point is
 *  the upper-left corner of the bounding rectangle of the circle.
 */

import java.awt.Color;
import java.awt.Point;

public class Circle
{
	private Point upperLeft;
	private Color color;
	
	/** Creates a circle with a given upper-left corner and color
	 *  @param p the point for the upper-left corner of the circle's bounding rectangle
	 *  @param c the color to use when drawing this circle
	 */
	public Circle(Point p, Color c)
	{
		upperLeft = p;
		color = c;
	}
	
	/** Retrieves the upper-left corner of the circle's bounding rectangle
	 *  @return point of the upper-left corner
	 */
	public Point getUpperLeft()
	{
		return upperLeft;
	}
	
	/** Retrieves the circle's color
	 *  @return the circle's drawing color
	 */
	public Color getColor()
	{
		return color;
	}
	
	@Override
	public String toString()
	{
		return upperLeft + " " + color;
	}
}