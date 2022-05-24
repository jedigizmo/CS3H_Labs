/**
 * ExpressionNode.java
 * Class representing a node in an expression tree
 */
public class ExpressionNode 
{
	private NodeType type;
	private int operandVal;
	private ExpressionNode left;
	private ExpressionNode right;

	/** Creates an expression node for an operand (NUMBER) with the given value and
	 *  no children.
	 *  @param value the numeric value for an operand 
	 */
	public ExpressionNode(int value) 
	{
		this.type = NodeType.NUMBER;
		this.operandVal = value;  
		this.left = null;  
		this.right = null;
	}
	
	/** Creates an expression node for an operator (not NUMBER). Initializes the 
	 *  value to 0 and the children to the given references.
	 *  @param type the type of operator 
	 *  @param left the left child reference
	 *  @param right the right child reference
	 */
	public ExpressionNode(NodeType operatorType, ExpressionNode left, ExpressionNode right) 
	{
		this.type = operatorType;
		this.operandVal = 0;  
		this.left = left;  
		this.right = right;
	}
	
	/** Retrieve this node's type
	 *  @return the node type
	 */
	public NodeType getType()
	{
		return this.type;
	}
	
	/** Retrieve this operand node's value
	 *  @return value stored in this node 
	 */
	public int getValue() 
	{ 
		return this.operandVal; 
	}
	
	/** Retrieve this node's left child
	 *  @return left child 
	 */
	public ExpressionNode getLeft() 
	{ 
		return this.left; 
	}
	
	/** Retrieve this node's right child
	 *  @return right child 
	 */
	public ExpressionNode getRight() 
	{ 
		return this.right; 
	}
	
	/** Change value to resetValue 
	 *  @param resetValue the new value for this node
	 */
	public void setValue(int resetValue) 
	{ 
		this.operandVal = resetValue; 
	}
	
	/** Reset left child to left
	 *  @param left the new left child reference
	 */
	public void setLeft(ExpressionNode left) 
	{ 
		this.left = left; 
	}
	
	/** Reset right child to right
	 *  @param right the new right child reference
	 */
	public void setRight(ExpressionNode right) 
	{ 
		this.right = right; 
	}
}