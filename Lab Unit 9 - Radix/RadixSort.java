/**
 * RadixSort.java
 * RadixSort class represents the sorting class that sorts numbers
 *
 * @author Gikonyo Njendu
 * Collaborators: Takuto Ban, Ayon Das
 * Teacher Name: Ms.Bailey
 * Period: 6
 * Due Date: 01/25/2022
 */

import java.util.Queue;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class RadixSort 
{
	/** Sorts the given array using the Radix Sort
	 *  @param nums the array of integers to be sorted
	 *      Precondition: nums.length > 0; all values in nums are nonnegative
	 */
	private static final int QLEN = 10;
	public static void sort(int[] nums) 
	{
		int numDigits = getMaxDigits(nums);
		
		for (int k = 0; k < numDigits; k++) 
		{
			List<Queue<Integer>> qs = itemsToQueues(nums, k);
			queuesToArray(qs, nums);
		}
	}
	/** getMaxDigits works through the array looking for the longest number
	 *  @param the array of numbers to search
	 *  @return the length of the largest value
	 */
	private static int getMaxDigits(int[] numbers)
	{
		int out = 0;
		for(int x : numbers){
			if((int) Math.log10(x) + 1 > out)
				out = (int) Math.log10(x) + 1;
		}
		return out;
	}
	/** takes a number and returns the digit at the given index 
	 *  @param the number with the digit being returned
	 *  @param int k is the index of the digit wanted 
	 *	@return returns the digit at the given index
	 */
	public static int getDigit(int number, int k) 
	{
		for(int x = 0; x < k; x++){
			number = (int)(number/10);
		}
		number %= 10;
		return number;
	}
	/** given an array places the numbers into queues sorted by the value of the digit
	 *  @param array of the numbers to be placed into queues
	 *  @param int k is the given index of the digit to sort by
	 *  @return returns a list of the queues 
	 */
	private static List<Queue<Integer>> itemsToQueues(int[] nums, int k) 
	{
		List<Queue<Integer>> out = new ArrayList<>();
		
		for(int b = 0; b < QLEN; b++){
			out.add(new LinkedList<Integer>());
		}
		for(int x : nums){
			out.get(getDigit(x, k)).offer(x);
		}
		return out;
	}
	/** given a queue fills the array with the numbers starting from the front
	 *  @param the queues for the method to grab from
	 *  @param the array of int to be filled
	 */
	private static void queuesToArray(List<Queue<Integer>> queues, int[] nums) 
	{
		int ind = 0;
		for(int x = 0; x < queues.size(); x++){
			while(!queues.get(x).isEmpty()){
				nums[ind++] = queues.get(x).poll();
			}
		}
	}

}