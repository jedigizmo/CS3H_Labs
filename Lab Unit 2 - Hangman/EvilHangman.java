/** 
 * The EvilHangman class creates te evil hangman object and has the different methods
 *
 * @author Gikonyo Njendu
 * Collaborators: Nathan Ma
 * Teacher Name: Ms.Bailey
 * Period: 06
 * Due Date: 08/30/2021
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
		while ((solution.indexOf("-") != 1) && (remainingGuesses >= 0))
		{
			System.out.println(this);
			String let = inputLetter();
			guessedLetters += let;
			List<Partition> gameList = getPartitionList(let);
			removeSmallerPartitions(gameList);
			wordList = getWordsFromOptimalPartition(gameList);
			String tempo = solution;
			substitute(tempo,let);
			if(solution.equals(tempo))
			{
				remainingGuesses--;
			}
		}
		if (remainingGuesses != 0)
		{
			System.out.println("You win, congratulations!");
		}
		else
		{
			System.out.println("You lose, sorry!");
		}
		System.out.println("The word was \"" + wordList.get((int) (Math.random() * wordList.size())) + "\"");
	}
	
	/**
	 *  toString method creates a string
	 *@return returns string of remaining guesses, guessed letters, solution and remaining words if necessary
	 */
	public String toString()
	{
		if (debug)
		{
			return " Remaining guesses: " + remainingGuesses + "\nGuessed letters: " + guessedLetters + "\nSolution: " + solution + "\nRemaining words: " + wordList.size() + " ";
		}
		return " Remaining guesses: " + remainingGuesses + "\nGuessed letters: " + guessedLetters + "\nSolution: " + solution + " ";
	}
	
	
	////////// PRIVATE HELPER METHODS //////////
	/**
	 *  inputWords method adds the words with the same length to the wordList
	 *@throws FileNotFoundException if the file that is referenced does not exist
	 */
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
	/**
	 *  inputLetter prompts a string and checks if the letter is between A-Z
	 *@return returns the letter if it is between A-Z
	 */
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
	/**
	 *  getPattern checks which words in a string match the given letter
	 *@return returns a new string with the unmatching words filled in with "-"
	 */
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
	/**
	 *  addPartitionList creates partitions and adds words the partitions
	 *@return returns new list of partitions that hold words
	 */
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
	/**
	 *  removes the partitions that are smaller than the largest partition
	 */
	private void removeSmallerPartitions(List<Partition> partitions)
	{
		int longestInd = 0;
		int longestWords = 0;
		for (int x = 0; x < partitions.size(); x++)
		{
			if (partitions.get(x).getWords().size() > longestWords)
			{
				longestWords = partitions.get(x).getWords().size();
				longestInd = x;
			}
		}
		for (int x = 0; x < partitions.size(); x++)
		{
			if (partitions.get(x).getWords().size() < longestWords)
			{
				partitions.remove(x);
				x--;
			}
		}
		
	}	
		/**
	 *  getWordsFromOptimalPartition finds the partition with the most partitions and returns the partition
	 *@return returns the partition with the most dashes
	 */
	private List<String> getWordsFromOptimalPartition(List<Partition> partitions)
	{
		int longestInd = 0;
		int longestDashes = 0;
		for (int x = 0; x < partitions.size(); x++)
		{
			if (partitions.get(x).getPatternDashCount() > longestDashes)
			{
				longestDashes = partitions.get(x).getPatternDashCount();
				longestInd = x;
			}
		}
		return partitions.get(longestInd).getWords();
	}
	/**
	 *  
	 */
	private void substitute(String found, String letter)
	{
		String newSolution = "";
		for (int x = 0; x < found.length(); x++)
		{
			if (found.substring(x,x + 1).equals(letter))
			{
				newSolution += letter;
			}
			else
			{
				newSolution += solution.substring(x,x + 1);
			}
		}
		solution = newSolution;
	}
}
