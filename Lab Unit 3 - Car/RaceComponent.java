/** 
 * The RaceComponent draws the finish line, checks if the race is over, and moves the cars a random distance.
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

public class RaceComponent extends JComponent
{
	Car car1;
	Car car2;
	Rectangle finishLine;
	int startX = 10;
	/*RaceComponent creates the two car objects and the finishline
	 */
    public RaceComponent() 
    {
    	car1 = new Car(10,150, Color.green);
    	car2 = new Car(10,180, Color.blue);
    	finishLine = new Rectangle(450, 10, 6, 445);
    }
    /*paintComponent draws the cars and the finish line
	 *@param Graphics object to be passed
	 */
    public void paintComponent(Graphics gr)
    {
    	Graphics2D g2 = (Graphics2D) gr;
		g2.setColor(Color.black);
		g2.fill(finishLine);
		car1.draw(g2);
		car2.draw(g2);
    }
    /*advanceCars method moves the cars be a random amount and then repaints them
	 */
    public void advanceCars()
    {
    	car1.moveForward(((int)((Math.random() * 5)) + 1));
    	car2.moveForward(((int)((Math.random() * 5)) + 1));
    	repaint();
    }
    /*isRaceOver method checks if one of the cars has passed the finish line
	 */
	 public boolean isRaceOver()
	 {
	 	if ((car1.x + startX > 450) || car2.x + startX > 450)
	 	{
	 		return true;
	 	}
	 	else 
	 	{
	 		return false;
	 	}
	 }
	 /*restartRace method restarts the race is the cars have passed the finish line
	 */
	 public void restartRace()
	 {
	 	if(isRaceOver())
	 	{
	 		car1.x = startX;
	 		car2.x = startX;
	 	}
	 }
    
}