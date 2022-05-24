/**
 * TwitterTester.java
 * TwitterTester tests the Twitter class
 *
 * @author Gikonyo Njendu
 * Collaborators: Takuto Ban, Nikhil Venkatachalam
 * Teacher Name: Ms.Bailey
 * Period: 6
 * Due Date: 04/13/2022
 */
 
import java.io.*;
import java.util.*;

public class TwitterTester
{
	public static void main(String[] args) throws IOException
	{
		System.out.println("Testing Person class: ");

        List<Person> test = new ArrayList<>();
        Person p1 = new Person("Jordan Wolf", "@alphawolf");
        Person p2 = new Person("Heidi Klum", "@runway");
        Person p3 = new Person("Tim Gunn", "@makeItWork");
        test.add(p1);
        test.add(p2);
        test.add(p3);

        System.out.println("p1 name: " + p1.getName());
        System.out.println("p1 username: " + p1.getUserName());
        System.out.println("p2 name: " + p2.getName());
        System.out.println("p2 username: " + p2.getUserName());
        System.out.println("p3 name: " + p3.getName());
        System.out.println("p3 username: " + p3.getUserName());

        System.out.println("p1.equals(p2): " + p1.equals(p2));

        Person p1Clone = p1;
        System.out.println("p1.equals(p1Clone): " + p1.equals(p1Clone));
        System.out.println("peeps: " + test);
        System.out.println();

        System.out.println("Testing Twitter class:");
        System.out.println("Test #1: ");
        System.out.println("Person => followers");

        Twitter tw = new Twitter(test);
        tw.addFollower(p1, p2);
        tw.addFollower(p1, p3);
        tw.addFollower(p2, p3);

        System.out.println(p1.getUserName() + " => " + tw.getFollowers(p1));
        System.out.println(p2.getUserName() + " => " + tw.getFollowers(p2));
        System.out.println(p3.getUserName() + " => " + tw.getFollowers(p3));
        System.out.println();
        System.out.println("Most popular: " + tw.getMostPopular());
        System.out.println();

        System.out.println("Test #2: ");
        System.out.println("Person => followers");
        tw.removeFollower(p1, p3);
        System.out.println(p1.getUserName() + " => " + tw.getFollowers(p1));
        System.out.println(p2.getUserName() + " => " + tw.getFollowers(p2));
        System.out.println(p3.getUserName() + " => " + tw.getFollowers(p3));
        System.out.println();
        System.out.println("Most popular: " + tw.getMostPopular());
        System.out.println();
        
        //------------------------------------------------------------
        try
        {
            System.out.println("Test #3: Testing twitter_input.txt");
            System.out.println("Person => Followers");
            File file = new File("twitter_input.txt");
            Scanner scanner = new Scanner(file);
            List<Person> people = new ArrayList<>();

            while(scanner.hasNextLine())
            {

                String name = "";
                String userName = "";
                String line  = scanner.nextLine();
                String[] tempArr = line.split("\\s+");
                for(String str : tempArr)
                {
                    if(str.contains("@"))
                        userName = str;
                    else
                        name += str + " ";
                }
				
                people.add(new Person(name.trim(), userName));
            }

            Twitter tempTwitter = new Twitter(people);
            for(int i = 0; i < people.size(); i++)
            {
                int firstRand = (int) (Math.random() * people.size());
                int secondRand = (int) (Math.random() * people.size());
                tempTwitter.addFollower(people.get(firstRand), people.get(secondRand));
            }

            for (Person temp : people)
                System.out.println(temp.getUserName() + " => " + tempTwitter.getFollowers(temp));

            System.out.println();
            System.out.println("Most popular: " + tempTwitter.getMostPopular());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

	}
}
