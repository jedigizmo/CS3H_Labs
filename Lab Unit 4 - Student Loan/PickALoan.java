/** 
 * The PickALoan gets the user input of input file and then calculates the best lender 
 *
 * @author Gikonyo Njendu
 * Collaborators: Nathan Ma, Neil Gupta
 * Teacher Name: Ms.Bailey
 * Period: 06
 * Due Date: 9/28/2021
 */
 
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class PickALoan 
{
	public static void main(String[] args)  
	{
		boolean keepGoing = true;
		while (keepGoing)
		{
			try{
			// Ask user for input file 
			String inputFileName = getInputFileName(); 
			File inputFile = new File(inputFileName);
			// Add code here to read lender data, build output file name,
			// and determine the best student loan options
			String outputFileName = getOutputFileName(inputFileName);
			Scanner scan = new Scanner(inputFile);
			List<Lender> lenderList = readLenderData(scan);
			determineBestLoan(scan, lenderList, outputFileName);
			// Check if user wants to continue
			keepGoing = checkContinue();
			}
			catch (FileNotFoundException e)
			{
				System.out.println(e.getMessage());
			}
		}
	}
	
	/** Retrieves the name of an existing input file from the user
	 *  @return file name of an existing text file to read
	 */
	private static String getInputFileName()
	{
		Scanner userinput = new Scanner(System.in);
		System.out.print("Enter file with loan information: ");
		String filename = userinput.nextLine();
		File test = new File(filename);
		if (test.exists())
		{
			return filename;
		}
		System.out.println("File not found. Try again.");
		return getInputFileName();
	}
	/** Creates the name of the output file
	 *  @param name of the input file
	 *  @return the file name of the output file
	 */
	private static String getOutputFileName(String inputName)
	{
		String[] fileName = inputName.split("\\.");
		String outFile = fileName[0] + ".out";
		System.out.println("Recommendations written to " + outFile);
		return outFile;
	}
	/** Checks if the user wants to continue using the program
	 *  @return true if the user wants to continue or false if the user does not
	 */
	private static boolean checkContinue()
	{
		Scanner userinput = new Scanner(System.in);
		System.out.print("Would you like to continue <yes/no>? ");
		String con = userinput.nextLine();
		con = con.toLowerCase();
		if (con.substring(0,1).equals("y"))
		{
			return true;
		}
		return false;
	}
	/** Reads the lender data from the input file and creates lender objects
	 *  @param scam scanner used to read 
	 *  @return the list of Lender objects 
	 */
	private static List<Lender> readLenderData(Scanner scan) 
	{
		int num = scan.nextInt();
		scan.nextLine();
		List<Lender> outList = new ArrayList<>();
		for (int x = 0; x < num; x++)
		{
			String name = scan.nextLine();
			Double intrestRate = scan.nextDouble();
			Double minMonthlyPay = scan.nextDouble();
			Double monthlyFee = scan.nextDouble();
			outList.add(new Lender(name, (intrestRate/12), minMonthlyPay, monthlyFee));
			scan.nextLine();
		}
		return outList;
	}
	/** Determines which lender is the best for the student
	 *  @param scan the scanner used to get file input
	 *	 @param lenders list of lender objects 
	 *  @param outputFile out file where the best lender information will be written to
	 *	 @throws FileNotFoundException if file does not exist
	 */
	private static void determineBestLoan(Scanner scan, List<Lender> lenders, String outputFile)  
	{
		try {
		PrintWriter outPrint = new PrintWriter(outputFile);
		int numSt = scan.nextInt();
		scan.nextLine();
		for (int x = 0; x < numSt; x++)
		{
			String nameSt = scan.nextLine();
			double principleSt = scan.nextDouble();
			int yearsSt = scan.nextInt();
			scan.nextLine();
			double lowestPay = Double.MAX_VALUE;
			int indLender = 0;
			for (int i = 0; i < lenders.size(); i++)
			{
				if (lowestPay > lenders.get(i).getMonthlyPayment(principleSt, yearsSt))
				{
					lowestPay = lenders.get(i).getMonthlyPayment(principleSt, yearsSt);
					indLender = i;
				}
			}
			outPrint.printf(nameSt + " should choose the loan from " + lenders.get(indLender).getName() + " for $.2f per month.%n", lowestPay);
		}
		outPrint.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
		
	}
}