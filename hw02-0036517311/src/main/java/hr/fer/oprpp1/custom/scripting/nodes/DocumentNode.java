package hr.fer.oprpp1.custom.scripting.nodes;

import hr.fer.oprpp1.custom.collections.ArrayIndexedCollection;



/**
 * @author Tomislav Lovrencic
 * 
 * This is a class that represents a root node of constructed parser.
 * {@link hr.fer.oprpp.custom.scripting.parser.SmartScriptParser}
 *
 */
public class DocumentNode extends Node{

	/**
	 * Collection of child nodes.
	 */
	private ArrayIndexedCollection col;
	
	@Override
	public void addChildNode(Node child) {
		if(col == null) {
			col = new ArrayIndexedCollection();
		}
		col.add(child);
		
	}

	@Override
	public int numberOfChildren() {
		return col.size();
	}

	@Override
	public Node getChild(int index) {
		if(col.get(index) != null) {
			return (Node) col.get(index);
		}
		
		throw new IndexOutOfBoundsException();
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof DocumentNode)){
			return false;

		}
		DocumentNode node = (DocumentNode) obj;
		
		
		for(int i=0;i<this.numberOfChildren();i++) {
			
			if(!(this.getChild(i).equals(node.getChild(i)))) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
    	
		for(int i =0;i<this.numberOfChildren();i++) {
			Node helper = this.getChild(i);
			
			if(helper instanceof TextNode) {
				str.append(helper.toString());
			}
			else if(helper instanceof ForLoopNode) {
				str.append(helper.toString());
						
			}
			else if(helper instanceof EchoNode) {
				str.append(helper.toString());
		  }
		}
		
		return str.toString();
	}
	

}
