/**
 * EvilHangmanTest.java  06/04/2015
 *
 * @author - Robert Glen Martin
 * @author - School for the Talented and Gifted
 * @author - Dallas ISD
 *
 * Modified 08/24/16 to use without JAMTester
 * Modified 08/22/17 to use reflection for Partition
 * Modified 09/09/18 to refine tests 3,4,16,17
 * Modified 09/12/19 to simplify EH instance variable status check
 * @author - Tracy Ishman 
 * @author - Plano West Senior High School, PISD
 *
 * Modified 08/24/18 to use regex to test input/output text
 * @author - Timothy Qin, PWSH Student
 *
 * Tests for Partition.java
 * 1) One parameter constructor works.
 * 2) Two parameter constructor works.
 * 3) addIfMatches works when pattern matches
 * 4) addIfMatches works when pattern doesn't match
 * 5) getWords works
 * 6) getPatternDashCount works
 *
 * Tests for EvilHangman.java
 * 7) Constructor works correctly (word length 7).
 * 8) Constructor works correctly (word length 3).
 * 9) Constructor works correctly (illegal initial word lengths).
 * 10) Constructor works correctly (word length 4).
 * 11) toString works correctly (debug is false).
 * 12) toString works correctly (debug is true).
 * 13) inputLetter works correctly (a -> A).
 * 14) inputLetter works correctly (Z -> Z).
 * 15) inputLetter works correctly (MM A- Z+ x -> X).
 * 16) getPattern works correctly ("QQQQQ", "Q" -> "QQQQQ").
 * 17) getPattern works correctly ("QQQQQ", "R" -> "-----").
 * 18) getPartitionList works correctly.
 * 19) removeSmallerPartitions works correctly (1 largest)
 * 20) removeSmallerPartitions works correctly (3 largest)
 * 21) getWordsFromOptimalPartition works correctly
 * 22) substitute works correctly ("----", "ABCD", "E" -> "----").
 * 23) substitute works correctly ("----", "EBED", "E" -> "E-E-").
 * 24) substitute works correctly ("E-E-", "AMCM", "M" -> "EMEM").
 * 25-28) playGame works correctly.
 */


import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.AccessibleObject;

public class EvilHangmanTest 
{
	/** Data for accessing private fields and methods */
	private static final String[] EH_INSTANCE_VAR = {"debug", "in", "wordList", 
		"wordLength", "remainingGuesses", "solution", "guessedLetters"};
	private static Field[] fields;
	private static Method[] methods;
	
	/** Objects to test the classes */
	private static Partition partition;
	private static EvilHangman evil;

	/** Name of input file */
	private static final String INPUT_FILE = "test.txt";

	/** Data for calling main method */
	private static final String[] ARGS = new String[0];

	// "System.out.print" output is captured in arrayStream
	private static PrintStream stdOut = System.out;
	private static ByteArrayOutputStream arrayStream =
		new ByteArrayOutputStream();
	private static PrintStream out =
		new PrintStream(arrayStream);

	/** Start the tests */
	public static void main(String[] args)
	{
		System.out.println(INPUT_FILE);

		// Test Partition class
		testPartition();

		// Test Hangman class pieces
		testHangman();
	}

	/**
	 *  Test the Partition class
	 */
	public static void testPartition() 
	{
		System.out.println("Testing Partition Class");
		test01(); // 1-param constructor
		test02(); // 2-param constructor
		test03(); // addIfMatches: matches
		test04(); // addIfMatches: no match
		test05(); // getWords
		test06(); // getPatternDashCount
		System.out.println();
	}
	
	/**
	 *  Test the EvilHangman class
	 */
	public static void testHangman() 
	{
		try
		{
			System.out.println("Testing Hangman Class");
			test07(); // constructor: word length 7
			test08(); // constructor: word length 3
			test09(); // constructor: illegal initial word lengths
			test10(); // constructor: word length 4
			test11(); // toString: debug is false
			test12(); // toString: debug is true
			test13(); // inputLetter: a -> A
			test14(); // inputLetter: Z -> Z
			test15(); // inputLetter: MM A- Z+ x -> X
			test16(); // getPattern: QQQQQ, Q -> QQQQQ
			test17(); // getPattern: QQQQQ, R -> -----
			test18(); // getPartitionList
			test19(); // removeSmallerPartitions: 1 largest
			test20(); // removeSmallerPartitions: 3 largest
			test21(); // getWordsFromOptimalPartition
			test22(); // substitute: "----", "ABCD", "E" -> "----"
			test23(); // substitute: "----", "EBED", "E" -> "E-E-"
			test24(); // substitute: "E-E-", "AMCM", "M" -> "EMEM"
			test25(); // playGame 1
			test26(); // playGame 2
			test27(); // playGame 3
			test28(); // playGame 4
			System.out.println();
		}
		catch (Exception exc)
		{
			System.out.println(exc.getMessage());
			exc.printStackTrace();
		}
	}

