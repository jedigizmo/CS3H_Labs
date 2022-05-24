 /**
 * ScreenSaverComponent.java
 * Class representing the creation of new circle objects to be drawn
 *
 * @author Gikonyo Njendu
 * Collaborators: Ayon Das, Takuto Ban, Jackie Lasut, Mithun Balasubramanian
 * Teacher Name: Ms.Bailey
 * Period: 6
 * Due Date: 2/1/2022
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JComponent;
import java.util.Queue;
import java.util.LinkedList;
 
public class ScreenSaverComponent extends JComponent
{
    // Starting color for drawing
    private static final Color STARTING_COLOR = Color.magenta;
   
    // Min and max values for translations of x and y
    private static final int MIN_CHANGE = 5;
    private static final int MAX_CHANGE = 50;
    private int max;
    private int diameter;
    private int chgX;
    private int chgY;
    private Queue<Circle> list;
    private Color clr;
    private int dirx;
    private int diry;
    private int numCircles;
    private int fade;
    private int fadeSub;
    private int r;
    private int g;
    private int b;
    private int fadecnt;
    private static final int COLOR_BOUND = 256;
    private static final int FADE_MAX = 255;
    private static final int MAX_MULTI = 3;
    private static final int FADE_MULTI = 2;
    private int x;
    private int y;
    
 	/** The ScreenSaverComponent constructor initializes the instance variables 
	 */
    public ScreenSaverComponent(int max, int diameter, int chgX, int chgY)
    {
        this.max = max;
        this.diameter = diameter;
        this.chgX = chgX;
        this.chgY = chgY;
        list = new LinkedList<>();
        clr = STARTING_COLOR;
        dirx = 1;
        diry = 1;
        numCircles = 0;
        fade = FADE_MAX;
        fadeSub = (int) (FADE_MAX/max);
        fadecnt = 0;
        r = STARTING_COLOR.getRed();
        g = STARTING_COLOR.getGreen();
        b = STARTING_COLOR.getBlue();
    }
 
    /** Add a new circle to be drawn and then draw all circles.
     *  @param g the Graphics object for drawing in this component
     */
    @Override
    public void paintComponent(Graphics g)
    {
    	
        Graphics2D gr2 = (Graphics2D) g;
        gr2.setColor(Color.BLACK);
        gr2.fill3DRect(0,0, getWidth(), getHeight(), true);
        int maxWidth = getWidth();
        int maxHeight = getHeight();
        addCircle();
        drawAll(gr2);
    }
   /** Draws out all the circles on the queue
	 *  @param gr the Graphics2D object for drawing the circles
	 */
    private void drawAll(Graphics2D gr)
    {
        Graphics2D gr2 = (Graphics2D) gr;
        for(Circle c : list)
        {
            gr2.setColor(c.getColor());
            gr2.fillOval(c.getUpperLeft().x, c.getUpperLeft().y, diameter, diameter);
        }
    }
   	/** addCircle method adds a new circle to the list, if the circle hits the edge then
   	 *	the direction, color, and fade resets, 
   	 *	if the list if full then the oldest gets removed
	 */
    private void addCircle()
    {
    	if(list.isEmpty()){
    	x = getWidth()/2;
    	y = getHeight()/2;
    	}
        if(x + ((chgX + diameter) * dirx) >= getWidth() || x + (chgX * dirx) <= 0)
        {
            dirx *= -1;
            chgX = (int)(Math.random() * (MAX_CHANGE - MIN_CHANGE)) + MIN_CHANGE;
            r = (int) (Math.random() * COLOR_BOUND);
            g = (int) (Math.random() * COLOR_BOUND);
            b = (int) (Math.random() * COLOR_BOUND);
            fade = FADE_MAX;
        }
        if(y + ((chgY + diameter) * diry) >= getHeight() || y + (chgY * diry) <= 0)
        {
            diry *= -1;
            chgY = (int)(Math.random() * (MAX_CHANGE - MIN_CHANGE)) + MIN_CHANGE;
            r = (int) (Math.random() * COLOR_BOUND);
            g = (int) (Math.random() * COLOR_BOUND);
            b = (int) (Math.random() * COLOR_BOUND);
            fade = FADE_MAX;
        }
        if(numCircles == max){
            list.poll();
            numCircles--;
        }
        fade -= fadeSub;
        fadecnt++;
        if(fadecnt >= max/MAX_MULTI){
        fade = FADE_MAX;
        fadecnt = 0;
        }
        y += (chgY * diry);
        x += (chgX * dirx);
        clr = new Color(r,g,b, fade);
        Circle add = new Circle((new Point(x, y)), clr);
        list.offer(add);
        numCircles++;
    }
}

