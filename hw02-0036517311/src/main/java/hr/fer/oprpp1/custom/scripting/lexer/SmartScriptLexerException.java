package hr.fer.oprpp1.custom.scripting.lexer;



/**
 * @author Tomislav Lovrencic
 *
 *This class represents a SmartScriptLexer exception which is thrown when there is 
 * an error in {@link SmartScriptLexer}.
 *
 */
public class SmartScriptLexerException extends RuntimeException{

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
	public SmartScriptLexerException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}

}
