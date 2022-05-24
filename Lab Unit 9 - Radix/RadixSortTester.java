/**
*  RadixSortTester.java
*  Tests the RadixSort class
*/

import java.util.Arrays;
import java.util.*;

public class RadixSortTester
{
	public static void main(String[] args) 
	{
		int[] numbers = new int[25];
		for (int k = 0; k < numbers.length; k++)
			numbers[k] = (int) (Math.random() * 100000) + 1;
		System.out.println("Before sorting: " + Arrays.toString(numbers) + "\n");
	
		RadixSort.sort(numbers);
		System.out.println("After sorting: " + Arrays.toString(numbers) + "\n");

		numbers = new int[50];
		for (int k = 0; k < numbers.length; k++)
			numbers[k] = (int) (Math.random() * 20) + 1;
		System.out.println("Before sorting: " + Arrays.toString(numbers) + "\n");
	
		RadixSort.sort(numbers);
		System.out.println("After sorting: " + Arrays.toString(numbers) + "\n");

	}
}