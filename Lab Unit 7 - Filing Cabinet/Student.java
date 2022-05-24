/** 
 *  The Student class is an immutable class that stores a student's name, ID, and GPA.
 *  Students are ordered by last name, then first name, then ID, then GPA.
 *	 @author Tracy Ishman
 *  Date:   2016-02-01
 *	 Period: HCS3, all periods
 */

public class Student implements Comparable<Student>
{
	/** Constant for testing GPA equality */
	private static final double GPA_THRESHOLD = 0.00000001;
	
	/** Storing the student's first/last names, ID, and GPA */
	private String lastName;
	private String firstName;
	private int id;
	private double gpa;
	
	/** Constructs student with unknown name, ID, GPA
	 */
	public Student() 
	{
		lastName = firstName = "Unknown";
		id = -1;
		gpa = 0;
	}
	
	/** Constructs a student with the given name, ID, and GPA 
	 *  @param last the student's last name
	 *  @param first the student's first name
	 *  @param myID the student's ID
	 *  @param myGPA the student's GPA
	 */
	public Student(String last, String first, int myID, double myGPA) 
	{
		lastName = last.trim();
		firstName = first.trim();
		id = myID;
		gpa = myGPA;
	}
	
	/** Retrieves this student's name in format "Last, First"
	 *  @return the student's name as "Last, First"
	 */
	public String getName() 
	{
		return lastName + ", " + firstName;
	}
	
	/** Retrieves this student's last name
	 *  @return the student's last name
	 */
	public String getLastName() 
	{
		return lastName;
	}
	
	/** Retrieves this student's first name
	 *  @return the student's first name
	 */
	public String getFirstName() 
	{
		return firstName;
	}
	
	/** Retrieves this student's ID number
	 *  @return the student's ID
	 */
	public int getID() 
	{
		return id;
	}
	
	/** Retrieves this student's GPA
	 *  @return the student's GPA
	 */
	public double getGPA() 
	{
		return gpa;
	}
	
	/** Retrieves this student's information
	 *  @return the student's information as a string
	 */
	@Override
	public String toString() 
	{
		return getName() + " " + getID() + " " + getGPA();
	}
	
	/** Tests whether or not 2 students are the same 
	 *  @param obj Student to compare with this
	 *  @return true if this and obj have same name, ID, and GPA;
	 *          false otherwise
	 */
	@Override
	public boolean equals(Object obj) 
	{
		if (obj == null || getClass() != obj.getClass())
			return false;
			
		Student stu = (Student) obj;
		
		return this.getLastName().equalsIgnoreCase(stu.getLastName()) &&
		       this.getFirstName().equalsIgnoreCase(stu.getFirstName()) &&
		       this.getID() == stu.getID() && 
		       Math.abs(this.getGPA() - stu.getGPA()) < GPA_THRESHOLD;
	}
				  
	/** Retrieves the hash code for this student
	 *  @return hash code for this student
	 */
	@Override
	public int hashCode() 
	{
		return getLastName().hashCode() + getFirstName().hashCode() +
		       getID() + (new Double(getGPA())).hashCode();
	}
	
	/** Compares two students based first on student name, then ID, then GPA
	 *  @param other the student to check
	 *  @return 0 if this.equals(other); < 0 if this < other; > 1 if this > other
	 */
	@Override
	public int compareTo(Student other)
	{
		if (this.equals(other))
			return 0;
		
		// Check last names first	
		int diff = this.getLastName().compareToIgnoreCase(other.getLastName());
		if (diff == 0)
		{
			// check first names only if last names the same
			diff = this.getFirstName().compareToIgnoreCase(other.getFirstName());
			if (diff == 0)
			{
				// check IDs only if first names also the same
				diff = this.getID() - other.getID();
				if (diff == 0)
				{
					// check GPAs only if IDs also the same
					if (this.getGPA() < other.getGPA())
						diff = -1;
					else
						diff = 1;
				}
			}
		}
		return diff;
		
	}
}