	/**
	 *  Test 1-parameter Partition constructor
	 */
	public static void test01() 
	{
		partition = new Partition("PaTtErN");

		List<String> expectedWords = Arrays.asList(new String[]{});
		checkPartitionPIVs(
			"01 - One Parameter Constructor test",
			"PaTtErN", 			// pattern
			expectedWords); 	// words
	}

	/**
	 *  Test 2-parameter Partition constructor
	 */
	public static void test02() 
	{
		partition = new Partition("pAtTeRn", "TheOneWord");
		List<String> expectedWords = Arrays.asList(new String[]{"TheOneWord"});
		checkPartitionPIVs(
			"02 - Two Parameter Constructor test",
			"pAtTeRn", 			// pattern
			expectedWords); 	// words
	}

	/**
	 *  Test addIfMatches when pattern matches
	 */
	public static void test03() 
	{
		partition = new Partition(new String("pAtTeRn"), new String("TheOneWord"));

		boolean result = partition.addIfMatches(new String("pAtTeRn"), new String("TheOtherWord"));
		System.out.println("03 - addIfMatches #1 works when pattern matches? "
			+ result);

		result = partition.addIfMatches(new String("pAtTeRn"), new String("TheOtherOtherWord"));
		System.out.println("03 - addIfMatches #2 works when pattern matches? "
			+ result);

		List<String> expectedWords = Arrays.asList(
			new String[]{new String("TheOneWord"), new String("TheOtherWord"), 
							 new String("TheOtherOtherWord")});
		checkPartitionPIVs(
			"03 - addIfMatches works when pattern matches",
			"pAtTeRn", 			// pattern
			expectedWords); 	// words
	}

	/**
	 *  Test addIfMatches when pattern does not match
	 */
	public static void test04() 
	{
		partition = new Partition(new String("pAtTeRn"), new String("TheOneWord"));

		boolean result = partition.addIfMatches(new String("PaTtErN"), new String("TheOtherWord"));
		System.out.println("04 - addIfMatches #3 works when pattern doesn't "
			+ "match? " + !result);  // should return false

		result = partition.addIfMatches(new String("PaTtErN"), new String("TheOtherOtherWord"));
		System.out.println("04 - addIfMatches #4 works when pattern doesn't "
			+ "match? " + !result);  // should return false

		List<String> expectedWords = Arrays.asList(new String[]{new String("TheOneWord")});
		checkPartitionPIVs(
			"04 - addIfMatches test when pattern doesn't match",
			"pAtTeRn", 			// pattern
			expectedWords); 	// words
	}

	/**
	 *  Test getWords
	 */
	public static void test05() 
	{
		partition = new Partition("pAtTeRn", "TheOneWord");

		List<String> expectedWords = Arrays.asList(new String[]{"TheOneWord"});
		assertEquals("05 - getWords ", expectedWords.toString(),
			partition.getWords().toString());
	}

	/**
	 *  Test getPatternDashCount
	 */
	public static void test06() 
	{
		partition = new Partition("--p-A---t-T-e-R-n--", "TheOneWord");
		int count = partition.getPatternDashCount();
		assertEquals("06 - getPatternDashCount", 12, count);
	}

	/**
	 *  Test EvilHangman constructor 
	 */
	public static void test07() throws FileNotFoundException, 
		IllegalAccessException
	{
		setInOut("7 20");
		evil = new EvilHangman("test.txt", false);
		List<String> expectedWords = Arrays.asList(new String[]{"LENGTHY"});
		checkEvilHangmanPIVs(
			"07 - Constructor works correctly (word length 7)",
			false, 			// debug
			expectedWords, 	// words
			7, 				// wordLength
			20,				// remainingGuesses
			"-------",		// solution
			"");			// guessedLetters

		String expected = "Word length? Number of guesses? ";
		String actual = arrayStream.toString();
		assertEquals("07 - Constructor: output", expected, actual);
	}

	/**
	 *  Test EvilHangman constructor
	 */
	public static void test08() throws FileNotFoundException, 
		IllegalAccessException
	{
		setInOut("3 20");
		evil = new EvilHangman("test.txt", true);
		List<String> expectedWords = Arrays.asList(new String[]{
			"BET", "GOO"});

		checkEvilHangmanPIVs(
			"08 - Constructor works correctly (word length 3)",
			true, 			// debug
			expectedWords, 	// words
			3, 				// wordLength
			20,				// remainingGuesses
			"---",			// solution
			"");			// guessedLetters

		String expected = "Word length? Number of guesses? ";
		String actual = arrayStream.toString();
		assertEquals("08 - Constructor: output", expected, actual);
	}

	/**
	 *  Test EvilHangman constructor
	 */
	public static void test09() throws FileNotFoundException, 
		IllegalAccessException
	{
		setInOut("0 -5 10 3 20");
		evil = new EvilHangman("test.txt", false);
		List<String> expectedWords = Arrays.asList(new String[]{
			"BET", "GOO"});

		checkEvilHangmanPIVs(
			"09 - Constructor works correctly (illegal initial word lengths)",
			false, 			// debug
			expectedWords, 	// words
			3, 				// wordLength
			20,				// remainingGuesses
			"---",			// solution
			"");			// guessedLetters

		String expected = "Word length? There are no words with 0 letters. ";
		expected += "Word length? There are no words with -5 letters. ";
		expected += "Word length? There are no words with 10 letters. ";
		expected += "Word length? Number of guesses? ";
		String actual = arrayStream.toString();
		assertEquals("09 - Constructor: output", expected, actual);
	}

