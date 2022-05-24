 /**
 * Patient.java
 * Patient class holds the constructor method and various helper methods
 *
 * @author Gikonyo Njendu
 * Collaborators: Takuto Ban
 * Teacher Name: Ms.Bailey
 * Period: 6
 * Due Date: 03/31/2022
 */
 
import java.io.*;
import java.util.*;
 
public class Patient implements Comparable<Patient>
{
    private static int overpos = 1;
    private String name;
    private String prob;
    private PriorityRating prio;
    private int pos;
    
   	/** Constructor method for Patient class
		@param name is the name of the patient to be added
		@param prio is the priority of the patient to be added
		@param prob is the description of the problem 
	 */
    public Patient(String name, PriorityRating prio, String prob){
        this.name = name;
        this.prob = prob;
        this.prio = prio;
        pos = overpos;
        overpos++;
    }
    /** compareTo is the overrriden compareto method, that
     *	gives the most urgent case the highest priority, or if 
     *	the priority is the same, then in the order they arrived
	 *	@param other is the other patient to be compared
	 * 	@returns the integer comparison of the two patients
	 */
    public int compareTo(Patient other)
    {
        if(prio.equals(other.getPriorityRating())){
            return (pos - other.getPosition());
        }
        return prio.compareTo(other.getPriorityRating());
    }
    
   /** Grabs the priority rating of the patient
	 *  @returns the PriorityRating enum of the patient
	 */
    public PriorityRating getPriorityRating(){
        return prio;
    }
    /** getPosition is a helper method to access the order in which a
     *	patient had arrived
     *	@return the int position of the place of the patient arrival
	 */
    private int getPosition(){
        return pos;
    }
    /** getName grabs the name of the patient
	 *  @returns the string of the name of the patient
	 */
    public String getName(){
        return name;
    }
    /** getProblemDescription grabs the problem description of the patient
	 *  @returns the string of the problem of the patient
	 */
    public String getProblemDescription(){
        return prob;
    }
   /**  overrriden tostring method that returns the string of the patient with commas
	 *  @returns the string of the name, problem, and priority
	 */
    public String toString()
    {
        return name + ", " + prob + ", " + prio;
    }
   /** overriden equals method that compares if the name, priority,
    *	 and problem are the same
	 *  @returns true if there are patients are the same, false if not
	 *	@param is the other patient to check against
	 */
    public boolean equals(Patient other)
    {
        if(other.getPriorityRating().equals(prio) && other.getName().equals(name)){
        if( other.getProblemDescription().equals(prob)){
            return true;
        }
        }
        return false;
    }
}
