/**
 * Stack Lab - Maze Program
 * Using GridWorld, randomly populates a maze and tries to find a solution.
 */

import java.awt.Color;
import info.gridworld.grid.Grid;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import info.gridworld.world.World;

public abstract class AbstractMaze extends World<Color>
{
	// size of maze
	private static final int NUM_ROW = 19;
	private static final int NUM_COL = 19;
	
	// special messages
	private static final String REDRAW_MSG = "Press HOME to re-draw maze";
	private static final String START_MSG = "Press Step or END to solve maze\n" + REDRAW_MSG;

	/** 
	 * Instantiates a square maze and populates it
	 */
	public AbstractMaze() 
	{		
		super(new BoundedGrid<Color>(NUM_ROW, NUM_COL));
		populateMaze();
		setMessage(START_MSG);
	}
	
	/**
	 *  Solves the maze when the step button is pressed
	 */
	@Override
	public void step()
	{
		String message;
		if (solveMaze())
			message = "You're free!\n";
		else
			message = "You're stuck!\n";
		setMessage(message + REDRAW_MSG);
	}
	
	
	/**
	 * Suppresses menu from appearing when any Location is clicked
	 * @param loc the grid location that the user selected
	 * @return true so that nothing happens when loc is clicked
	 */
	@Override
	public boolean locationClicked(Location loc)
	{
		return true;
	}

	/**
	 * Create new maze when HOME is selected, solve maze when END selected,
	 * otherwise let GUI consume it
	 * @param description the string describing the key, in 
	 * <a href="http://java.sun.com/javase/6/docs/api/javax/swing/KeyStroke.html#getKeyStroke(java.lang.String)">this format</a>. 
	 * @param loc the selected location in the grid at the time the key was pressed
	 * @return true if the world consumes the key press, false if the GUI should
	 * consume it.
	 */
	@Override
	public boolean keyPressed(String description, Location loc)
	{	
		if (description.equals("HOME"))
		{
			populateMaze();
			setMessage(START_MSG);
			return true;
		}
		else if (description.equals("END"))     
		{
			step();
			return true;
		}	
		else
			return false;
	}	
	
	
	/** 
	 * Returns the maze information
	 * @return maze block as a string
	 */
	@Override
	public String toString() 
	{
		StringBuilder str = new StringBuilder();
		for (int r = 0; r < getGrid().getNumRows(); r++) 
		{
			for (int c = 0; c < getGrid().getNumCols(); c++) 
			{
				str.append(getGrid().get(new Location(r, c)));
				str.append(" ");
			}
			str.append("\n");
		}
		return str.toString();
	}


	public abstract void populateMaze();


	public abstract boolean solveMaze();
	
}