	/**
	 *  Test EvilHangman constructor
	 */
	public static void test10() throws FileNotFoundException, 
		IllegalAccessException
	{
		setInOut("4 10");
		evil = new EvilHangman("test.txt", false);
		List<String> expectedWords = Arrays.asList(new String[]{
			"ALLY", "BETA", "COOL", "DEAL", "ELSE", "FLEW", "GOOD",
			"HOPE", "IBEX"});

		checkEvilHangmanPIVs(
			"10 - Constructor works correctly (word length 4)",
			false, 			// debug
			expectedWords, 	// words
			4, 				// wordLength
			10,				// remainingGuesses
			"----",			// solution
			"");			// guessedLetters

		String expected = "Word length? Number of guesses? ";
		String actual = arrayStream.toString();
		assertEquals("10 - Constructor: output", expected, actual);
	}

	/**
	 *  Test toString when debug is false
	 */
	public static void test11() throws FileNotFoundException
	{
		String expected = "\nRemaining guesses: 10\n";
		expected += "Guessed letters: \n";
		expected += "Solution: ----\n";
		String actual = evil.toString();
		assertEquals("11 - toString works correctly (debug is false)",
			expected, actual);
	}

	/**
	 *  Test toString when debug is true
	 */
	public static void test12() throws FileNotFoundException
	{
		setInOut("4 10");
		evil = new EvilHangman("test.txt", true);
		String expected = "\nRemaining guesses: 10\n";
		expected += "Guessed letters: \n";
		expected += "Solution: ----\n";
		expected += "Remaining words: 9\n";
		String actual = evil.toString();
		assertEquals("12 - toString works correctly (debug is true)",
			expected, actual);
	}

	/**
	 *  Test inputLetter: a -> A
	 */
	@SuppressWarnings("unchecked")
	public static void test13() throws FileNotFoundException,
		NoSuchMethodException, IllegalAccessException, InvocationTargetException
	{
		setInOut("4 10 a");
		String expected = "A";

		evil = new EvilHangman("test.txt", true);
		Class c = EvilHangman.class;
		Method method = c.getDeclaredMethod("inputLetter");
		method.setAccessible(true);
		String actual = (String) method.invoke(evil);

		assertEquals("13 - inputLetter works correctly (a -> A)",
			expected, actual);

		expected = "Word length? Number of guesses? ";
		expected += "Next letter? ";
		actual = arrayStream.toString();
		assertEquals("13 - Constructor: output", expected, actual);
	}

	/**
	 *  Test inputLetter: Z -> Z
	 */
	@SuppressWarnings("unchecked")
	public static void test14() throws FileNotFoundException,
		NoSuchMethodException, IllegalAccessException, InvocationTargetException
	{
		setInOut("4 10 Z");
		String expected = "Z";

		evil = new EvilHangman("test.txt", true);
		Class c = EvilHangman.class;
		Method method = c.getDeclaredMethod("inputLetter");
		method.setAccessible(true);
		String actual = (String) method.invoke(evil);

		assertEquals("14 - inputLetter works correctly (Z -> Z)",
			expected, actual);

		expected = "Word length? Number of guesses? ";
		expected += "Next letter? ";
		actual = arrayStream.toString();
		assertEquals("14 - Constructor: output", expected, actual);
	}

	/**
	 *  Test inputLetter: MM A- Z+ x -> X
	 */
	@SuppressWarnings("unchecked")
	public static void test15() throws FileNotFoundException,
		NoSuchMethodException, IllegalAccessException, InvocationTargetException
	{
		setInOut("4 10 MM " + (char)('A' - 1) + " " + (char)('Z' + 1) + " x");
		String expected = "X";

		evil = new EvilHangman("test.txt", true);
		Class c = EvilHangman.class;
		Method method = c.getDeclaredMethod("inputLetter");
		method.setAccessible(true);
		String actual = (String) method.invoke(evil);

		assertEquals("15 - inputLetter works correctly (MM A- Z+ x -> X)",
			expected, actual);

		expected = "Word length? Number of guesses? ";
		expected += "Next letter? Invalid input! ";
		expected += "Next letter? Invalid input! ";
		expected += "Next letter? Invalid input! ";
		expected += "Next letter? ";
		actual = arrayStream.toString();
		assertEquals("15 - Constructor: output", expected, actual);
	}

	/**
	 *  Test getPattern: QQQQQ, Q -> QQQQQ
	 */
	@SuppressWarnings("unchecked")
	public static void test16() throws FileNotFoundException,
		NoSuchMethodException, IllegalAccessException, InvocationTargetException
	{
		Class c = EvilHangman.class;
		Method method = c.getDeclaredMethod(
			"getPattern", String.class, String.class);
		method.setAccessible(true);
		String actual = (String) method.invoke(evil, new String("QQQQQ"), "Q");
		String expected = new String("QQQQQ");

		assertEquals("16 - getPattern works correctly " +
			"(\"QQQQQ\", \"Q\" -> \"QQQQQ\")",
			expected, actual);
	}

