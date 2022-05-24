/**
 * Person.java
 * Person class holds the Person constructor and various methods
 *
 * @author Gikonyo Njendu
 * Collaborators: Takuto Ban, Nikhil Venkatachalam
 * Teacher Name: Ms.Bailey
 * Period: 6
 * Due Date: 04/13/2022
 */
 
import java.io.*;
import java.util.*;

public class Person
{
	private String name;
	private String userName;
	
	/** Person constructor instantiates the variables
	 *  @param the name and username of the user 
	 */
	public Person(String name, String userName)
	{
		this.name = name;
		this.userName = userName;
	}
	
	/** getName method is get the name of the twitter user
	 *  @returns returns the name of the user 
	 */
	public String getName()
	{
		return name;
	}
	
	/** getUserName method gets and returns the username of the twitter user
	 *  @returns returns the name of the user
	 */
	public String getUserName()
	{
		return userName;
	}
	
	/** toString method converts the name and username into readable format
	 *  @returns returns the string of the name and username seperated by brackets
	 */
	@Override
	public String toString()
	{
		return name + " (" + userName + ")";
	}
	
	/** equals method checks if the two person objects are equal
	 *  @param the Person object to be compared against
	 *  @returns returns true if the name and the username are the same
	 */
	@Override
	public boolean equals(Object obj)
	{
		Person check = (Person) obj;
		return name.equals(check.getName()) && userName.equals(check.getUserName());
	}
	
	/** hashCode hashes the person object
	 *  @returns returns the hashcode of the name and username hashed combined
	 */
	@Override
	public int hashCode()
	{
		return name.hashCode() + userName.hashCode();
	}
}
