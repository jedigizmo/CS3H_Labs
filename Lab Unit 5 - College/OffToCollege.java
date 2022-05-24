/*
 * OffToCollege.java
 *
 * OffToCollege Class
 * Tests the CollegeMapping class by adding students and their university
 * choice, updating selections, removing student, and accessing information
 * about students and their choices.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class OffToCollege 
{
	//------------------------------
	// Data Members
	//------------------------------
	private static final Scanner scan = new Scanner(System.in);
	private static final String MENU = new String(
		"\nMenu choices:\n" +
		"1 - Add/Update Student\n" +
		"2 - Remove Student\n" +
		"3 - Display Student's Choices\n" +
		"4 - Display All Students & Choices\n" +
		"5 - Display All Students Choosing Same University\n" +
		"6 - Exit");
	
	private CollegeMapping seniors;
	private ArrayList<Student> students;
	private ArrayList<String> universities;
	
	/**
	 *  Instantiates the CollegeMapping and populates array lists
	 *  of student and university choices
	 */
	public OffToCollege() 
	{
		// Create the college mapping and array list of students
		seniors = new CollegeMapping();
		
		// Fill the student list with students to pick from
		students = new ArrayList<Student>();
		students.add(new Student("Thomas", "Marcus", 1234, 3.35));
		students.add(new Student("Thomas", "Miles", 1357, 4.23));
		students.add(new Student("Thomas", "Mason", 1246, 3.75));
		students.add(new Student("Jones", "Amanda", 4446, 3.97)); 
		students.add(new Student("Jones", "Amanda", 3332, 2.5)); 
		
		// Fill the university list with universities to pick from
		universities = new ArrayList<String>();
		universities.add(CollegeMapping.UNDECIDED);
		universities.add("University of Texas");
		universities.add("Texas A&M");
		universities.add("Harvard College");
		universities.add("Cal Tech");
		universities.add("MIT");
		universities.add("University of Alabama");
	}
	
	//------------------------------
	// Public Methods
	//------------------------------
	
	/**
	 *  Process the user's choice of menu options until exit is chosen
	 */
	public void menuProcess() 
	{
		boolean done = false;
		int choice;
		while (!done) 
		{
			System.out.print(MENU + "\nChoice: ");
			choice = scan.nextInt();
			
			// Add/update a student & university choice
			if (choice == 1) 
			{
				Student stu = chooseStudent();
				String univ = chooseUniversity();
				seniors.updateStudent(stu, univ);
				System.out.println("\n" + stu + " updated");
			}
			// Remove a student 
			else if (choice == 2) 
			{
				Student stu = chooseStudent();
				seniors.removeStudent(stu);
				System.out.println("\n" + stu + " removed");
			}
			// Display a student's choices
			else if (choice == 3) 
			{
				Student stu = chooseStudent();
				System.out.println("\nChoices for " + stu + ":\n" +
										 seniors.getUniversityChoices(stu) + "\n");
			}
			// Display all students and their choices
			else if (choice == 4) 
			{
				System.out.println("\nAll students and choices:\n" +
										 seniors.getAllStudents() + "\n");
			}
			// Display all students attending same university
			else if (choice == 5) 
			{
				String univ = chooseUniversity();
				System.out.println("\nAll students attending " + univ + ":\n" +
										 seniors.getStudentsAt(univ) + "\n");
			}
			// Exit
			else if (choice == 6) 
			{
				done = true;
			}
			else 
			{
				System.out.println("Invalid choice. Try again.\n");
			}
		} // end while
		
	}

	//------------------------------
	// Private Methods
	//------------------------------
	
	/**
	 *  Choose a student from the list or enter new data to add to the list
	 *  @return the student selected
	 */
	private Student chooseStudent() 
	{
		while (true) 
		{
			// Display choices
			System.out.println("\nChoose from:");
			int k;
			for (k = 0; k < students.size(); k++) 
			{
				System.out.println((k + 1) + " - " + students.get(k));
			}
			System.out.println((k + 1) + " - Enter New Student Data");
			System.out.print("Student choice: ");
			int choice = scan.nextInt();
			
			// Chose from array
			if (1 <= choice && choice <= students.size()) 
			{
				return students.get(choice - 1);
			}
			// Enter new student info
			else if (choice == students.size() + 1) 
			{	
				scan.nextLine();
				System.out.print("\nEnter student's first name: ");
				String fName = scan.nextLine();
				System.out.print("Enter student's last name: ");
				String lName = scan.nextLine();
				System.out.print("Enter student's ID number: ");
				int id = scan.nextInt();
				System.out.print("Enter student's GPA: ");
				double gpa = scan.nextDouble();
				Student stu = new Student(lName, fName, id, gpa);
				students.add(stu);
				return stu;
			}
			// Invalid entry
			else 
			{
				System.out.println("Invalid entry; try again.");
			}
		}
	}

	/**
	 *  Choose a university from the list or enter new data to add to the list
	 *  @return the university selected
	 */
	private String chooseUniversity() 
	{
		while (true) 
		{
			// Display choices
			System.out.println("\nChoose from:");
			int k;
			for (k = 0; k < universities.size(); k++) 
			{
				System.out.println((k + 1) + " - " + universities.get(k));
			}
			System.out.println((k + 1) + " - Enter New University");
			System.out.print("University choice: ");
			int choice = scan.nextInt();
			
			// Chose from array
			if (1 <= choice && choice <= universities.size()) 
			{
				return universities.get(choice - 1);
			}
			// Enter new student info
			else if (choice == universities.size() + 1) 
			{	
				scan.nextLine();
				System.out.print("\nEnter university: ");
				String univ = scan.nextLine();
				universities.add(univ);
				return univ;
			}
			// Invalid entry
			else 
			{
				System.out.println("Invalid entry; try again.");
			}
		}
	}
	
	//------------------------------
	// Main Method
	//------------------------------
	
	/**
	 *  Run this test 
	 */
	public static void main(String[] args) 
	{
		OffToCollege college = new OffToCollege();
		college.menuProcess();
	}
}
