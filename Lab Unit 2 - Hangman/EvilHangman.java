/**
 * Heading goes here
 *
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class EvilHangman
{
	private boolean debug;
	private Scanner in;
	private List<String> wordList;
	private int wordLength;
	private int remainingGuesses;
	private String solution;
	private String guessedLetters;

	public EvilHangman(String fileName, boolean debug)
		throws FileNotFoundException
	{
		this.debug = (debug == true);
		in = new Scanner (System.in);
		inputWords(fileName);
		System.out.print("Number of guesses? ");
		remainingGuesses = in.nextInt();
		solution = "";
		for (int i = 0; i < wordLength; i++)
		{
			solution += "-";
		}
		guessedLetters = "";
	}
	
	public void playGame()
	{
	
	}
	
	
	public String toString()
	{
		if (debug)
		{
			return " Remaining guesses: " + remainingGuesses + "\nGuessed letters: " + guessedLetters + "\nSolution: " + solution + "\nRemaining words: " + wordList.size() + " ";
		}
		return " Remaining guesses: " + remainingGuesses + "\nGuessed letters: " + guessedLetters + "\nSolution: " + solution + " ";
	}
	
	
	////////// PRIVATE HELPER METHODS //////////
	
	private void inputWords(String fileName) throws FileNotFoundException
	{
		wordList = new ArrayList<String>();
		String fileString = "";
		while (wordList.size() == 0)
		{
			System.out.print("Word length? ");
			wordLength = in.nextInt();
			Scanner file = new Scanner(new File(fileName));
			while (file.hasNext())
			{
				fileString = file.next();
				if (fileString.length() == wordLength)
				{
					wordList.add(fileString);
				}
			}
		if (wordList.size() == 0)
		{
		System.out.println("There are no words with " + wordLength + " letters.");
		}
		}
	}
	
	private String inputLetter()
	{
		while(true)
		{
			System.out.print("Next letter? ");
			String letter = in.next();
			letter = letter.toUpperCase();
			if ((letter.length() == 1))
			{
				if ((((int) letter.charAt(0)) >= 65) && ((int) letter.charAt(0) <= 90))
				{
					return letter;
				}
			}
			System.out.println("Invalid input!");
		}
	}
	
	private String getPattern(String word, String letter)
	{
		String outWord = "";
		for (int x = 0; x < word.length(); x++)
		{
			if (word.substring(x,x + 1).equals(letter))
			{
				outWord += letter;
			}
			else
			{
				outWord += "-";
			}
		}
		return outWord;
	}
	
	private List<Partition> getPartitionList(String letter)
	{
		ArrayList<Partition> partitions = new ArrayList<Partition>();
		boolean changed = false;
		for (int i = 0; i < wordList.size(); i++)
		{
			if (partitions.size() == 0) 
			{
			partitions.add(new Partition(getPattern(wordList.get(i), letter), wordList.get(i)));
			}
			else 
			{	
				for (int x = 0; x < partitions.size(); x++)
				{
					if (partitions.get(x).addIfMatches(getPattern(wordList.get(i), letter), wordList.get(i)))
					{
						changed = true;
						break;
					}
				}
			if (!(changed))
			{
			partitions.add(new Partition(getPattern(wordList.get(i), letter), wordList.get(i)));
			}
			changed = false;
			}
		}
		return partitions;
	}
	
	private void removeSmallerPartitions(List<Partition> partitions)
	{
	
	}
	
	private List<String> getWordsFromOptimalPartition(List<Partition> partitions)
	{
		return null;	// REPLACE
	}
	
	private void substitute(String found, String letter)
	{
	
	}
}
