 /**
 * GridConnector.java
 * Class that holds the Gridconnector constructor and various helper methods
 *
 * @author Gikonyo Njendu
 * Collaborators: Takuto Ban, Ayon Das
 * Teacher Name: Ms.Bailey
 * Period: 6
 * Due Date: 2/18/2022
 */
 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
 
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
 
public class GridConnector
{
    /** Dimensions of grid */
    private int numRows;
    private int numCols;
   
    /** Grid of values */
    private String[][] grid;
   
    /** Set of all values in grid */
    private Set<String> allValues;
    
   /** boolean to track checked */
    private boolean[][] checked;
    
    public GridConnector(String inputFile)
    {
        loadFile(inputFile);
    }
 
    public void changeGrid(String newFile)
    {
        loadFile(newFile);
    }
   	/**getLargestBlock method finds the largest block of given string
   	 *in the 2d array by using nested for loops and a helper 
   	 *recursive method
   	 *@param value is the string to be checked in the 2d array
   	 *@return returns the largest block of the value given
   	 */
    public int getLargestBlock(String value)
    {
        int max = 0;
        for(int y = 0; y < numRows; y++){
        for(int x = 0; x < numCols; x++ ){
            max = Math.max(max, dfs(x,y,value));
        }
    }
    for(int y = 0; y < numRows; y++){
        Arrays.fill(checked[y], false);
    }
        return max;
    }
   /**dfs helper method finds the largest block of given string
   	 *in the 2d array recursively
   	 *@param value is the string to be check in the 2d array
   	 *@param x is the x coordinate of the string to be checked
   	 *@param y is the y coordinate of the sting to be checked
   	 *@return returns the largest block of the same value given
   	 */
    private int dfs(int x, int y, String val){
        if((y >= 0 && y < numRows) && (x >= 0 && x < numCols)){
        if(checked[y][x]){
            return 0;
        }
        checked[y][x] = true;
        if(grid[y][x].equals(val)){
            return 1 + dfs(x+1,y,val) + dfs(x-1,y,val) + dfs(x,y+1,val) + dfs(x,y-1,val);
        }
    }
        return 0;
    }
   
    /** Retrieve set of all values in grid
     *  @return set of values in grid
     */
    public String getAllValues()
    {
        if (allValues == null)
            return "";
 
        return allValues.toString();
    }
   
    /** Retrieve grid as a string
     *  @return grid as a string
     */
    @Override
    public String toString()
    {
        if (grid == null)
            return "";
       
        StringBuilder str = new StringBuilder();
        for (String[] row : grid)
        {
            for (String ch : row)
            {
                str.append(ch + " ");
            }
            str.append("\n");
        }
        return str.toString();
    }
   /** the loadFile method populates a 2d array of strings from a given
    *	inputed file name, as well as a set of unique string and
    *	fills the checked boolean array with false
     *  @param inputFile is the file to be loaded
     */
    private void loadFile(String inputFile)
    {
        try{
        allValues = new TreeSet<>();
        File f = new File(inputFile);
        Scanner sc = new Scanner(f);
        numRows = sc.nextInt();
        numCols = sc.nextInt();
        grid = new String[numRows][numCols];
        checked = new boolean[numRows][numCols];
        sc.nextLine();
        for(int y = 0; y < numRows; y++){
        for(int x = 0; x < numCols; x++ ){
        String tmp = sc.next();
        grid[y][x] = tmp.trim();
        allValues.add(tmp.trim());
        checked[y][x] = false;
        }
        sc.nextLine();
        }
        }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
       
    }
}
 

