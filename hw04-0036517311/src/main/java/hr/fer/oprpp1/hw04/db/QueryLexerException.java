package hr.fer.oprpp1.hw04.db;



/**
 * @author Tomislav Lovrencic
 *
 *This class represents a SmartScriptLexer exception which is thrown when there is 
 * an error in {@link SmartScriptLexer}.
 *
 */
public class QueryLexerException extends RuntimeException{

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
	public QueryLexerException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}

}
