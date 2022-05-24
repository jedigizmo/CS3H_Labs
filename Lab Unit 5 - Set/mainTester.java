/**
 * The mainTester tests the WordList class and takes input on file names from user
 *
 * @author Gikonyo Njendu
 * Collaborators: Nathan Ma, Neil Gupta
 * Teacher Name: Ms.Bailey
 * Period: 6
 * Due Date: 10/21/2021
 */
import java.util.*;
import java.io.*;
 
public class mainTester {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter file to read: ");
        String fileName = sc.nextLine();
        File rfile = new File(contt(fileName , 1));
        fileName = rfile.getName();
 
        if (rfile.exists())
        {
        WordList wordsOne = new WordList(fileName);
        System.out.println("Word list from " + fileName);
        System.out.println(wordsOne.getWords());
        System.out.println();
        System.out.print("Enter another file to read to union with first: ");
        String fileNameTwo = sc.nextLine();
        File rfileTwo = new File(contt(fileNameTwo, 2));
        fileNameTwo = rfileTwo.getName();
 
        if (rfileTwo.exists())
        {
        WordList wordsTwo = new WordList(fileNameTwo);
        System.out.println("Word list from " + fileNameTwo);
        System.out.println(wordsTwo.getWords());
        System.out.println();
        System.out.println("All words from " + fileName + " and " + fileNameTwo);
        System.out.println(wordsOne.getAllWords(wordsTwo.getWords()));
        System.out.println();
        System.out.print("Enter another file to read to intersect with first: ");
        String fileNameThree = sc.nextLine();
        File rfileThree = new File(contt(fileNameThree, 3));
        fileNameThree = rfileThree.getName();
       
        if (rfileThree.exists())
        {
        WordList wordsThree = new WordList(fileNameThree);
        System.out.println("Word list from " + fileNameThree);
        System.out.println(wordsThree.getWords());
        System.out.println();
        System.out.println("Common words from " + fileName + " and " + fileNameThree);
        System.out.println(wordsOne.getCommonWords(wordsThree.getWords()));
        }
        }
        }
        sc.close();
    }
    /*Checks to see if the given file name is true or not, if not it prompts user
     *@param fileName is the given file name to be tested
     *@param pass checks the iteration to make sure the prompt is correct.
     *@return returns the filename if either it exists or the user stops the program
     */
    public static String contt(String fileName, int pass)
    {
        File rfile = new File(fileName);
        boolean cont = true;
        Scanner scan = new Scanner(System.in);
        while (!rfile.exists() && cont)
        {
        System.out.print(fileName + " does not exist. Do you want to try again (y/n)? ");
        String con = scan.nextLine();
        con = con.toLowerCase();
        if (con.substring(0,1).equals("y"))
        {
        System.out.println();
        switch (pass) {
            case 1:  System.out.print("Enter file to read: ");
                     break;
            case 2:  System.out.print("Enter another file to read to union with first: ");
                     break;
            case 3:  System.out.print("Enter another file to read to intersect with first: ");
                     break;
        }
        rfile = new File(scan.nextLine());
        fileName = rfile.getName();
        }
        else
        {
            cont = false;
        }
        }
        return fileName;
    }
}
