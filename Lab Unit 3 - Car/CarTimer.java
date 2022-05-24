/**
 * @(#)CarTimer.java
 *
 *
 * @author 
 * @version 1.00 2021/9/15
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarTimer implements ActionListener {
	private RaceComponent Car1;
	private RaceComponent Car2;

   public CarTimer(RaceComponent component1, RaceComponent component2)
	{
		Car1 = component1;
		Car2 = component2;
	}
	public void actionPerformed(ActionEvent event)
	{
		Car1.advanceCars();
		Car1.restartRace();
	}
    
}