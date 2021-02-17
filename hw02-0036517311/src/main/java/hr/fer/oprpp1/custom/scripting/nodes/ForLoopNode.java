package hr.fer.oprpp1.custom.scripting.nodes;

import hr.fer.oprpp1.custom.collections.ArrayIndexedCollection;
import hr.fer.oprpp1.custom.scripting.elems.Element;
import hr.fer.oprpp1.custom.scripting.elems.ElementString;
import hr.fer.oprpp1.custom.scripting.elems.ElementVariable;

/**
 * @author Tomislav Lovrencic
 *
 * This is a class that represents ForLoopNode. This node can have his own child nodes.
 * 
 */
public class ForLoopNode extends Node{

	
	/**
	 *  First element of forLoopNode is variable. 
	 */
	private ElementVariable variable;
	
	/**
	 *  start Element of forLoopNode, needs to be number,variable or string
	 */
	private Element startExpression;
	/**
	 *  end Element of forLoopNode, needs to be number,variable or string
	 */
	private Element endExpression;
	/**
	 *  step Element of forLoopNode, needs to be number,variable or string
	 */
	private Element stepExpression;
	/**
	 *  Collection of elements.
	 */
	private ArrayIndexedCollection col;
	
	
	
	/**
	 *  This is a constructor.
	 * @param var First Element , needs to be variable.
	 * @param st start Element.
	 * @param end end Element.
	 * @param step Step Element , can be null.
	 */
	public ForLoopNode(ElementVariable var,Element st,Element end,Element step) {
		this.variable = var;
		this.startExpression = st;
		this.endExpression = end;
		this.stepExpression = step;
	}
	
	/**
	 * This is a getter for variable.
	 * @return variable
	 */
	public ElementVariable getVariable() {
		return this.variable;
		
	}
	/**
	 * This is a getter for startExpression Element.
	 * @return start Element
	 */
	public Element getStartExpression() {
		return this.startExpression;
		
	}
	/**
	 * This is a getter for EndExpression Element.
	 * @return end Element
	 */
	public Element getEndExpression() {
		return this.endExpression;
	
	}
	/**
	 * This is a getter for step Element.
	 * @return Step Element.
	 */
	public Element getStepExpression() {
		return this.stepExpression;
	
	}
	@Override
	public void addChildNode(Node child) {
		if(col == null) {
			col = new ArrayIndexedCollection();

		}
		col.add(child);
		
	}
	@Override
	public Node getChild(int index) {
		if(col.get(index) != null) {
			return (Node) col.get(index);
		}
		throw new IndexOutOfBoundsException();
	}
	@Override
	public int numberOfChildren() {
		return col.size();
	}
	
	@Override
	public boolean equals(Object obj) {
		ForLoopNode node = (ForLoopNode) obj;
		if(!(this.variable.asText().equals(node.variable.asText())))  return false;
		if(!(this.startExpression.asText().equals(node.startExpression.asText()))) return false;
		if(!(this.endExpression.asText().equals(node.endExpression.asText()))) return false;
		if(this.stepExpression != null && node.stepExpression != null) {
			if(!(this.stepExpression.asText().equals(node.stepExpression.asText()))) return false;
		}
		for(int i = 0;i<this.numberOfChildren();i++) {
			if(!this.getChild(i).equals(node.getChild(i))){
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("{$FOR ");
		
		ElementVariable var = this.variable;
		Element start = this.startExpression;
		Element end = this.endExpression;
		Element step = this.stepExpression;
		str.append(var.asText()+" ");
		
		if(start.getClass() == ElementString.class) {
			String s = "";
			s+="\"";
			s+=start.asText();
			s+="\"";
			str.append(s+" ");
		}
		else {
			str.append(start.asText()+" ");
		}
		if(end.getClass() == ElementString.class) {
			String s = "";
			s+="\"";
			s+=end.asText();
			s+="\"";
			str.append(s+" ");
		}
		else {
			str.append(end.asText()+" ");
		}
		if(step != null && step.getClass() == ElementString.class) {
			String s = "";
			s+="\"";
			s+=start.asText();
			s+="\"";
			str.append(s+" ");
		}
		else {
			if(step != null) {
				str.append(step.asText()+" ");
			}
		}
	
		str.append(" $}");

		for(int i =0;i<this.numberOfChildren();i++) {
			Node helper = this.getChild(i);
			str.append(helper.toString());
			
		}
		str.append("{$END$}");
		
		return str.toString();
	}
}
