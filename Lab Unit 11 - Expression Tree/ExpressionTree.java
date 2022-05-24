/**
 * ExpressionTree.java
 * Class that holds the ExpressionTree constructor and various methods
 *
 * @author Gikonyo Njendu
 * Collaborators: Takuto Ban
 * Teacher Name: Ms.Bailey
 * Period: 6
 * Due Date: 3/18/2022
 */ 
 
import java.util.Scanner;
 
public class ExpressionTree
{
    /** Root of the expression tree */
    private ExpressionNode root;
   
    /** Used to parse a prefix expression */    
    private Scanner strScan; 
    	   
   /**
	 * Construtor for the ExpressionTree class
	 */
    public ExpressionTree()
    {
        root = null;
    }
    /**
	 * ExpressionTree constructor that initializes tree and builds it
	 * @param prefix if the string that is going to parsed
	 */
    public ExpressionTree(String prefix)
    {
        this();
        setExpression(prefix);
    }
 /**
	 * setExpression takes in the string, checks if empty or not
	 *	then calls helper method to build out tree
	 * @param prefix is the string to be parsed
	 */
    public void setExpression(String prefix)
    {
        strScan = new Scanner(prefix);
        if(strScan.hasNext()){
            root = parseExpression(strScan.next(), root);
        }
    }
    /**
	 * Suppresses menu from appearing when any Location is clicked
	 * @param token is the expression or number to be added
	 * @param parent is the node to build the tree on, starts at root
	 * @return the node to be attached
	 */
    private ExpressionNode parseExpression(String token, ExpressionNode parent){
        NodeType hold = null;
        switch(token){
            case "+": hold = NodeType.ADD;
            break;
            case "-": hold = NodeType.SUBTRACT;
            break;
            case "*": hold = NodeType.MULTIPLY;
            break;
            case "/": hold = NodeType.DIVIDE;
            break;
            case "%": hold = NodeType.REMAINDER;
            break;
            case "^": hold = NodeType.EXPONENT;
            break;
            default: hold = NodeType.NUMBER;
            break;
        }
        if(!hold.equals(NodeType.NUMBER)){
            ExpressionNode left = parseExpression(strScan.next(), null);
            ExpressionNode right = parseExpression(strScan.next(), null);
            parent = new ExpressionNode(hold, left, right);
        }
        else{
            parent = new ExpressionNode(Integer.parseInt(token));
        }
        return parent;
 
    }
 	/**
	 * evaluate method calls a helper recursive method 
	 * @return the double of the calculated expression tree
	 */
    public double evaluate()
    {
        return calculate(root);
    }
    /**
	 * Recursive helper method to calculate the Tree
	 * @param the node to calculate the expression tree from
	 * @return the double of the calculated expression tree
	 */
    private double calculate(ExpressionNode node){
        if(node == null){
            return 0.0;
        }else if(node.getType() == NodeType.NUMBER){
            return (double) node.getValue();
        }
            double left = calculate(node.getLeft());
            double right = calculate(node.getRight());
            switch(node.getType().getSymbol()){
                case "+": return (double)(left + right);
                case "-": return (double)(left - right);
                case "*": return (double)(left * right);
                case "/": return (double)(left / right);
                case "%": return (double)(left % right);
                case "^": return Math.pow(left, right);
            }
        return 0.0;
    }
 	/**
	 * Converts the expression tree into a string with brackets
	 * by calling a helper recursive method
	 * @return the collected string of the tree
	 */
    @Override
    public String toString()
    {
        return stringTree(root);
    }
    /**
	 * Helper recursive method that builds out and adds brackets
	 * @param the starting node to make a string out of the tree
	 * @return the collected string of the tree
	 */
    private String stringTree(ExpressionNode node){
        if (node == null){
            return "";
        }
        else if (node.getType() == NodeType.NUMBER){
            return node.getValue() + "";
        }
        else{
            return "(" + stringTree(node.getLeft()) + " " + node.getType().getSymbol() + " "  + stringTree(node.getRight()) + ")";
        }
    }
 
}

