/**
 * EvilHangmanMain.java  06/04/2015
 * Driver for the EvilHangman program
 *
 * @author - Robert Glen Martin
 * @author - School for the Talented and Gifted
 * @author - Dallas ISD
 */

import java.io.FileNotFoundException;

public class EvilHangmanMain
{
	public static void main(String[] args) throws FileNotFoundException
	{
		EvilHangman evil = new EvilHangman("test.txt", true);
		//EvilHangman evil = new EvilHangman("dictionary.txt", false);
		evil.playGame();
	}
}