	/**
	 *  Test getPattern: QQQQQ, R -> -----
	 */
	@SuppressWarnings("unchecked")
	public static void test17() throws FileNotFoundException,
		NoSuchMethodException, IllegalAccessException, InvocationTargetException
	{
		Class c = EvilHangman.class;
		Method method = c.getDeclaredMethod(
			"getPattern", String.class, String.class);
		method.setAccessible(true);
		String actual = (String) method.invoke(evil, new String("QQQQQ"), "R");
		String expected = new String("-----");

		assertEquals("17 - getPattern works correctly " +
			"(\"QQQQQ\", \"R\" -> \"-----\")",
			expected, actual);
	}

	/**
	 *  Test getPartitionList
	 */
	@SuppressWarnings("unchecked")
	public static void test18() throws FileNotFoundException,
		NoSuchMethodException, IllegalAccessException, InvocationTargetException
	{
		setInOut("");
		Class c = EvilHangman.class;
		Method method = c.getDeclaredMethod("getPartitionList", String.class);
		method.setAccessible(true);
		List<String> patternsE = Arrays.asList(new String[] {
			"----", "-E--", "--E-", "---E", "E--E"});

		List<List<String>> wordGroupsE = new ArrayList<List<String>>();
		wordGroupsE.add(Arrays.asList(new String[] {"ALLY", "COOL", "GOOD"}));
		wordGroupsE.add(Arrays.asList(new String[] {"BETA", "DEAL"}));
		wordGroupsE.add(Arrays.asList(new String[] {"FLEW", "IBEX"}));
		wordGroupsE.add(Arrays.asList(new String[] {"HOPE"}));
		wordGroupsE.add(Arrays.asList(new String[] {"ELSE"}));
		List<Partition> partitionsA =
			(List<Partition>)method.invoke(evil, "E");
		assertEquals("18 - getPartitionList: 5 partitions returned",
			patternsE.size(), partitionsA.size());

		for (int i = 0; i < patternsE.size(); i++)
		{
			String patternE = patternsE.get(i);
			boolean found = false;
			for (Partition partitionA : partitionsA)
			{
				fields = Partition.class.getDeclaredFields();
				AccessibleObject.setAccessible(fields, true);
				String wordPatternA = (String)fields[0].get(partitionA);
				if (wordPatternA.equals(patternE))
				{
					found = true;
					List<String> wordsA = partitionA.getWords();
					List<String> wordsE = wordGroupsE.get(i);
					Collections.sort(wordsA);
					if (! wordsA.equals(wordsE))
					{
						assertEquals("18 - getPartitionList: words for pattern",
							wordsE.size() + wordsE.toString(),
							wordsA.size() + wordsA.toString());
					}
				}
			}
			if (!found)
			{
				assertEquals("18 - getPartitionList: missing pattern",
					patternE, "");
			}
		}
	}

	/**
	 *  Test removeSmallerPartitions: 1 largest
	 */
	@SuppressWarnings("unchecked")
	public static void test19() throws FileNotFoundException,
		NoSuchMethodException, IllegalAccessException, InvocationTargetException
	{
		setInOut("");
		Class c = EvilHangman.class;
		Method method1 = c.getDeclaredMethod("getPartitionList", String.class);
		method1.setAccessible(true);
		Method method2 = c.getDeclaredMethod(
			"removeSmallerPartitions", java.util.List.class);
		method2.setAccessible(true);

		List<String> patternsE = Arrays.asList(new String[] {"----"});
		List<List<String>> wordGroupsE = new ArrayList<List<String>>();
		wordGroupsE.add(Arrays.asList(new String[] {"ALLY", "COOL", "GOOD"}));
		List<Partition> partitionsA = 
			(List<Partition>) method1.invoke(evil, "E");
		method2.invoke(evil, partitionsA);
		assertEquals(
			"19 - removeSmallerPartitions (1 largest): 1 partition returned",
			patternsE.size(), partitionsA.size());

		for (int i = 0; i < patternsE.size(); i++)
		{
			String patternE = patternsE.get(i);
			boolean found = false;
			for (Partition partitionA : partitionsA)
			{
				fields = Partition.class.getDeclaredFields();
				AccessibleObject.setAccessible(fields, true);
				String wordPatternA = (String)fields[0].get(partitionA);
				if (wordPatternA.equals(patternE))
				{
					found = true;
					List<String> wordsA = partitionA.getWords();
					List<String> wordsE = wordGroupsE.get(i);
					Collections.sort(wordsA);
					if (! wordsA.equals(wordsE))
					{
						assertEquals(
							"19 - removeSmallerPartitions (1 largest): " +
								"words for pattern",
							wordsE.size() + wordsE.toString(),
							wordsA.size() + wordsA.toString());
					}
				}
			}
			if (! found)
			{
				assertEquals(
					"19 - removeSmallerPartitions (1 largest): " +
						"missing pattern",
					patternE, "");
			}
		}
	}


