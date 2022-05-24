/**
 * Racer.java
 * Interface defining methods that different types of 
 * racers need for drawing in a graphics window.
 *
 * @author Tracy Ishman
 * @version 1.00 2011/9/8
 */

import java.awt.Graphics2D;
import java.awt.Rectangle;

public interface Racer 
{
	/** Draws the racer using the given graphics context
	 *  @param gr the graphics context for drawing
	 */
	void draw(Graphics2D gr);
	
	/** Advances the racer forward in the race by amount
	 *  @param amount the number of pixels to advance
	 */
	void moveForward(int amount);
    
	/** Retrieves the shape's bounding rectangle
	 *  @return the bounding rectangle that encompasses this shape
	 */
	Rectangle getBox();
}

