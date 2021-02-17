package hr.fer.oprpp1.custom.scripting.nodes;

import hr.fer.oprpp1.custom.scripting.elems.Element;
import hr.fer.oprpp1.custom.scripting.elems.ElementFunction;
import hr.fer.oprpp1.custom.scripting.elems.ElementString;


/**
 * @author Tomislav Lovrencic
 * 
 * This class represents a EchoNode with collection of elements.
 * This class doesnt have child nodes.
 *
 */
public class EchoNode extends Node{


	/**
	 *  Collection of elements.
	 */
	private Element[] elements;
	
	/**
	 * This is a basic constructor witch takes collection of elements
	 * @param elem array of elements
	 */
	public EchoNode(Element[] elem) {
		this.elements = elem;
	}
	
	/**
	 * This is a getter for elements in echoNode.
	 * @return collection of elements
	 */
	public Element[] getElements() {
		return elements;
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
		EchoNode echo = (EchoNode) obj;
		for(int i=0;i<elements.length;i++) {
			if(!(elements[i].asText().equals(echo.elements[i].asText()))) {

				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("{$= ");
		
        Element[] echoNodeElements = this.getElements();

        for (Element echoNodeElement : echoNodeElements) {
        	if(echoNodeElement.getClass() == ElementString.class) {
    			String s = "";
    			s+="\"";
    			s+=echoNodeElement.asText();
    			s+="\"";
    			str.append(s+" ");
    		}
        	else if(echoNodeElement.getClass() == ElementFunction.class) {
        		String s = "";
    			s+="@";
    			s+=echoNodeElement.asText();
    			str.append(s+" ");
        	}
    		else {
    			str.append(echoNodeElement.asText()+" ");
    		}

        }
        str.append("$}");
    	
		return str.toString();
	}
}
