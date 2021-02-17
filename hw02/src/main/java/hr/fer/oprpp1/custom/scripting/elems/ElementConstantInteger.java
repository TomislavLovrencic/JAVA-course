package hr.fer.oprpp1.custom.scripting.elems;

/**
 * @author Tomislav Lovrencic
 * 
 * 
 * This class represents an element whose value is integer.
 *
 */
public class ElementConstantInteger extends  Element {
	
	
	/**
	 *  This is the value of element.
	 */
	private int value;
	
	
	/**
	 * This is a constructor.
	 * @param value
	 */
	public ElementConstantInteger(int value) {
		this.value = value;
	}
	
	@Override
	public String asText() {
		return String.valueOf(value);
	}
	
	/**
	 *  This is a getter for value.
	 *  
	 * @return value of element.
	 */
	public int getValue() {
		return this.value;
	}

}
