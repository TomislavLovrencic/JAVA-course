package hr.fer.oprpp1.custom.collections;


/**
 * @author Tomislav Lovrencic
 *
 * This class is a custom exception. This exception is thrown when we try to acces elements from an empty stack.
 */
public class EmptyStackException extends RuntimeException {


	private static final long serialVersionUID = 1L;
	
	private final String message;
	
	/**
	 * This is a basic constructor that initializes and exception with the given message.
	 * @param message String that will be shown when exception is thrown.
	 */
	public EmptyStackException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}
	
	

}
