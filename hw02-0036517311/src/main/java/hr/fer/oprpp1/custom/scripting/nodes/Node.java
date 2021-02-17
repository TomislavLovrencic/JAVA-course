package hr.fer.oprpp1.custom.scripting.nodes;

/**
 * @author Tomislav Lovrencic
 *
 *This class represents a Node. Its a basic element of constructing a parser. 
 * Class is abstract and every more specific node has its methods.
 *
 */
public abstract class Node {


	
	/**
	 * This method is used to add a child in a collection of nodes.
	 * 
	 * @param child Node to be added in collection.
	 */
	public abstract void addChildNode(Node child);
	
	
	/**
	 * This method is used to check the number of children this node has.
	 * 
	 * @return number of children.
	 */
	public abstract int numberOfChildren();
	
	
	/**
	 * This method is used to get a node on given index.
	 * 
	 * @param index 
	 * @return Node on this index.
	 */
	public abstract Node getChild(int index);
	
	
	
	
}
