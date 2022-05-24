/**
 *	ExpressionType.java
 *	Enumerated type to represent the types of nodes in an expression tree:
 *  Operands (NUMBER) & Operators (+, -, *, /, %).
 */

public enum NodeType 
{
	NUMBER(""), 
	ADD("+"), 
	SUBTRACT("-"), 
	MULTIPLY("*"), 
	DIVIDE("/"), 
	REMAINDER("%"), 
	EXPONENT("^");
	
	private String symbol;
	
	/** Create node type with a given symbol 
	 *  @param symbol the symbol associated with this type
	 */
	NodeType(String symbol)
	{
		this.symbol = symbol;
	}
	
	/** @return this type's symbol
	 */
	public String getSymbol()
	{
		return this.symbol;
	}
} 