	/**
	 *  Test removeSmallerPartitions: 3 largest
	 */
	@SuppressWarnings("unchecked")
	public static void test20() throws FileNotFoundException,
		NoSuchMethodException, IllegalAccessException, InvocationTargetException
	{
		setInOut("");
		Class c = EvilHangman.class;
		Method method1 = c.getDeclaredMethod("getPartitionList", String.class);
		method1.setAccessible(true);
		Method method2 = c.getDeclaredMethod(
			"removeSmallerPartitions", java.util.List.class);
		method2.setAccessible(true);

		List<String> patternsE = Arrays.asList(new String[] {
			"----", "-E--", "--E-"});

		List<List<String>> wordGroupsE = new ArrayList<List<String>>();
		wordGroupsE.add(Arrays.asList(new String[] {"ALLY", "GOOD"})); // no COOL
		wordGroupsE.add(Arrays.asList(new String[] {"BETA", "DEAL"}));
		wordGroupsE.add(Arrays.asList(new String[] {"FLEW", "IBEX"}));
		List<Partition> partitionsA =
			(List<Partition>)method1.invoke(evil, "E");
		// Find and remove COOL
		for (Partition p : partitionsA)
		{
			List<String> words = p.getWords();
			for (int i = words.size() - 1; i >= 0; i--)
			{
				String w = words.get(i);
				if (w.equals("COOL"))
					words.remove(i);
			}
		}
		method2.invoke(evil, partitionsA);
		assertEquals(
			"20 - removeSmallerPartitions (3 largest): 3 partitions returned",
			patternsE.size(), partitionsA.size());

		for (int i = 0; i < patternsE.size(); i++)
		{
			String patternE = patternsE.get(i);
			boolean found = false;
			for (Partition partitionA : partitionsA)
			{
				fields = Partition.class.getDeclaredFields();
				AccessibleObject.setAccessible(fields, true);
				String wordPatternA = (String)fields[0].get(partitionA);
				if (wordPatternA.equals(patternE))
				{
					found = true;
					List<String> wordsA = partitionA.getWords();
					List<String> wordsE = wordGroupsE.get(i);
					Collections.sort(wordsA);
					if (! wordsA.equals(wordsE))
					{
						assertEquals(
							"20 - removeSmallerPartitions (3 largest): " +
								"words for pattern",
							wordsE.size() + wordsE.toString(),
							wordsA.size() + wordsA.toString());
					}
				}
			}
			if (! found)
			{
				assertEquals(
					"20 - removeSmallerPartitions (3 largest): " + 
						"missing pattern ***",
					patternE, "");
			}
		}
	}

	/**
	 *  Test getWordsFromOptimalPartition
	 */
	@SuppressWarnings("unchecked")
	public static void test21() throws FileNotFoundException,
		NoSuchMethodException, IllegalAccessException, InvocationTargetException
	{
		setInOut("");
		Class c = EvilHangman.class;
		Method method1 = c.getDeclaredMethod(
			"getPartitionList", String.class);
		method1.setAccessible(true);
		Method method2 = c.getDeclaredMethod(
			"removeSmallerPartitions", java.util.List.class);
		method2.setAccessible(true);
		Method method3 = c.getDeclaredMethod(
			"getWordsFromOptimalPartition", java.util.List.class);
		method3.setAccessible(true);

		List<String> wordsE = new ArrayList<String>();
		wordsE.add("ALLY");
		wordsE.add("GOOD");

		List<Partition> partitionsA =
			(List<Partition>) method1.invoke(evil, "E");
		// Find and remove COOL
		for (Partition p : partitionsA)
		{
			List<String> words = p.getWords();
			for (int i = words.size() - 1; i >= 0; i--)
			{
				String w = words.get(i);
				if (w.equals("COOL"))
					words.remove(i);
			}
		}
		method2.invoke(evil, partitionsA);
		List<String> wordsA =
			(List<String>)method3.invoke(evil, partitionsA);
		assertEquals("21 - getWordsFromOptimalPartition: word count",
			wordsE.size(), wordsA.size());
		Collections.sort(wordsA);
		assertEquals("21 - getWordsFromOptimalPartition: words",
			wordsE.size() + wordsE.toString(),
			wordsA.size() + wordsA.toString());
	}

	/**
	 *  Test substitute: "----", "ABCD", "E" -> "----"
	 */
	@SuppressWarnings("unchecked")
	public static void test22() throws FileNotFoundException,
		NoSuchMethodException, IllegalAccessException, InvocationTargetException
	{
		Class c = EvilHangman.class;
		fields = c.getDeclaredFields();
		methods = c.getDeclaredMethods();
		Method method = c.getDeclaredMethod(
			"substitute", String.class, String.class);
		method.setAccessible(true);

		method.invoke(evil, "ABCD", "E");

		AccessibleObject.setAccessible(fields, true);
		String actual = (String) fields[5].get(evil);
		String expected = "----";

		assertEquals("22 - substitute", expected, actual);
	}

