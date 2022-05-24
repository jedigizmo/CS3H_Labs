
public class MazeRunner 
{
	public static void main(String[] args) 
	{
 		System.setProperty("info.gridworld.gui.tooltips", "hide");   		  
 		System.setProperty("info.gridworld.gui.selection", "hide");   	 		    
		System.setProperty("info.gridworld.gui.frametitle", "Maze Lab");
		Maze maze = new Maze();
		maze.show();

	}
}