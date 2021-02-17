package hr.fer.oprpp1.custom.scripting.elems;

/**
 * @author Tomislav Lovrencic
 * 
 * 
 * This class represents an element whose value is double
 *
 */
public class ElementConstantDouble extends Element {
	
	/**
	 *  This is the value of element.
	 */
	private double value;
	
	/**
	 * This is a constructor.
	 * @param value
	 */
	public ElementConstantDouble(double value) {
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
	public double getValue() {
		return this.value;
	}
	
	
	

}