	/**
	 *  Test substitute: "----", "EBED", "E" -> "E-E-"
	 */
	@SuppressWarnings("unchecked")
	public static void test23() throws FileNotFoundException,
		NoSuchMethodException, IllegalAccessException, InvocationTargetException
	{
		Class c = EvilHangman.class;
		Method method = c.getDeclaredMethod(
			"substitute", String.class, String.class);
		method.setAccessible(true);

		method.invoke(evil, "EBED", "E");

		AccessibleObject.setAccessible(fields, true);
		String actual = (String)fields[5].get(evil);
		String expected = "E-E-";

		assertEquals("23 - substitute",	expected, actual);
	}

	/**
	 *  Test substitute: "E-E-", "AMCM", "M" -> "EMEM"
	 */
	@SuppressWarnings("unchecked")
	public static void test24() throws FileNotFoundException,
		NoSuchMethodException, IllegalAccessException, InvocationTargetException
	{
		Class c = EvilHangman.class;
		Method method = c.getDeclaredMethod(
			"substitute", String.class, String.class);
		method.setAccessible(true);

		method.invoke(evil, "AMCM", "M");

		AccessibleObject.setAccessible(fields, true);
		String actual = (String)fields[5].get(evil);
		String expected = "EMEM";

		assertEquals("24 - substitute", expected, actual);
	}

	/**
	 *  Test playGame step 1
	 */
	@SuppressWarnings("unchecked")
	public static void test25() throws FileNotFoundException, 
		IllegalAccessException
	{
		setInOut("4 1 E");
		evil = new EvilHangman("test.txt", true);
		List<String> expectedWords = Arrays.asList(new String[]{
			"ALLY", "BETA", "COOL", "DEAL", "ELSE", "FLEW", "GOOD",
			"HOPE", "IBEX"});

		checkEvilHangmanPIVs("25 - Game (step 0)",
			true, 			// debug
			expectedWords, 	// words
			4, 				// wordLength
			1,				// remainingGuesses
			"----",			// solution
			"");			// guessedLetters

		evil.playGame();

		expectedWords = Arrays.asList(new String[]{
			"ALLY", "COOL", "GOOD"});
		checkEvilHangmanPIVs("25 - Game (step 1)",
			true, 			// debug
			expectedWords, 	// words
			4, 				// wordLength
			0,				// remainingGuesses
			"----",			// solution
			"E");			// guessedLetters

		String expected = "Word length? Number of guesses? ";

		expected += " Remaining guesses: 1 ";
		expected += "Guessed letters:  ";
		expected += "Solution: ---- ";
		expected += "Remaining words: 9  ";

		expected += "Next letter? ";

		expected += "You lose, sorry! ";
		expected += "The word was \"GOOD\" ";

		String actual = arrayStream.toString();
		actual = actual.replaceAll("ALLY", "GOOD");
		actual = actual.replaceAll("COOL", "GOOD");

		assertEquals("25 - Game (step 1): output",
			expected, actual);
	}

	/**
	 *  Test playGame 2
	 */
	@SuppressWarnings("unchecked")
	public static void test26() throws FileNotFoundException, 
		IllegalAccessException
	{
		setInOut("4 2 E X");
		evil = new EvilHangman("test.txt", true);
		List<String> expectedWords = Arrays.asList(new String[]{
			"ALLY", "BETA", "COOL", "DEAL", "ELSE", "FLEW", "GOOD",
			"HOPE", "IBEX"});

		checkEvilHangmanPIVs("26 - Game (step 0)",
			true, 			// debug
			expectedWords, 	// words
			4, 				// wordLength
			2,				// remainingGuesses
			"----",			// solution
			"");			// guessedLetters

		evil.playGame();

		expectedWords = Arrays.asList(new String[]{
			"ALLY", "COOL", "GOOD"});
		checkEvilHangmanPIVs("26 - Game (step 2)",
			true, 			// debug
			expectedWords, 	// words
			4, 				// wordLength
			0,				// remainingGuesses
			"----",			// solution
			"EX");			// guessedLetters

		String expected = "Word length? Number of guesses? ";

		expected += " Remaining guesses: 2 ";
		expected += "Guessed letters:  ";
		expected += "Solution: ---- ";
		expected += "Remaining words: 9  ";

		expected += "Next letter? ";

		expected += " Remaining guesses: 1 ";
		expected += "Guessed letters: E ";
		expected += "Solution: ---- ";
		expected += "Remaining words: 3  ";

		expected += "Next letter? ";

		expected += "You lose, sorry! ";
		expected += "The word was \"GOOD\" ";

		String actual = arrayStream.toString();
		actual = actual.replaceAll("ALLY", "GOOD");
		actual = actual.replaceAll("COOL", "GOOD");

		assertEquals("26 - Game (step 2): output",
			expected, actual);
	}

