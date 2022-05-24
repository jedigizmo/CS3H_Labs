/**
 * Twitter.java
 * Twitter holds the twitter constructor and various methods
 *
 * @author Gikonyo Njendu
 * Collaborators: Takuto Ban, Nikhil Venkatachalam
 * Teacher Name: Ms.Bailey
 * Period: 6
 * Due Date: 04/13/2022
 */
 
import java.io.*;
import java.util.*;

public class Twitter
{
	private boolean adj_matrix[][];
	private Person people[];
	private int numU;
	private HashMap<Person,Integer> indexes;
	
	/** Constructor for the Twitter class
	 *  @param the list of users to be added
	 */
	public Twitter(List<Person> myPeeps)
	{
		numU = myPeeps.size();
		people = myPeeps.toArray(new Person[0]);
		adj_matrix = new boolean[numU][numU];
		indexes = new HashMap<>();
		for(int i = 0; i < people.length; i++){
			indexes.put(people[i], i);
		}
	}
	
	/** addFollower adds the follower p2 to p1
	 *  @param p1 the user of the person being followed
	 *	@param p2 the user of the person followed
	 */
	public void addFollower(Person p1, Person p2)
	{
		adj_matrix[getInd(p1)][getInd(p2)] = true;
	}
	
	/** getInd is a private helper method to get the array index of the user
	 *  @param the Person object to get the array index of 
	 *  @returns the int of the index of the person in the array
	 */
	private int getInd(Person p){
		return indexes.get(p);
	}
	
	/** removeFollower removes the follower p2 to p1
	 *  @param p1 the user of the person being followed
	 *	@param p2 the user of the person followed
	 */
	public void removeFollower(Person p1, Person p2)
	{
		adj_matrix[getInd(p1)][getInd(p2)] = false;
	}
	
	/** getFollowers goes through the matrix and gets the followers of a user
	 *  @param the person object to get the list of followers from
	 *  @returns a list of all the followers of the given user
	 */
	public List<Person> getFollowers(Person p)
	{
		List<Person> out = new ArrayList<>();
		int ind = getInd(p);
		for(int q = 0; q < adj_matrix.length; q++){
			if(adj_matrix[q][ind]){
				out.add(people[q]);
			}
		}
		return out;
	}
	
	/** getMostPopular finds the most followed people in the matrix and puts them in a list
	 *  @returns returns a list of the most followed users
	 */
	public List<Person> getMostPopular()
	{
		List<Person> out = new ArrayList<>();
		int max = -1;
		int col = 0;
		for(; col < adj_matrix.length; col++){
			int tmp = 0;
			for(int row = 0; row < adj_matrix.length; row++){
			if(adj_matrix[row][col]){
				tmp++;
			}
		}
		if(tmp == max){
			out.add(people[col]);
		}else if(tmp > max){
			out.clear();
			out.add(people[col]);
			max = tmp;
		}
		}
		return out;
	}
}
