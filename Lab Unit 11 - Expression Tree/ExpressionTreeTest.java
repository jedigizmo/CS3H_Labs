+/*
 *	ExpressionTreeTest.java
 *
 *	Test the ExpressionTree class
 */

import javax.swing.*;

public class ExpressionTreeTest 
{
	public static void main(String[] args) 
	{
		ExpressionTree tree = new ExpressionTree();
		String input;
		boolean done = false;
		while (!done) 
		{
			input = JOptionPane.showInputDialog(null,
					"Enter prefix expression (cancel to quit):");
			if (input == null) 
			{
				done = true;
			}
			else
			{
				tree.setExpression(input);
				JOptionPane.showMessageDialog(null,
					input + "\nEvaluates to:\n" +
					tree + " = " + String.format("%.3f", tree.evaluate()));
			}
		}
		System.exit(0);
	}
}