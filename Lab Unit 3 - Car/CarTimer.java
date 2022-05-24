/** 
 * The CarTimer class listens and then advances the car or checks if the car has reached the finish line.
 *
 * @author Gikonyo Njendu
 * Collaborators: Nathan Ma
 * Teacher Name: Ms.Bailey
 * Period: 6
 * Due Date: 9/17/2021
 */
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarTimer implements ActionListener {
	private RaceComponent Car1;
	private RaceComponent Car2;
	
	/*CarTimer method makes the race component
	 *@param the first and second car to be moved
	 */
   public CarTimer(RaceComponent component1 ) 
	{
		Car1 = component1;
	}
	
	/*actionPerformed method is the action to be performed everytime the timer ends
	 *@param event when the timer ends
	 */
	public void actionPerformed(ActionEvent event)
	{
		Car1.advanceCars();
		Car1.restartRace();
	}
    
}