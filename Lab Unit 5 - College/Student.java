/*
 *	Student.java
 *	Represents a single student
 */

public class Student implements Comparable<Student>
{
	private String lastName;
	private String firstName;
	private int id;
	private double gpa;
	
	/** Construct student with unknown name, ID, GPA
	 */
	public Student() 
	{
		lastName = firstName = "Unknown";
		id = -1;
		gpa = 0;
	}
	
	/** Construct student with give name, ID, and GPA
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
	
	/** @return the student's name as last, first
	 */
	public String getName() 
	{
		return lastName + ", " + firstName;
	}
	
	/** @return the student's last name
	 */
	public String getLastName() 
	{
		return lastName;
	}
	
	/** @return the student's first name
	 */
	public String getFirstName() 
	{
		return firstName;
	}
	
	/** @return the student's ID
	 */
	public int getID() 
	{
		return id;
	}
	
	/** @return the student's GPA
	 */
	public double getGPA() 
	{
		return gpa;
	}
	
	/** @return the student's information as a string
	 */
	@Override
	public String toString() 
	{
		return getName() + " " + getID() + " " + getGPA();
	}
	
	/** @param obj Student to compare with this
	 *  @return true if this and obj have same ID;
	 *          false otherwise
	 */
	@Override
	public boolean equals(Object obj) 
	{
		if (obj == null || getClass() != obj.getClass())
			return false;
			
		Student stu = (Student) obj;
		
		return this.getID() == stu.getID();
	}
				  
	/** @return hash code for this student
	 */
	@Override
	public int hashCode() 
	{
		return getID();
	}

	/** Compare this Student to other based on its ID
	 *  @param other the other Student for comparison
	 *  @return negative integer if this < other
	 *          0 if this.equals(other)
	 *          positive integer if this > other
	 */
	public int compareTo(Student other) 
	{
		return this.getID() - other.getID();
	}
}