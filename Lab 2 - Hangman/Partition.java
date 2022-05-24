/**
 * Heading goes here
 *
 */

import java.util.List;
import java.util.ArrayList;

public class Partition
{
	private String wordPattern;
	private List<String> wordList;

	/**
	 * Construct a Partition object with no words.
	 * @param pattern the pattern for the partition.
	 */
	public Partition(String pattern)
	{
		wordPattern = pattern;
		wordList = new ArrayList<String>();
	}

	/**
	 * Construct a Partition object with one given word.
	 * @param pattern the pattern for the partition.
	 * @param word the initial word for the partition.
	 */
	public Partition(String pattern, String word)
	{
		wordPattern = pattern;
		wordList = new ArrayList<String>(); 
		wordList.add(word);
	}

	/**
	 * Adds word to the partition IFF the pattern matches the given one.
	 * @param pattern the pattern to check for a match.
	 * @param word the word to add if the patterns match.
	 * @returns true if word is added; false otherwise.
	 */
	public boolean addIfMatches(String pattern, String word)
	{
		if (pattern.indexOf(wordPattern) == -1)
		{
			return false;	// REPLACE
		}
		else
		{
			wordList.add(word);
			return true;
		}
	}

	/**
	 * Retrieves the words for this pattern
	 * @return the pattern's words.
	 */
	public List<String> getWords()
	{
		return wordList;	// REPLACE
	}

	/**
	 * Retrieves the number of dashes in the partition's pattern.
	 * @return the number of dashes in the partition's pattern.
	 */
	public int getPatternDashCount()
	{
		int DashCnt = 0;
		for (int x = 0; x < wordPattern.length(); x++)
		{
			if (wordPattern.substring(x,x + 1).equals("-"))
			{
				DashCnt++;
			}
		}
		return DashCnt;	// REPLACE
	}

	
	/**
	 *  Test Partition class - expected output at bottom
	 */
	public static void main(String[] args)
	{
		// tests 1&5 - word list should be []
		System.out.println("Testing 1-param constructor");
		Partition partition = new Partition("PaTtErN");
		System.out.println("wordPattern: " + partition.wordPattern);
		System.out.println("wordList: " + partition.wordList);
		System.out.println("getWords: " + partition.getWords());
		System.out.println();
		
		// tests 2&5 - word list should be [TheOneWord]
		System.out.println("Testing 2-param constructor");
		partition = new Partition(new String("pAtTeRn"), "TheOneWord");
		System.out.println("wordPattern: " + partition.wordPattern);
		System.out.println("wordList: " + partition.wordList);
		System.out.println("getWords: " + partition.getWords());
		System.out.println();
		
		// test 3 - word list should contain [TheOneWord, TheOtherWord, TheOtherOtherWord]
		System.out.println("Testing addIfMatches when is a match");
		boolean result = partition.addIfMatches(new String("pAtTeRn"), "TheOtherWord");
		System.out.println("addifMatches should be true; result = " + result);
		result = partition.addIfMatches(new String("pAtTeRn"), "TheOtherOtherWord");
		System.out.println("addifMatches true; result = " + result);
		System.out.println("wordList: " + partition.wordList);
		System.out.println("getWords: " + partition.getWords());
		System.out.println();
		
		// test 4 - word list should contain [TheOneWord]
		System.out.println("Testing addIfMatches when NOT a match");
		partition = new Partition(new String("pAtTeRn"), "TheOneWord");
		result = partition.addIfMatches(new String("PaTtErN"), "TheOtherWord");
		System.out.println("addifMatches false; result = " + result);
		result = partition.addIfMatches(new String("PaTtErN"), "TheOtherOtherWord");
		System.out.println("addifMatches false; result = " + result);
		System.out.println("wordList: " + partition.wordList);
		System.out.println("getWords: " + partition.getWords());
		System.out.println();
		
		// test 6 - should be 12
		partition = new Partition("--p-A---t-T-e-R-n--", "TheOneWord");
		int count = partition.getPatternDashCount();
		System.out.println("getPatternDashCount should be 12; count = " + count);
		System.out.println();

		/****************** Expected Output ***********************		
			Testing 1-param constructor
			wordPattern: PaTtErN
			wordList: []
			getWords: []
			
			Testing 2-param constructor
			wordPattern: pAtTeRn
			wordList: [TheOneWord]
			getWords: [TheOneWord]
			
			Testing addIfMatches when is a match
			addifMatches should be true; result = true
			addifMatches true; result = true
			wordList: [TheOneWord, TheOtherWord, TheOtherOtherWord]
			getWords: [TheOneWord, TheOtherWord, TheOtherOtherWord]
			
			Testing addIfMatches when NOT a match
			addifMatches false; result = false
			addifMatches false; result = false
			wordList: [TheOneWord]
			getWords: [TheOneWord]
			
			getPatternDashCount should be 12; count = 12
		*/
	}
}