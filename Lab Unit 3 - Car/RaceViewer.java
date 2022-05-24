
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.Timer;

public class RaceViewer
{
	private static int DELAY = 20;
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		
		final int FRAME_WIDTH = 500;
		final int FRAME_HEIGHT = 500;
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setLocation(5, 5);
		frame.setTitle("Trucky 500 Track");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		RaceComponent race = new RaceComponent();
		frame.add(race);
		frame.setVisible(true);
		
		ActionListener listener = new CarTimer(race, race);
		Timer t = new Timer(DELAY, listener);
		t.start();
	}
	
}