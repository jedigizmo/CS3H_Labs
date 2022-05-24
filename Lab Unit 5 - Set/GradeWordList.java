/**
 *  Tests the Set Lab's WordList class. 
 *    Tests the sets of words based on input file. 
 *    Tests the union and intersection of the sets.
 *
 *  Run the student's main separately.
 */

import java.util.*;
import java.io.*;

public class GradeWordList
{
	//------------------------------
	// Data Members
	//------------------------------
	private static final String BANNER = "--------------------------------------------";
	private static final String LEFT_BANNER = "* ";
	private static final String RIGHT_BANNER = " *";
	
	/** redirect standard input */
	private static final InputStream STANDARD_INPUT = System.in;
	private static final PrintStream STANDARD_OUTPUT = System.out;
	
	/** data for testing WordList */
	private static final String[] TEST_FILES = {"grader1.dat", "grader2.dat", "grader3.dat"};
	private static Set<String>[] expectedWordSets = null;
	private static String[] expectedAllWords = new String[TEST_FILES.length - 1];
	private static String[] expectedCommonWords = new String[TEST_FILES.length - 1];
	
	private static WordList[] actualWordLists = new WordList[TEST_FILES.length];
	private static String[] actualWords = new String[TEST_FILES.length];
	
	
	public static void main(String[] args)
	{
		// Test WordList methods
		testSetup();
		testGetWords();
		testAllWords();
		testCommonWords();
		testSetFile();
	}
	
	/** 
	 *  Tests constructor, getWords
	 */
	public static void testGetWords()
	{
		showBanner("Testing constructor, getWords");
		try
		{
			String savedSet = "";
			for (int k = 0; k < TEST_FILES.length; k++)
			{
				actualWordLists[k] = new WordList(TEST_FILES[k]);
				actualWords[k] = actualWordLists[k].getWords().toString().toLowerCase();
				assertEquals("getWords - " + TEST_FILES[k], expectedWordSets[k].toString(),
					actualWords[k]);
					
				if (k == 0)
					savedSet = actualWords[k].toString().toLowerCase();
			}
			
			assertEquals("getWords - retest " + TEST_FILES[0], savedSet, 
				actualWordLists[0].getWords().toString().toLowerCase());
		}
		catch (RuntimeException exc)
		{
			System.out.println("testGetWords: " + exc.getMessage());
			exc.printStackTrace();
		}
		System.out.println();
	}
		
	/** 
	 *  Tests getAllWords (union)
	 */
	public static void testAllWords()
	{
		showBanner("Testing getAllWords");
		try
		{
			String savedSet = actualWordLists[0].getWords().toString().toLowerCase();
			for (int k = 1; k < TEST_FILES.length; k++)
			{
				Set<String> expectedClone = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
				expectedClone.addAll(expectedWordSets[k]);
				Set<String> actualAllWords = actualWordLists[0].getAllWords(expectedClone);
				assertEquals("getAllWords - " + TEST_FILES[0] + " & " + TEST_FILES[k], 
					expectedAllWords[k - 1], actualAllWords.toString().toLowerCase());				
					
				assertEquals("getAllWords - param not changed", 
					expectedWordSets[k].toString().toLowerCase(), 
					expectedClone.toString().toLowerCase());
			}
			
			assertEquals("getAllWords - not mutator", savedSet, 
				actualWordLists[0].getWords().toString().toLowerCase());
		}
		catch (Exception exc)
		{
			System.out.println("testAllWords: " + exc.getMessage());
			exc.printStackTrace();
		}
		System.out.println();
	}
	
	/** 
	 *  Tests getCommonWords (intersection)
	 */
	public static void testCommonWords()
	{
		showBanner("Testing getCommonWords");
		try
		{
			String savedSet = actualWordLists[0].getWords().toString().toLowerCase();
			for (int k = 1; k < TEST_FILES.length; k++)
			{
				Set<String> expectedClone = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
				expectedClone.addAll(expectedWordSets[k]);
				Set<String> actualCommonWords = actualWordLists[0].getCommonWords(expectedClone);
				assertEquals("getCommonWords - " + TEST_FILES[0] + " & " + TEST_FILES[k], 
					expectedCommonWords[k - 1], actualCommonWords.toString().toLowerCase());				
					
				assertEquals("getCommonWords - param not changed", 
					expectedWordSets[k].toString().toLowerCase(), 
					expectedClone.toString().toLowerCase());
			}
			
			assertEquals("getCommonWords - not mutator", savedSet, 
				actualWordLists[0].getWords().toString().toLowerCase());
		}
		catch (Exception exc)
		{
			System.out.println("testCommonWords: " + exc.getMessage());
			exc.printStackTrace();
		}
		System.out.println();
	}
	
