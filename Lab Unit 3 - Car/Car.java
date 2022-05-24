/**
 * @(#)Car.java
 *
 *
 * @author 
 * @version 1.00 2021/9/9
 */

import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

public class Car implements Racer
{
	int x;
	int y;
	Color c;
	Rectangle rect = new Rectangle();
	Ellipse2D.Double wheel1 = new Ellipse2D.Double();
	Ellipse2D.Double wheel2 = new Ellipse2D.Double();
	RoundRectangle2D frontSeat = new RoundRectangle2D.Double();
	Rectangle window = new Rectangle();
	public Car (int x , int y, Color c)
	{
		this.x = x;
		this.y = y;
		this.c = c;
	}
	@Override
	public void draw (Graphics2D g)
	{
		Graphics2D gr = (Graphics2D) g;
		gr.setColor(c);
		rect = new Rectangle(x, y, 20, 20);
		gr.fill(rect);
		wheel1 = new Ellipse2D.Double(x + 2, y + 17, 10 , 10);
		wheel2 = new Ellipse2D.Double(x + 18, y + 17, 10 , 10);
		frontSeat = new RoundRectangle2D.Double(x + 22, y, 10, 18, 2, 5);
		gr.setColor(Color.black);
		gr.fill(wheel1);
		gr.fill(wheel2);
		gr.fill(frontSeat);
		gr.draw(rect);
		window = new Rectangle(x + 25, y + 4, 5, 5);
		gr.setColor(Color.cyan);
		gr.fill(window);
	}
	@Override
	public void moveForward(int dx)
	{
		x += dx;
	}
	public Rectangle getBox()
	{
		return rect;
	}
}