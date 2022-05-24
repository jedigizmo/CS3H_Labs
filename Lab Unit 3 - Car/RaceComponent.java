/**
 * @(#)RaceComponent.java
 *
 *
 * @author 
 * @version 1.00 2021/9/10
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
    public RaceComponent() 
    {
    	car1 = new Car(10,150, Color.green);
    	car2 = new Car(10,180, Color.blue);
    	finishLine = new Rectangle(450, 10, 5, 445);
    }
    public void paintComponent(Graphics gr)
    {
    	Graphics2D g2 = (Graphics2D) gr;
		g2.setColor(Color.black);
		g2.fill(finishLine);
		car1.draw(g2);
		car2.draw(g2);
    }
    
    public void advanceCars()
    {
    	car1.moveForward(((int)((Math.random() * 5)) + 1));
    	car2.moveForward(((int)((Math.random() * 5)) + 1));
    	repaint();
    }
	 public boolean isRaceOver()
	 {
	 	if ((car1.x + 10 > 450) || car2.x + 10 > 450)
	 	{
	 		return true;
	 	}
	 	else 
	 	{
	 		return false;
	 	}
	 }
	 public void restartRace()
	 {
	 	if(isRaceOver())
	 	{
	 		car1.x = 10;
	 		car2.x = 10;
	 	}
	 }
    
}