	/** 
	 *  Tests setFile
	 */
	public static void testSetFile()
	{
		showBanner("Testing setFile");
		try
		{
			WordList testWordList = new WordList(TEST_FILES[0]);
			testWordList.setFile(TEST_FILES[1]);
			String actualSet = testWordList.getWords().toString().toLowerCase();
			assertEquals("setFile - " + TEST_FILES[1], expectedWordSets[1].toString(), actualSet);				
					
			testWordList.setFile(TEST_FILES[2]);
			actualSet = testWordList.getWords().toString().toLowerCase();
			assertEquals("setFile - " + TEST_FILES[2], expectedWordSets[2].toString(), actualSet);				
		}
		catch (RuntimeException exc)
		{
			System.out.println("testSetFile: " + exc.getMessage());
			exc.printStackTrace();
		}
		System.out.println();
	}
	
	/** 
	 *  Set-up the expected output for the tests (all lowercase)
	 */
	@SuppressWarnings("unchecked")
	private static void testSetup()
	{
		try
		{
			expectedWordSets = (Set<String>[]) new Set[TEST_FILES.length];

			for (int k = 0; k < TEST_FILES.length; k++)
			{
				expectedWordSets[k] = getExpectedWords(TEST_FILES[k]);
				if (k != 0)
				{
					Set<String> allSet = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
					allSet.addAll(expectedWordSets[0]);
					allSet.addAll(expectedWordSets[k]);
					expectedAllWords[k - 1] = allSet.toString().toLowerCase();
			
					Set<String> commonSet = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
					commonSet.addAll(expectedWordSets[0]);
					commonSet.retainAll(expectedWordSets[k]);
					expectedCommonWords[k - 1] = commonSet.toString().toLowerCase();
				}
			}
		}
		catch (RuntimeException exc)
		{
			System.out.println("testSetup: " + exc.getMessage());
			exc.printStackTrace();
		}
	}
	
	/**
	 *  Builds a set of distinct words from a file
	 *  @param inputFile the file to read
	 *  @return set of distinct words from file
	 */
	private static Set<String> getExpectedWords(String inputFile)
	{
		Set<String> wordList = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
		try
		{
			Scanner scan = new Scanner(new File(inputFile)).useDelimiter("[\\s.,!?;:()]+");
																	// OR  [\\p{Punct}\\s]+
																	// NOT \\W+
			while (scan.hasNext())
			{
				String str = scan.next().toLowerCase();
				wordList.add(str);
			}
			scan.close();
		}
		catch (IOException err)
		{
			System.out.println("Exception in getExpectedWords: " + err.getMessage());
		}
		return wordList;
	}
	
	/**
	 *  Display whether or not expected and actual strings match
	 *  @param msg the initial message to display
	 *  @param expected the expected string value
	 *  @param actual the actual string value to check
	 */
	private static void assertEquals(String msg, String expected, String actual)
	{
		// Reset "standard" I/O
		resetStandard();
		
		System.out.print(msg + " is ");
		if (expected.equals(actual))
			System.out.println("correct");
		else
			System.out.println("INCORRECT: \nexpected=" + expected
				+ "\n  actual=" + actual);
	}

	/**
	 *  Display whether or not expected and actual integers match
	 *  @param msg the initial message to display
	 *  @param expected the expected int value
	 *  @param actual the actual int value to check
	 */
	private static void assertEquals(String msg, int expected, int actual)
	{
		assertEquals(msg, "" + expected, "" + actual);
	}

	/**
	 *  Display whether or not expected and actual floating point values match
	 *  @param msg the initial message to display
	 *  @param expected the expected double value
	 *  @param actual the actual double value to check
	 */
	private static void assertEquals(String msg, double expected, double actual)
	{
		String expStr = String.format("%.5f", expected);
		String actStr = String.format("%.5f", actual);
		assertEquals(msg, expStr, actStr);
	}

	/**
	 *  Display banner between tests
	 *  @param title description of test
	 */
	private static void showBanner(String title)
	{
		// Reset "standard" I/O
		resetStandard();

		System.out.println(BANNER);
		int width = BANNER.length() - (LEFT_BANNER.length() + RIGHT_BANNER.length());
		System.out.printf("%s%-" + width + "s%s%n", LEFT_BANNER, title, RIGHT_BANNER);
		System.out.println(BANNER);
	}

	/**
	 *  Reset back to standard input/output
	 */
	private static void resetStandard()
	{
		System.setIn(STANDARD_INPUT);
		System.setOut(STANDARD_OUTPUT);
	}

	/** Returns a copy of str without any whitespace
	 *  @param str a string from which to strip whitespace
	 *  @return copy of str stripped of all whitespace
	 */
	private static String stripSpaces(String str)
	{
		StringBuilder result = new StringBuilder();
		for (int k = 0; k < str.length(); k++)
		{
			char ch = str.charAt(k);
			if (!Character.isWhitespace(ch))
				result.append(ch);
		}
		return result.toString();
	}
}