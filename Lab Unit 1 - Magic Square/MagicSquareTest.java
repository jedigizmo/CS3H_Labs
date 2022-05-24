/** 
 * The MagicSquareTest class gets user input to create 2d array
 *
 * @author Gikonyo Njendu
 * Collaborators: None
 * Teacher Name: Ms.Bailey
 * Period: 6
 * Due Date:8/18/2021
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class MagicSquareTest
{
	public static void main(String[] args)
	{
		System.out.println("Testing self-made square\n");
		boolean keepGoing = true;
		while (keepGoing)
		{
			testMySquare();
			System.out.println();
			keepGoing = checkContinue();
			System.out.println();
		}
		System.out.println();

		// Test the bonus	
		/*	
		System.out.println("Testing magic square maker\n");
		keepGoing = true;
		while (keepGoing)
		{
			testMagicMaker();
			System.out.println();
			keepGoing = checkContinue();
			System.out.println();
		}
		System.out.println();
	}
	*/
	/** Test the MagicSquare class by adding user-input values to the matrix.
	 */
	public static void testMySquare()
	{
		Scanner scan = new Scanner(System.in);
		int numRows = getPositiveInteger("Enter number of rows for square matrix: ");
		MagicSquare square = new MagicSquare(numRows);
		
		for (int k = 1; k <= numRows * numRows; k++)
		{
			System.out.print("Enter value to add: ");
			int val = scan.nextInt();
			square.add(val);
		}
		System.out.println();
		System.out.println("Magic Square: \n" + square);
		System.out.println();
		
		String result = square.isMagic();
		if (result.equals(MagicSquare.MAGIC_SUCCESS))
			System.out.println("It's a magic square!");
		else
			System.out.println("Not a magic square because " + result);
		System.out.println();
	}
	
	/** Create a magic square with a given number of rows/columns.
	 */
	public static void testMagicMaker()
	{
		Scanner scan = new Scanner(System.in);
		int numRows = 0;
		while (numRows % 2 == 0)
		{
			numRows = getPositiveInteger("Enter number of rows for magic square: ");
			if (numRows % 2 == 0)
				System.out.print("Must be an odd integer. Try again.\n");
		}
		int[][] magic = MagicSquare.makeMagic(numRows);
		
		MagicSquare square = new MagicSquare(numRows);
		for (int r = 0; r < numRows; r++)
			for (int c = 0; c < numRows; c++)
				square.add(magic[r][c]);
		System.out.println("Magic square:\n" + square);
		System.out.println();
		String result = square.isMagic();
		if (result.equals(MagicSquare.MAGIC_SUCCESS))
			System.out.println("It's a magic square!");
		else
			System.out.println("Not a magic square because " + result);
		System.out.println();
	}

	/** Input a positive integer from the user. Continue asking until positive integer is entered.
	 *  @return positive integer
	 */
	public static int getPositiveInteger(String prompt)
	{
		Scanner scan = new Scanner(System.in);
		int pos = -1;
		while (pos < 1)
		{
			System.out.print(prompt);
			if (scan.hasNextInt())
			{
				pos = scan.nextInt();
				if (pos < 1)
					System.out.println("Please enter an integer greater than 0.");
			}
			else
			{
				scan.nextLine();
				System.out.println("Please enter an integer greater than 0.");
			}
		}
		return pos;
	}

	/** Check if the user wants to continue.
	 *   @return true if the user wants to continue; false otherwise
	 */
	public static boolean checkContinue()
	{
		Scanner scan = new Scanner(System.in);
		System.out.print("Would you like to continue? ");
		String response = scan.next();
		return response.length() > 0 && response.substring(0, 1).equalsIgnoreCase("y");
	}

}