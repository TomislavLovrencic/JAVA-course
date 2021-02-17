package hr.fer.oprpp1.custom.scripting.nodes;


/**
 * @author Tomislav Lovrencic
 *
 *This class represents a TextNode. It cant have child nodes.
 *
 */
public class TextNode extends Node{
	
	
	/**
	 * value of TextNode is a string.
	 */
	private String text;
	
	
	/**
	 *  This is a basic constructor.
	 * @param text
	 */
	public TextNode(String text) {
		this.text = text;
	}
	
	/**
	 * This is a getter for value of TextNode.
	 * 
	 * @return text value.
	 */
	public String getText() {
		return text;
	}
	
	@Override
	public void addChildNode(Node child) {
		return;
	}
	@Override
	public Node getChild(int index) {
		return null;
	}
	@Override
	public int numberOfChildren() {
		return 0;
	}
	@Override
	public boolean equals(Object obj) {
		TextNode ob = (TextNode) obj;
		return this.text.equals(ob.text);
	}

	@Override
	public String toString() {
		return this.text;
	}
}
