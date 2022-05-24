/** 
 * The Car class holds the car constructor, draws the car onto the frame, and updates the location of the car when it moves.
 *
 * @author Gikonyo Njendu
 * Collaborators: Nathan Ma
 * Teacher Name: Ms.Bailey
 * Period: 6
 * Due Date: 9/17/2021
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
	int wheel1AddX = 2;
	int wheel2Addx = 18;
	int wheelAddy = 17;
	int wheelsize = 10;
	int carbody = 20;
	int windowsize = 5;
	int windowXAdd = 25;
	int windowYAdd = 4;
	int frontXAdd = 22;
	int frontSizeW = 10;
	int frontSizeH = 18;
	int leftCurve = 2;
	int rightCurve = 5;
	
	/*Car constructor creates the Car object
	 *@param the x coordinate of the car, the y coordinate of the car and the color of the car
	 */
	public Car (int x , int y, Color c)
	{
		this.x = x;
		this.y = y;
		this.c = c;
	}
	/*Draw method draws the various parts of the car
	 *@param the Graphics2D to be passed
	 */
	@Override
	public void draw (Graphics2D g)
	{
		Graphics2D gr = (Graphics2D) g;
		gr.setColor(c);
		rect = new Rectangle(x, y, carbody, carbody);
		gr.fill(rect);
		wheel1 = new Ellipse2D.Double(x + wheel1AddX, y + wheelAddy, wheelsize, wheelsize);
		wheel2 = new Ellipse2D.Double(x + wheel2Addx, y + wheelAddy, wheelsize , wheelsize);
		frontSeat = new RoundRectangle2D.Double(x + frontXAdd, y, frontSizeW, frontSizeH, leftCurve, rightCurve);
		gr.setColor(Color.black);
		gr.fill(wheel1);
		gr.fill(wheel2);
		gr.fill(frontSeat);
		gr.draw(rect);
		window = new Rectangle(x + windowXAdd, y + windowYAdd, windowsize, windowsize);
		gr.setColor(Color.cyan);
		gr.fill(window);
	}
	@Override
	/*moveForward method moves the car and car parts forward.
	 *@param the amount to move the car by
	 */
	public void moveForward(int dx)
	{
		x += dx;
	}
	/*Rectangle method returns the rectangle bounding around the car.
	 *@return the rectangle bounding around the car
	 */
	public Rectangle getBox()
	{
		return rect;
	}
}