	/**
	 *  Test playGame 3
	 */
	@SuppressWarnings("unchecked")
	public static void test27() throws FileNotFoundException, 
		IllegalAccessException
	{
		setInOut("4 3 E X O D");
		evil = new EvilHangman("test.txt", true);
		List<String> expectedWords = Arrays.asList(new String[]{
			"ALLY", "BETA", "COOL", "DEAL", "ELSE", "FLEW", "GOOD",
			"HOPE", "IBEX"});

		checkEvilHangmanPIVs("27 - Game (step 0)",
			true, 			// debug
			expectedWords, 	// words
			4, 				// wordLength
			3,				// remainingGuesses
			"----",			// solution
			"");			// guessedLetters

		evil.playGame();

		expectedWords = Arrays.asList(new String[]{"COOL"});
		checkEvilHangmanPIVs("27 - Game (step 4)",
			true, 			// debug
			expectedWords, 	// words
			4, 				// wordLength
			0,				// remainingGuesses
			"-OO-",			// solution
			"EXOD");		// guessedLetters

		String expected = "Word length? Number of guesses? ";

		expected += " Remaining guesses: 3 ";
		expected += "Guessed letters:  ";
		expected += "Solution: ---- ";
		expected += "Remaining words: 9  ";

		expected += "Next letter? ";

		expected += " Remaining guesses: 2 ";
		expected += "Guessed letters: E ";
		expected += "Solution: ---- ";
		expected += "Remaining words: 3  ";

		expected += "Next letter? ";

		expected += " Remaining guesses: 1 ";
		expected += "Guessed letters: EX ";
		expected += "Solution: ---- ";
		expected += "Remaining words: 3  ";

		expected += "Next letter? ";

		expected += " Remaining guesses: 1 ";
		expected += "Guessed letters: EXO ";
		expected += "Solution: -OO- ";
		expected += "Remaining words: 2  ";

		expected += "Next letter? ";

		expected += "You lose, sorry! ";
		expected += "The word was \"COOL\" ";

		String actual = arrayStream.toString();

		assertEquals("27 - Game (step 4): output",
			expected, actual);
	}

	/**
	 *  Test playGame 4
	 */
	@SuppressWarnings("unchecked")
	public static void test28() throws FileNotFoundException, 
		IllegalAccessException
	{
		setInOut("4 4 E X O D L C");
		evil = new EvilHangman("test.txt", true);
		List<String> expectedWords = Arrays.asList(new String[]{
			"ALLY", "BETA", "COOL", "DEAL", "ELSE", "FLEW", "GOOD",
			"HOPE", "IBEX"});

		checkEvilHangmanPIVs("28 - Game (step 0)",
			true, 			// debug
			expectedWords, 	// words
			4, 				// wordLength
			4,				// remainingGuesses
			"----",			// solution
			"");			// guessedLetters

		evil.playGame();

		expectedWords = Arrays.asList(new String[]{"COOL"});
		checkEvilHangmanPIVs("28 - Game (step 6)",
			true, 			// debug
			expectedWords, 	// words
			4, 				// wordLength
			1,				// remainingGuesses
			"COOL",			// solution
			"EXODLC");		// guessedLetters

		String expected = "Word length? Number of guesses? ";

		expected += " Remaining guesses: 4 ";
		expected += "Guessed letters:  ";
		expected += "Solution: ---- ";
		expected += "Remaining words: 9  ";

		expected += "Next letter? ";

		expected += " Remaining guesses: 3 ";
		expected += "Guessed letters: E ";
		expected += "Solution: ---- ";
		expected += "Remaining words: 3  ";

		expected += "Next letter? ";

		expected += " Remaining guesses: 2 ";
		expected += "Guessed letters: EX ";
		expected += "Solution: ---- ";
		expected += "Remaining words: 3  ";

		expected += "Next letter? ";

		expected += " Remaining guesses: 2 ";
		expected += "Guessed letters: EXO ";
		expected += "Solution: -OO- ";
		expected += "Remaining words: 2  ";

		expected += "Next letter? ";

		expected += " Remaining guesses: 1 ";
		expected += "Guessed letters: EXOD ";
		expected += "Solution: -OO- ";
		expected += "Remaining words: 1  ";

		expected += "Next letter? ";

		expected += " Remaining guesses: 1 ";
		expected += "Guessed letters: EXODL ";
		expected += "Solution: -OOL ";
		expected += "Remaining words: 1  ";

		expected += "Next letter? ";

		expected += "You win, congratulations! ";
		expected += "The word was \"COOL\" ";

		String actual = arrayStream.toString();

		assertEquals("28 - Game (step 6): output",
			expected, actual);
	}


