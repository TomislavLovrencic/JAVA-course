package hr.fer.oprpp1.custom.scripting.parser;



/**
 * @author Tomislav Lovrencic
 *
 *This class represents a SmartScriptParser exception which is thrown when there is 
 * an error in {@link SmartScriptParser}.
 *
 */
public class SmartScriptParserException extends RuntimeException {

	/**
	 * final serialVersionUid.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 *  message of exception.
	 */
	private String message;
	
	
	/**
	 * This is a basic constructor for exception.
	 * @param message
	 */
	public SmartScriptParserException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}

}
