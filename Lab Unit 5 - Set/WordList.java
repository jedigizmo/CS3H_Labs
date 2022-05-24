/**
 * The WordList class contains the constructor methods and class methods.
 *
 * @author Gikonyo Njendu
 * Collaborators: Nathan Ma, Neil Gupta
 * Teacher Name: Ms.Bailey
 * Period: 6
 * Due Date: 10/21/2021
 */
import java.util.Set;
import java.util.*;
import java.io.*;
 
public class WordList
{
    private File rFile;
    private Set<String> words;
    /*Constructs the WordList object
     *@param file the name of the file to be used
     */
    public WordList(String file)
    {
        setFile(file);
    }
    /*Builds the list of words for the object
     *@param file is the given file name to be used to construct the list
     */
    public void setFile(String file)
    {
        try
        {
            rFile = new File(file);
            Scanner scan = new Scanner(rFile);
            words = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
            while (scan.hasNext())
            {
                String s = scan.next();
                s = s.replaceAll("[\\s\\.\\!\\?\\(\\),;:]+" , "");
                words.add(s);
            }
            scan.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
    /*returns the list of words
     *@return returns the set of words associated with the object
     */
    public Set<String> getWords()
    {
        return words;
    }
    /*combines to lists in alphabetical order with no duplicates
     *@return returns the combined list of words of two sets, with no repeats
     */
    public Set<String> getAllWords(Set<String> moreWords)
    {
        Set<String> out = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
        out.addAll(moreWords);
        out.addAll(words);
        return out;
    }
    /*finds the common words between the two sets and returns the list
     *@return returns the common words between the two sets
     */
    public Set<String> getCommonWords(Set<String> moreWords)
    {
        Set<String> out = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
        out.addAll(moreWords);
        out.retainAll(words);
        return out;
    }
}
