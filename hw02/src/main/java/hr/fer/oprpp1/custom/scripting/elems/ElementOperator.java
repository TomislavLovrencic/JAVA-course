package hr.fer.oprpp1.custom.scripting.elems;


/**
 * @author Tomislav Lovrencic
 * 
 * 
 * This class represents an element whose value is operator.
 *
 */
public class ElementOperator extends Element {
	
	/**
	 *  This is the symbol of the element.
	 */
	private String symbol;
	
	
	/**
	 * This is a constructor.
	 * @param value
	 */
	public ElementOperator(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String asText() {
		return symbol;
	}
	
	
	/**
	 *  This is a getter for name.
	 *  
	 * @return value of element.
	 */
	public String getValue() {
		return this.symbol;
	}
	

}
