/**
 * PatientTester.java
 * Tests the Patient class to ensure the Comparable implementation
 * works as requested.
 *
 * @author Tracy Ishman
 * @version 1.00 2017/3/17
 */

public class PatientTester 
{
	public static void main(String[] args) 
	{		
		// Test the basic methods
		Patient cranky = new Patient("Cranky Pants", PriorityRating.HIGH_PRIORITY, "Senioritis");
		System.out.println("Patient: " + cranky);
		System.out.println("Patient name: " + cranky.getName());
		System.out.println("Patient issue: " + cranky.getProblemDescription());
		System.out.println("Patient priority: " + cranky.getPriorityRating());
		System.out.println();
		
		// Create 2 objects for each possible PriorityRating
		Patient med1 = new Patient("M1", PriorityRating.MEDIUM_PRIORITY, "issue med1");
		Patient low1 = new Patient("L1", PriorityRating.LOW_PRIORITY, "issue low1");
		Patient high1 = new Patient("H1", PriorityRating.HIGH_PRIORITY, "issue hi1");
		Patient low2 = new Patient("L2", PriorityRating.LOW_PRIORITY, "issue low2");
		Patient high2 = new Patient("H2", PriorityRating.HIGH_PRIORITY, "issue hi2");
		Patient med2 = new Patient("M2", PriorityRating.MEDIUM_PRIORITY, "issue med2");

		// Test equals
		System.out.println("Testing .equals");
		System.out.println("med1.equals(med2)? F => " + (med1.equals(med2)));
		System.out.println("med1.equals(med1)? T => " + (med1.equals(med1)));
		System.out.println();
		
		// Test compareTo
		System.out.println("Testing .compareTo");
		System.out.println("high1 < high2? T => " + (high1.compareTo(high2) < 0));
		System.out.println("high1 < med1?  T => " + (high1.compareTo(med1) < 0));
		System.out.println("high1 < low1?  T => " + (high1.compareTo(low1) < 0));
		System.out.println("high2 < high1? F => " + (high2.compareTo(high1) < 0));
		System.out.println("low2 < high1?  F => " + (low2.compareTo(high1) < 0));
		System.out.println("low2 < med1?   F => " + (low2.compareTo(med1) < 0));
		System.out.println("low2 < low1?   F => " + (low2.compareTo(low1) < 0));
		System.out.println("low1 < low2?   T => " + (low1.compareTo(low2) < 0));
		System.out.println("med1 < low2?   T => " + (med1.compareTo(low2) < 0));
		System.out.println("med2 < med1?   F => " + (med2.compareTo(med1) < 0));
		System.out.println("med1 < med2?   T => " + (med1.compareTo(med2) < 0));
		System.out.println();
	}
}
