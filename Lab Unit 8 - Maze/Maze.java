/**
 * Maze.java
 * Class that holds the Maze constructor, the populateMaze and the solveMaze method
 *
 * @author Gikonyo Njendu
 * Collaborators: Mithun Balasubramanian, Takuto Ban, Ayon Das
 * Teacher Name: Ms.Bailey
 * Period: 6
 * Due Date: 1/18/2022
 */


import java.awt.*;
import java.util.*;
import java.awt.Color;
import info.gridworld.grid.Grid;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import info.gridworld.world.World;
 
public class Maze extends AbstractMaze
{
    private Location start;
    private Location end;
    private static final Color wall = Color.GRAY;
    private static final Color startc = Color.GREEN;
    private static final Color endc = Color.RED;
    private static final Color path = Color.YELLOW;
    private static final Color blank = Color.WHITE;
    
    /** Constructor for the Maze class
	 */
    public Maze(){
        super();
    }
    
    /** populateMaze populates the maze with the walls and the start and end positions 
     *	as well iterating through the empty tiles with a 45% percent 
     *	chance of turning it into a wall
	 */
    public void populateMaze(){
        Grid<Color> maze = getGrid();
        int rows = maze.getNumRows();
        int col = maze.getNumCols();
        start = new Location((int) (Math.random() * rows - 1) + 1, 0);
        end = new Location((int) (Math.random() * rows - 1) + 1, col -1);
        for(int b = 0; b < rows; b++){
        maze.put(new Location(0,b), wall);
        maze.put(new Location(b,0), wall);
        maze.put(new Location(rows - 1,b), wall);
        maze.put(new Location(b,col - 1), wall);
        }
        for(int i = 1; i < rows - 1; i++){
            for(int z = 1; z < col - 1; z++){
                if((int) (Math.random() * 100) + 1 >= 55){
                    maze.put(new Location(i,z), wall);
                }
                else{
                    maze.put(new Location(i,z), blank);
                }
            }
        }
        maze.put(start, startc);
        maze.put(end, endc);
    }
    
    /** solveMaze uses a stack to solve the maze placing down a path for every tile that it visits
     *	when choosing a path to take it if there are no options it backtraces and checks again
	 *  @return true if the maze is solvable false if not
	 */
    public boolean solveMaze(){
        Deque<Location> solve = new LinkedList<>();
        Grid<Color> maze = getGrid();
        solve.push(start);
        boolean solved = false;
        boolean total = false;
        Location best = start;
        while(!solve.isEmpty() && !solve.peek().equals(end)){
            ArrayList<Location> temp = maze.getValidAdjacentLocations(solve.peek());
            int dic = Integer.MAX_VALUE;
            solved = false;
            for(Location loc : temp){
                if(loc.equals(end)){
                    best = loc;
                    solved = true;
                    total = true;
                    break;
                }
                int endd = solve.peek().getDirectionToward(end);
                int locd = solve.peek().getDirectionToward(loc);
                if(Math.abs(endd - locd) < dic){
                	Color tempc = maze.get(loc);
                	if((!tempc.equals(wall)&&(!tempc.equals(path)&&!tempc.equals(startc)))){
                    best = loc;
                    dic = Math.abs(endd - locd);
                    solved = true;
                	}
                }
            }
            if(!solved){
                solve.pop();
            }
            else if (solved && !total){
                solve.push(best);
                maze.put(best, path);
            }else{
                solve.push(best);
            }
        }
        return total;
    }
}

