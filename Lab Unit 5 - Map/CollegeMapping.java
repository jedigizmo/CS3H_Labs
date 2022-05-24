/** 
 * The CollegeMapping Class creates the CollegeMapping objects 
 *
 * @author Gikonyo Njendu
 * Collaborators: Nathan Ma, Neil Gupta
 * Teacher Name: Ms.Bailey
 * Period: 06
 * Due Date: 10/26/2021
 */
 import java.io.*;
 import java.util.*;


public class CollegeMapping {
	 static final String UNDECIDED = "UNDECIDED";
	 private Map<Student, TreeSet<String>> lists;
	 /** CollegeMapping constructor creates the CollegeMapping object 
	 */
    public CollegeMapping() {
    	lists = new TreeMap<>();
    }
     /** updateStudent method updates the student choice of university  
	 *  @param name of the student object
	 *  @param choice college that the student chooses
	 */
    public void updateStudent (Student name, String choice)
    {
    	if (lists.containsKey(name))
    	{
    		lists.get(name).add(choice);
    	}
    	else
    	{
    		lists.put(name, new TreeSet<>());
    		if (!((choice == "" || choice == null) || choice == UNDECIDED))
    			lists.get(name).add(choice);
    	}
    }
     /** removeStudent method removes the given student object
	 *  @param name of the student object
	 */
    public void removeStudent(Student name)
    {
    	lists.remove(name);
    }
     /** getAllStudents method gets all the information of the students and outputs them.
      *@return returns the string of student information and universities chosen. 
	 */
    public String getAllStudents()
    {
    	Set<Student> keys = lists.keySet();
		Iterator<Student> it = keys.iterator();
		String out = "";
		if (lists.isEmpty())
		{
			out += "No college mappings";
			return out;
		}
		while (it.hasNext())
		{
			Student outer = it.next();
    		out = out + outer.toString() + " => " + lists.get(outer) + "\n";
      }
      return out;
    }
     /** getUniversityChoices method gets the university choices of the given student
	 *  @param name of the student object
	 *  @return gets the string of the students university choices
	 */
    public String getUniversityChoices(Student name)
    {
     String out;
     if (lists.containsKey(name))
     {
     out =	lists.get(name).toString();
     return out;
     }
     else
     {
     	out = "Student not in list";
     	return out;
     }
    }
     /** getStudentsAt grabs the list of students that attend a certain university 
	 *  @param name of the university to check
	 *  @return returns an arraylist of the students that go to the given university
	 */
    public ArrayList<Student> getStudentsAt (String Uni) 
    {
    	Set<Student> keys = lists.keySet();
		Iterator<Student> it = keys.iterator();
		ArrayList<Student> out = new ArrayList<>();
		while (it.hasNext())
		{
		Student test = it.next();
		if (lists.get(test).contains(Uni))
		{
			out.add(test);
		}
		if ((lists.get(test).isEmpty()) && (Uni == UNDECIDED))
		{
			out.add(test);
		}
    }
    	return out;
    }
}