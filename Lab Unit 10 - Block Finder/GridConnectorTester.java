

import java.io.File;
import java.util.Scanner;

public class GridConnectorTester
{
	public static void main(String[] args)
	{
		// Continue to read in files until user chooses to quit
		GridConnector grid = null;
		Scanner scan = new Scanner(System.in);
		boolean keepGoing = true;
		while (keepGoing)
		{
			String fileName = getFileName("Enter name of file to read: ");
			if (fileName == null)
				keepGoing = false;
			else
			{
				if (grid == null)
					grid = new GridConnector(fileName);
				else
					grid.changeGrid(fileName);
				
				// find the largest block for various values in the grid
				blockTester(grid);
				
				keepGoing = checkContinue("Do you want to continue (y/n)? ");
				System.out.println();
			}
		}		
	}

	/** Find the largest block for various values in the given grid
	 *  @param grid the GridConnector to test
	 */
	public static void blockTester(GridConnector grid)
	{
		// Show grid and available values
		System.out.println();
		System.out.println("Grid: \n" + grid);
		System.out.println("Values in grid: " + grid.getAllValues());

		// Continue to read in values to check until user chooses to quit
		Scanner scan = new Scanner(System.in);
		boolean keepChecking = true;
		while (keepChecking)
		{
			System.out.println();
			System.out.print("Enter value to find biggest block (<ENTER> to quit): ");
			String value = scan.nextLine();
			if (value.length() == 0)
			{
				keepChecking = false;					
			}
			else
			{
				int biggestBlock = grid.getLargestBlock(value);
				System.out.println("Biggest block of " + value + ": " 
					+ biggestBlock);
			}
		}
		System.out.println();
	}

	/** Retrieve the name of an existing file for input using the given prompt
	 *  @param prompt the user prompt for input
	 *  @return the name of an existing file, null if the user wants to quit
	 */
	public static String getFileName(String prompt)
	{
		Scanner scan = new Scanner(System.in);
		String fileName = null;
		boolean stopLoop = false;
		while (!stopLoop)
		{
			System.out.print(prompt);
			fileName = scan.nextLine();
			
			File checkFile = new File(fileName);
			if (checkFile.exists())
			{
				stopLoop = true;
			}
			else
			{
				boolean saysYes = checkContinue(fileName 
					+ " does not exist. Do you want to try again (y/n)? ");
				if (!saysYes)
				{
					fileName = null;
					stopLoop = true;
				}
				System.out.println();
			}
		}
		return fileName;
	}
	
	/** Check if the user wants to continue
	 *  @param prompt the prompt to give the user
	 *  @return true if the user wants to continue, false otherwise
	 */
	public static boolean checkContinue(String prompt)
	{
		Scanner scan = new Scanner(System.in);
		System.out.print(prompt);
		String reply = scan.next().toLowerCase();
		return reply.length() > 0 && reply.charAt(0) == 'y';
	}
}
