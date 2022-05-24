/**
 * DoubleNode.java
 * Class representing a generic doubly linked list node
 *
 * @author Gikonyo Njendu
 * Collaborators: Nathan Ma, Neil Gupta, Ayon Das
 * Teacher Name: Ms.Bailey
 * Period: 6
 * Due Date: 12/08/2021
 */

public class DoubleNode<T>
{
	private T value;
	private DoubleNode<T> next;
	private DoubleNode<T> previous;
	
	/** Construct a node with the given value and the next and prev references
	 *  @param initValue the value to store in this node
	 *  @param initNext the next node in the list
	 *  @param initPrev the previous node in the list
	 */
	public DoubleNode(T initValue, DoubleNode<T> initNext,
					   DoubleNode<T> initPrev) 
	{
		value = initValue;
		next = initNext;
		previous = initPrev;
	}
	
	/** Retrieve this node's value
	 *  @return the value stored in this node
	 */
	public T getValue() 
	{ 
		return value; 
	}
	
	/** Retrieve this node's next node
	 *  @return the reference to the next node in the list
	 */
	public DoubleNode<T> getNext() 
	{ 
		return next;
	}
	
	/** Retrieve this node's previous node
	 *  @return the reference to the previous node in the list
	 */
	public DoubleNode<T> getPrevious() 
	{ 
		return previous; 
	}
	
	/** Change the value stored in this node
	 *  @param theNewValue the new value to store in this node
	 */
	public void setValue(T theNewValue) 
	{
		value = theNewValue;
	}
	
	/** Change the reference to the next node after this node
	 *  @param theNewNext the next node in the list
	 */
	public void setNext(DoubleNode<T> theNewNext) 
	{
		next = theNewNext;
	}
	
	/** Change the reference to the previous node after this node
	 *  @param theNewPrev the previous node in the list
	 */
	public void setPrevious(DoubleNode<T> theNewPrev) 
	{
		previous = theNewPrev;
	}
}