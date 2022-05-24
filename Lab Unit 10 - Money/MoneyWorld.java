/**
 * MoneyWorld.java
 * Class that holds the MoneyWorld constructor and various helper methods
 *
 * @author Gikonyo Njendu
 * Collaborators: Takuto Ban, Ayon Das
 * Teacher Name: Ms.Bailey
 * Period: 6
 * Due Date: 2/15/2022
 */
 
import info.gridworld.grid.Grid;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import info.gridworld.world.World;

public class MoneyWorld extends World<String>
{
	/** Message for help area */
	private static final String HELP_MSG 
		= "Click on grid cell to pick-up money; STEP to reset world";
	
	/** grid to cleanup */
	private Grid<String> grid;
	private static final String FIVE = "5";
	private static final String TEN = "10";
	private static final String TWENTY = "20";
	private static final String FIFTY = "50";
	private static final String PLUS = "+";
	private static final String MINUS = "-";
	private static final double CHANCE = 0.65;
	
	private static final String[] MONEY_ARR = {FIVE,TEN,TWENTY,FIFTY};
	private static final String[] ITEM_ARR = {PLUS, MINUS};
	
	private int total;
	private static final String CLEAN = "*";

	/**
	 * Create a world of letters of size rows x cols
	 * @param rows number of rows in world
	 * @param cols number of cols in world
	 */
	public MoneyWorld(int rows, int cols) 
	{
		super(new BoundedGrid<String>(rows, cols));
		total = 0;
		grid = getGrid();
		fillWorld();
		setMessage("Total amount picked up: $" + total + " "  + HELP_MSG);
	}
	
	/**
	 *  Refill world when the step button is pressed
	 */
	@Override
	public void step()
	{
		total = 0;
		setMessage("Total amount picked up: $" + total + " "  + HELP_MSG);
		fillWorld();
	}
	
	
	/**
	 * Suppresses menu from appearing when any Location is clicked
	 * @param loc the grid location that the user selected
	 * @return true to indicate click was processed
	 */
	@Override
	public boolean locationClicked(Location loc)
	{
		int tot = pickupMoney(loc);
		total += tot;
		setMessage("Total amount picked up: $" + total + " "  + HELP_MSG);
		return true;
	}
	/**
	 * Fills the grid with random numbers from 5,10,20,50 and 
	 * random symbols from + and -
	 */
	private void fillWorld()
	{
		Grid<String> grid = getGrid();
		int rows = grid.getNumRows();
        int col = grid.getNumCols();
        for(int i = 0; i < rows; i++){
            for(int z = 0; z < col; z++){
                if(Math.random() > CHANCE){
                    grid.put(new Location(i,z), MONEY_ARR[((int) (Math.random() * (MONEY_ARR.length)))]);
                }
                else{
                    grid.put(new Location(i,z), ITEM_ARR[((int) (Math.random() * (ITEM_ARR.length)))]);
                }
            }
        }
	}
	/**The pickupMoney method picks up money around a given location recursively
	 * @return the amount of money picked up around the location
	 * @param loc is the location around which the money is being picked up from
	 */
	private int pickupMoney(Location loc)
	{
		int out = 0;
		Grid<String> grid = getGrid();
		if((loc.getRow() >= 0 && loc.getRow() < grid.getNumRows())){
		if((loc.getCol() < grid.getNumCols() && loc.getCol() >= 0)){
		if(!grid.get(loc).equals(PLUS)){
		if((!grid.get(loc).equals(MINUS) && !grid.get(loc).equals(CLEAN))){
			out += Integer.parseInt(grid.get(loc));
			grid.put(loc, CLEAN);
			out = pickupMoney(new Location(loc.getRow() - 1, loc.getCol())) + out;
			out = pickupMoney(new Location(loc.getRow() + 1, loc.getCol())) + out;
			out = pickupMoney(new Location(loc.getRow(), loc.getCol() - 1)) + out;
			out = pickupMoney(new Location(loc.getRow(), loc.getCol() + 1)) + out;
		}
		}
		}
		}
		return out;
	}
	
	/** 
	 * Run the money world
	 */	
	public static void main(String[] args)
	{
		MoneyWorld myWorld = new MoneyWorld(10, 10);
		myWorld.show();
	}
}
