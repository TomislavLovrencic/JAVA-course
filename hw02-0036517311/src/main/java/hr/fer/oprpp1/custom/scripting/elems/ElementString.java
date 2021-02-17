package hr.fer.oprpp1.custom.scripting.elems;


/**
 * @author Tomislav Lovrencic
 * 
 * 
 * This class represents an element whose value is string.
 *
 */
public class ElementString extends Element {
	
	/**
	 *  This is the value of the element.
	 */
	private String value;
	
	
	/**
	 * This is a constructor.
	 * @param value
	 */
	public ElementString(String value) {
		this.value = value;
	}

	@Override
	public String asText() {
		return value;
	}
	
	/**
	 *  This is a getter for name.
	 *  
	 * @return value of element.
	 */
	public String getValue() {
		return this.value;
	}
	

}
