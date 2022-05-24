/** 
 * The MagicSquare class determines if a 2d array is a "magic square"
 *
 * @author Gikonyo Njendu
 * Collaborators: None
 * Teacher Name: Ms.Bailey
 * Period: 6
 * Due Date: 8/18/2021
 */

public class MagicSquare
{
	public static final String MAGIC_SUCCESS = "successful";
	public static final String FAILED_NOT_ALL_VALUES = "not all values used";
	public static final String FAILED_ROW_SUM = "failed row sum";
	public static final String FAILED_COL_SUM = "failed column sum";
	public static final String FAILED_DIAG_SUM = "failed diagonal sum";
	public int[][] MagicArray;
	public int y = 0;
	public int x = 0;
	
	/*Sets the side lengths of the matrix 
	 *@param sideLength is the user input for the side lengths of the matrix.
	 */
	public MagicSquare(int sideLength)
	{
		MagicArray = new int [sideLength][sideLength];
	}
	/*Adds number to matrix
	 *@param number is the number to be added to matrix
	 */
	public void add(int number)
	{
		if (x < MagicArray[y].length - 1)
		{
			MagicArray[y][x] = number;
			x++; 
		}
		else if (y < MagicArray.length)
		{
			MagicArray[y][x] = number;
			x = 0;
			y++;
		}
	}
	/* Checks if matrix is a magic square
	 *@return returns whether or not the matrix is a magic square.
	 */
	public String isMagic()
	{	
	int [] checkArray = new int [(int) Math.pow(MagicArray.length , 2)];
	int aValue = (((int) Math.pow(MagicArray.length , 2)) * (((int) Math.pow(MagicArray.length , 2)) + 1)) / 2;
	int Hcnt1 = 0;
	int Hcnt2 = 0;
	int Vcnt1 = 0;
	int Vcnt2 = 0;
	int Dcnt1 = 0;
	int Dcnt2 = 0;
	int Tcnt = 0;
	for (int y1 = 0; y1 < MagicArray.length; y1++)
	{
	for (int x1 = 0; x1 < MagicArray[y1].length; x1++)
	{
		Tcnt += MagicArray[y1][x1];
		if ((y1 % 2) == 0)
		{
			Hcnt1 += MagicArray[y1][x1];
		}
		else 
		{
			Hcnt2 += MagicArray[y1][x1];
		}
		if (y1 == x1)
		{
			Dcnt1 += MagicArray[y1][x1];
		}
		else if ((y1 + x1) == ((MagicArray.length -1)))
		{
			Dcnt2 += MagicArray[y1][x1];
		}
		if ((y1 + x1) == ((MagicArray.length -1)) && (y1 == x1))
		{
			Dcnt2 += MagicArray[y1][x1];
		}
	}
		if ((Hcnt1 > 0) && (Hcnt2 > 0))
		{
			if (Hcnt1 != Hcnt2)
			{
				return FAILED_ROW_SUM;
			}
				else if (y1 % 2 == 1)
			{
				Hcnt1 = 0;
			}
			else 
			{
				Hcnt2 = 0;
			}
		}
	}
	for (int y2 = 0; y2 < MagicArray.length; y2++)
	{
	for (int x2 = 0; x2 < MagicArray[y2].length; x2++)
	{
		if ((y2 % 2) == 0)
		{
			Vcnt1 += MagicArray[x2][y2];
		}
		else 
		{
			Vcnt2 += MagicArray[x2][y2];
		}
	}
		if ((Vcnt1 > 0) && (Vcnt2 > 0))
		{
			if (Vcnt1 != Vcnt2)
			{
				return FAILED_COL_SUM;
			}
			else if ((y2 % 2) == 1)
			{
				Vcnt1 = 0;
			}
			else 
			{
				Vcnt2 = 0;
			}
	 	}
	}
	if (Dcnt1 != Dcnt2)
	{
		return FAILED_DIAG_SUM;
		
	}
	if (aValue != Tcnt)
	{
		return FAILED_NOT_ALL_VALUES;
	}
		return MAGIC_SUCCESS;
	}
	/*Turns the matrix into string
	 *@return returns the string
	 */
	 
	@Override
	public String toString()
	{
		String out = "";
		for (int y = 0; y < MagicArray.length; y ++)
		{
		for (int x = 0; x < MagicArray[y].length; x++)
		{
			out = out + String.format("%5d", MagicArray[y][x]);
		}
		out = out + "\n";
		}
		return out;
	}
	public static int[][] makeMagic(int sideLength)
	{
		return null;
	}
	
}