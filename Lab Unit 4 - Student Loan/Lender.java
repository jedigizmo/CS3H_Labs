/** 
 * The Lender Class creates the lender objects 
 *
 * @author Gikonyo Njendu
 * Collaborators: Nathan Ma, Neil Gupta
 * Teacher Name: Ms.Bailey
 * Period: 06
 * Due Date: 9/28/2021
 */
public class Lender 
{
	private static final int MONTHS_PER_YEAR = 12;
	
	private String lender;			     // name of lending institution
	private double monthlyRate;	    // monthly interest rate
	private double minPayment;      // minimum payment amount
	private double monthlyFee;       // additional monthly fee 
	
	/** Constructs a Lender for the given lending institution
	 *  given the loan information
	 *  @param lender name of lending institution
	 *  @param rate annual interest rate, where 10.5 means 10.5%
	 *  @param minPayment minimum monthly payment amount
	 *  @param fee an additional monthly fee
	 */
	public Lender(String lender, double rate, double minPayment, double fee) 
	{
		this.lender = lender;
		this.monthlyRate = rate / MONTHS_PER_YEAR / 100;
		this.minPayment = minPayment;
		this.monthlyFee = fee;
	}
	/** Calculates the monthly payment for the given lender
	 *  @param loanAmount amount loaned out by student
	 *  @param years number of years loaned for 
	 */
	public double getMonthlyPayment(double loanAmount, int years) 
	{
		double pay = loanAmount * (Math.pow((1 + monthlyRate), (years * 12))) * (monthlyRate / (Math.pow((1 + monthlyRate), (years * 12)) - 1)) + getMonthlyFee();
		if (pay <= minPayment) 
		{
			return minPayment;
		}
		return pay;
	}
	
	/** Retrieves the lender's name
	 *  @return name of lender
	 */
	public String getName() 
	{
		return lender;
	}
		 	
	/** Retrieves the annual interest rate
	 *  @return annual interest rate
	 */
	public double getAnnualRate() 
	{
		return monthlyRate * MONTHS_PER_YEAR * 100;
	}
		 	
	/** Retrieves the minimum monthly payment to be paid
	 *  @return minimum monthly payment amount
	 */
	public double getMinimumPayment() 
	{
		return minPayment;
	}
	
	/** Retrieves the mandatory monthly fee
	 *  @return additional monthly fee
	 */
	public double getMonthlyFee() 
	{
		return monthlyFee;
	}

}