/** 
 * The Polynomial Class holds the methods and constructor and the addterm inner class
 *
 * @author Gikonyo Njendu
 * Collaborators: Nathan Ma, Neil Gupta
 * Teacher Name: Ms.Bailey
 * Period: 06
 * Due Date: 11/12/2021
 */
public class Polynomial 
{
	private ListNode<Term> list;
		
	/** 
	 *Constructor method for the Polynomial class creates empty list
	 */
	public Polynomial() 
	{
		list = null;
	}
	/** Construct a node with the given value and next reference
	 *  @param coeff the coefficent of the polynomial to be added
	 *  @param power the power of the polynomial to be added
	 */
	public void addTerm(int coeff, int power) 
	{
		ListNode<Term> newNode = new ListNode<>((new Term(coeff, power)) , null);
		int test = newNode.getValue().getPower();
		if (list == null)
		{
			list = newNode;
		}
		else if (test >= list.getValue().getPower())
		{
			newNode.setNext(list);
			list = newNode;
		}
		else
		{
			ListNode<Term> previous = list;
			ListNode<Term> current = list.getNext();
			while ((current != null) && (test < current.getValue().getPower()))
			{
				previous = current;
				current = current.getNext();
			}
			previous.setNext(newNode);
			newNode.setNext(current);
		}
	}
	/** sum method adds the two polynomials together
	 *  @param other the other polynomial to add
	 *  @return polynomial that has the summation of the two polynomials
	 */
	public Polynomial sum(Polynomial other) 
	{
		Polynomial out = new Polynomial();
		ListNode<Term> temp = this.list;
		ListNode<Term> tempsecond = other.list;
		while ((temp.getNext() != null) && (tempsecond.getNext() != null))
		{
			if(temp.getValue().getPower() == tempsecond.getValue().getPower())
			{
            out.addTerm((temp.getValue().getCoefficient() + tempsecond.getValue().getCoefficient()), temp.getValue().getPower());
				temp = temp.getNext();
				tempsecond = temp.getNext();
			}
			else if(temp.getValue().getPower() > tempsecond.getValue().getPower())
			{
				out.addTerm(temp.getValue().getCoefficient(), temp.getValue().getPower());
				temp = temp.getNext();
			}
			else if(temp.getValue().getPower() < tempsecond.getValue().getPower())
			{
				out.addTerm(tempsecond.getValue().getCoefficient(), tempsecond.getValue().getPower());
				tempsecond = tempsecond.getNext();
			}
		}
//	return out; // dummy return
	return null;
	}
	/** toString method converts the node value to a string to be printed out
	 *  @return string returns a string that holds the polynomial object to be printed out
	 */
	@Override
	public String toString() 
	{
		String out = "";
		if (list != null)
		{
			ListNode<Term> temp;
			for (temp = list; temp != null; temp = temp.getNext())
			{
				out = out + temp.getValue(); 
				if(temp.getNext() != null)
				{
				out += " + ";
				}
			}
		}
		else
		{
			out = "0";
		}
		return out; 
	}
	

/** 
 * Private Inner class
 *
 * @author Gikonyo Njendu
 * Collaborators: Nathan Ma, Neil Gupta
 * Teacher Name: Ms.Bailey
 * Period: 06
 * Due Date: 11/12/2021
 */
	private class Term 
	{
		/** Coefficient and exponent of a polynomial term
		 */
		private int coeff;
		private int power;
	
		/**  Constructs a term with the given coefficient and exponent
		 *   @param co the coefficient for this term
		 *   @param pwr the exponent for this term
		 */
		public Term(int co, int pwr) 
		{
			coeff = co;
			power = pwr;
		}
		
		/** Returns the coefficient for this term
		 *  @return coefficient of this term
		 */
		public int getCoefficient() 
		{
			return coeff;
		}
		
		/** Returns the exponent for this term
		 *  @return exponent of this term
		 */
		public int getPower() 
		{
			return power;
		}
		/** toString method converts the addTerm object into a string to be printed out.
	 *  @return string that holds the Polynomial to be printed out
	 */
		public String toString()
		{
			String build = "";
			if (coeff == 1)
			{
			build += "x";
			}
			else if(coeff == -1)
			{
			build += "-x";
			}
			else
			{
			build += String.format("%dx", coeff);
			}
			if (power == 0)
			{
			build = Integer.toString(coeff);
			}
			else if(power > 1)
			{
			build += String.format("^%d", power);
			}
			if (coeff == 0)
			{
			build = "";
			}
			return build;
		}
	}
}