	/**
	 *  Check Partition Instance Variables for accuracy
	 *  @param msg the message to use in displays
	 *  @param pattern the pattern used for this partition
	 *  @param words the expected list of words for this partition
	 */
	@SuppressWarnings("unchecked")
	private static void checkPartitionPIVs(String msg,
		String pattern, List<String> words)
	{
		// reset standard output
		System.setOut(stdOut);
		
		try
		{
			Class c = Partition.class;
			fields = c.getDeclaredFields();
			Field wordP = c.getDeclaredField("wordPattern");
			wordP.setAccessible(true);
			String wordPattern = (String) wordP.get(partition);
			assertEquals(msg + "- \"pattern\"", pattern, wordPattern);
		}
		catch (Exception e)
		{
			System.out.println("Error checkPartitionPIVs: getting wordPattern"
				+ "\n" + e.getMessage());
		}
		
		List<String> partWords = new ArrayList<>(partition.getWords());
		Collections.sort(words);
		Collections.sort(partWords);
		assertEquals(msg + "- \"words\" list", words.toString(), 
			partWords.toString());
	}

	/**
	 *  Check EvilHangman Instance Variables for accuracy
	 *  @param msg the message to use in displays
	 *  @param debug the expected debug setting
	 *  @param words the expected list of words 
	 *  @param wordLength the expected word length 
	 *  @param remainingGuesses the expected number of remaining guesses 
	 *  @param solution the expected solution 
	 *  @param guessedLetters the expected guessed letters 
	 */
	@SuppressWarnings("unchecked")
	private static void checkEvilHangmanPIVs(String msg, boolean debug, 
		List<String> words, int wordLength, int remainingGuesses,  
		String solution, String guessedLetters) throws IllegalAccessException
	{
		// reset standard output
		System.setOut(stdOut);
		
		// Get class information
		Class c = EvilHangman.class;
		Field[] fields = c.getDeclaredFields();
		AccessibleObject.setAccessible(fields, true);
		
		// Track which fields are correct/incorrect
		String[] expected = new String[EH_INSTANCE_VAR.length];
		String[] actual = new String[EH_INSTANCE_VAR.length];
				
		// Check debug variable
		Boolean ehDebug = (Boolean) fields[0].get(evil);
		expected[0] = "" + debug;
		actual[0] = ehDebug.toString();
		
		// Check Scanner variable
		Scanner ehIn = (Scanner) fields[1].get(evil);
		expected[1] = "not null";
		if (ehIn == null)
			actual[1] = "null";
		else
			actual[1] = expected[1];  // not null
		
		// Check list of words
		List<String> ehWords = (List<String>) fields[2].get(evil);
		Collections.sort(ehWords);
		expected[2] = words.toString();
		actual[2] = ehWords.toString();
		
		// Check wordLength variable
		expected[3] = "" + wordLength;
		actual[3] = "" + (Integer) fields[3].get(evil);
		
		// Check number of remaining guesses	
		expected[4] = "" + remainingGuesses;
		actual[4] = "" + (Integer) fields[4].get(evil);
		
		// Check solution variable
		expected[5] = solution;
		actual[5] = (String)fields[5].get(evil);
		
		// Check guessedLetters variable
		expected[6] = guessedLetters;
		actual[6] = (String)fields[6].get(evil);
		
		// Display status
		reportEHPIVstatus(msg, expected, actual);

		// Redirect "standard" output
		System.setOut(out);
	}
	
	/**
	 *  Displays the whether or not the EvilHangman instance variables are correct.
	 *  Only shows those that are incorrect.
	 *  @param msg the initial message to display
	 *  @param expected array of expected values
	 *  @param actual array of actual values
	 */
	private static void reportEHPIVstatus(String msg, String[] expected, String[] actual)
	{
		// Build string to report only errors
		StringBuilder errorStr = new StringBuilder();
		boolean allCorrect = true;
		for (int ndx = 0; ndx < EH_INSTANCE_VAR.length; ndx++)
		{
			if (!expected[ndx].equals(actual[ndx]))
			{
				allCorrect = false;
				errorStr.append("     \"");
				errorStr.append(EH_INSTANCE_VAR[ndx]);
				errorStr.append("\" value is INCORRECT:\n          expected=<");
				errorStr.append(expected[ndx]);
				errorStr.append("\n            actual=<");
				errorStr.append(actual[ndx]);
				errorStr.append("\n");
			}
		}
		
		// Output status
		System.out.print(msg);
		if (allCorrect)
		{
			System.out.println(" -> all instance variables are correct");
		}
		else
		{
			System.out.println(" -> these instance variables are INCORRECT:");
			System.out.println(errorStr);
		}
	}
	
	/**
	 *  Display whether or not expected and actual strings match
	 *  @param msg the initial message to display
	 *  @param expected the expected string value
	 *  @param actual the actual string value to check
	 */
	private static void assertEquals(String msg, String expected, String actual)
	{
		// reset standard output
		System.setOut(stdOut);
		
		expected = expected.replaceAll("\\s+", " ");
		actual = actual.replaceAll("\\s+", " ");

		System.out.print(msg + " is ");
		if (expected.equals(actual))
			System.out.println("correct");
		else
			System.out.println("INCORRECT: \n        expected=<" + expected
				+ ">\n          actual=<" + actual + ">");
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
	 *  Reassign standard input and output streams
	 */
	private static void setInOut(String input)
	{
		// Reassign the "standard" input stream
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		// Reset and Reassign the "standard" output stream.
		arrayStream.reset();
		System.setOut(out);
	}
}
