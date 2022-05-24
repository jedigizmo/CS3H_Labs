/**
 * FilingCabinet.java
 * Class representing the FilingCabinet class that holds drawers and the filingcabinet methods
 *
 * @author Gikonyo Njendu
 * Collaborators: Nathan Ma, Neil Gupta, Ayon Das
 * Teacher Name: Ms.Bailey
 * Period: 6
 * Due Date: 12/08/2021
 */
public class FilingCabinet 
{
	/** Number of filing cabinets (one for each letter A-Z) */
	private static final int NUM_CABINETS = 26;
	
	/** Array of DoubleNode objects for filing cabinet of Students */
	private DoubleNode<Student>[] cabinet;	

	@SuppressWarnings("unchecked")
	/** Constructor method for the FilingCabinet class
	 */
	public FilingCabinet() 
	{
		cabinet = new DoubleNode[NUM_CABINETS];
	}	
	/** Adds the student object to the correct list or empty drawer.
	 *  @param stu is a student object to be added to cabinet.
	 */
	public void add(Student stu) 
	{	
		DoubleNode<Student> newNode = new DoubleNode<Student>(stu, null, null);
		DoubleNode<Student> testing = cabinet[getDrawer(stu)];
		if (testing == null)
		{
			cabinet[getDrawer(stu)] = newNode;
		}
		else if(testing.getValue().compareTo(stu) > 0)
		{
		newNode.setNext(testing);
		testing.setPrevious(newNode);
		cabinet[getDrawer(stu)] = newNode;
		}
		else
		{
			while (testing.getValue().compareTo(stu) < 0 && testing.getNext() != null)
			{
				testing = testing.getNext();
			}
			if (testing.getNext() == null)
			{
			testing.setNext(newNode);
			newNode.setPrevious(testing);
		}
		else{
			newNode.setNext(testing.getNext());
			testing.getNext().setPrevious(newNode);
			testing.setNext(newNode);
			newNode.setPrevious(testing);
		}
		}
	}
	/** Checks if the cabinet contains a given student object.
	 *  @param the student object to be checked if contains.
	 *  @returns returns true if the student is in the cabinet false if not.
	 */
	public boolean contains(Student stu) 
	{
		for (int i = 0; i < NUM_CABINETS; i++)
		{
		if (cabinet[i] != null)
		{
			DoubleNode<Student> testing = cabinet[i];
			while (testing != null)
			{
				if (testing.getValue().equals(stu))
				{
					return true;
				}
				testing = testing.getNext();
			}
		}
		}
			return false;
	}
	/** The remove method removes the given student object from the cabinet.
	 *  @param the student object to be removed.
	 */
	public void remove(Student stu) 
	{
		boolean flag = true;
		for (int i = 0; i < NUM_CABINETS; i++)
		{
		if (cabinet[i] != null)
		{
			DoubleNode<Student> testing = cabinet[i];
			while (testing != null && flag)
			{
				if (testing.getValue().equals(stu))
				{
					if(testing.getNext() != null && testing.getPrevious() != null)
					{
					testing.getPrevious().setNext(testing.getNext());
					testing.getNext().setPrevious(testing.getPrevious());
					flag = false;
					}
					else if(testing.getNext() == null && testing.getPrevious() != null)
					{
						testing.getPrevious().setNext(null);
						flag = false;
					}
					else if(testing.getNext() != null && testing.getPrevious() == null)
					{
						cabinet[i] = testing.getNext();
						testing.getNext().setPrevious(null);
						flag = false;
					}
					else if(testing.getNext() == null && testing.getPrevious() == null)
					{
						cabinet[i] = null;
						flag = false;
					}
				}
				testing = testing.getNext();
			}
		}
		}
	}
	
	/** Retrieves the filing cabinet a drawer at a time
	 *  @return filing cabinet as a string with each student on a separate line
	 */
	@Override
	public String toString() 
	{
		StringBuilder buf = new StringBuilder("Filing Cabinet:\n");
		DoubleNode<Student> drawer;
		for (int k = 0; k < cabinet.length; k++) 
		{
			drawer = cabinet[k];
			if (drawer != null)
				buf.append("Drawer " + (char) ('A' + k) + ":  ");
			while (drawer != null) 
			{
				buf.append(drawer.getValue().toString() + "\n");
				drawer = drawer.getNext();
				if (drawer != null) buf.append("           ");
			}
		}
		buf.append("**end**");
		return buf.toString();
	}
	
	/** Retrieves the filing cabinet in reverse order
	 *  @return filing cabinet as a string with each student in reverse alphabetical order
	 */
	public String reverseToString() 
	{
		StringBuilder buf = new StringBuilder("Reverse Filing Cabinet:\n");
		DoubleNode<Student> drawer;
		for (int k = cabinet.length-1; k >= 0; k--) 
		{
			drawer = cabinet[k];
			if (drawer != null)
			{
				buf.append("Drawer " + (char) ('A' + k) + ":  ");
			
				// go to end
				while (drawer.getNext() != null)
					drawer = drawer.getNext();
					
				while (drawer != null) 
				{
					buf.append(drawer.getValue().toString() + "\n");
					drawer = drawer.getPrevious();
					if (drawer != null) buf.append("           ");
				}
			}
		}
		buf.append("**end**");
		return buf.toString();
	}
	
	//--------------------------------------------------
	//	Private Methods
	//--------------------------------------------------
	
	/** Determines which drawer should contain stu
	 *  @param stu the student object to check
	 *  @return index of the drawer to which this student belongs;
	 *          (uses the 'Z' drawer if last name does not begin with a letter)
	 */
	private int getDrawer(Student stu) 
	{
		String lastName = stu.getLastName().toUpperCase();
		char letter = lastName.charAt(0);
		if (letter < 'A' || letter > 'Z') 
			letter = 'Z';
		
		return letter - 'A';
	}
}