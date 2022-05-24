/**
 *  LenderTester tests the individual methods of the Lender class, making
 *  sure the monthly payment is calculated correctly.
 *  Expected monthly payments:
 *     $10,000 for 20 years:  98.76
 *     $123,456.78, 30 years: 586.7475970328959
 *     $3,000 1 year:         270.10717237710094
 *     $1,000 1 year:         100.0357241257003
 *  @author Tracy Ishman
 *  Date: 6 Oct 16
 */

public class LenderTester
{
	public static void main(String[] args)
	{
		// Create a Lender object and test it
		Lender test = new Lender("Wolf Loans", 3.75, 98.76, 15.00);
		System.out.println("Lender Name: " + test.getName());
		System.out.println("Annual Rate: " + test.getAnnualRate());
		System.out.println("Min Payment: " + test.getMinimumPayment());
		System.out.println("Monthly Fee: " + test.getMonthlyFee());
		System.out.println();
		System.out.println("Monthly Payment Calculations:");
		System.out.println("$10,000 20 years:   " + test.getMonthlyPayment(10_000, 20));
		System.out.println("$123,456.78 30 yrs: " + test.getMonthlyPayment(123_456.78, 30));
		System.out.println("$3,000 1 year:      " + test.getMonthlyPayment(3_000, 1));
		System.out.println("$1,000 1 year:      " + test.getMonthlyPayment(1_000, 1));
		System.out.println();
	}
}