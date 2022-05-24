/*
 * ListNode.java
 * Class representing a linked list node 
 * @author Tracy Ishman
 */
public class ListNode<T> 
{
	private T value;
	private ListNode<T> next;
	
	/** Construct a node with the given value and next reference
	 *  @param initValue the value to store in this node
	 *  @param initNext the next node in the list
	 */
	public ListNode(T initValue, ListNode<T> initNext) 
	{
		value = initValue;
		next = initNext;
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
	public ListNode<T> getNext() 
	{
		return next; 
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
	public void setNext(ListNode<T> theNewNext) 
	{
		next = theNewNext;
	}
}