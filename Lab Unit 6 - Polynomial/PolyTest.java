/*
 * PolyTest.java
 *
 * Tests the Polynomial Class
 *@author Tracy Ishman
 */
 
public class PolyTest 
{
	public static void main(String[] args) 
	{
		Polynomial p = new Polynomial();
		System.out.println("p(x) = " + p);
		p.addTerm(9, 7);
		p.addTerm(-10, 0);
		p.addTerm(5, 10);
		p.addTerm(-1, 1);
		p.addTerm(5, 20);
		p.addTerm(5, 8);
		p.addTerm(5, 3);
		System.out.println("p(x) = " + p);
		System.out.println();
		
		Polynomial q = new Polynomial();
		q.addTerm(5, 6);
		q.addTerm(4, 3);
		q.addTerm(-17, 1);
		q.addTerm(-5, 10);
		q.addTerm(4, 7);
		System.out.println("q(x) = " + q);
		System.out.println();
		
		Polynomial sum = p.sum(q);
		System.out.println("p(x) + q(x) = " + sum);
		System.out.println();
		System.out.println("p(x) = " + p);
		System.out.println("q(x) = " + q);
		System.out.println();
	}
}