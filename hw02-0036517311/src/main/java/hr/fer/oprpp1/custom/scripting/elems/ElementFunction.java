package hr.fer.oprpp1.custom.scripting.elems;


/**
 * @author Tomislav Lovrencic
 * 
 * 
 * This class represents an element whose value is function.
 *
 */
public class ElementFunction extends Element {
	
	
	/**
	 *  This is the name of the element.
	 */
	private String name;
	
	
	/**
	 * This is a constructor.
	 * @param value
	 */
	public ElementFunction(String name) {
		this.name = name;
	}

	@Override
	public String asText() {
		return name;
	}
	
	/**
	 *  This is a getter for name.
	 *  
	 * @return value of element.
	 */
	public String getValue() {
		return this.name;
	}
	

}
