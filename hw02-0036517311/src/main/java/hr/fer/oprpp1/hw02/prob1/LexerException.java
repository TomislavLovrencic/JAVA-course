package hr.fer.oprpp1.hw02.prob1;

/**
 * @author Tomislav Lovrencic
 *
 *This class represents a lexer exception which is thrown when there is 
 * an error in {@link Lexer}.
 *
 */
public class LexerException extends RuntimeException{

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
	public LexerException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}

}
