package hr.fer.oprpp1.hw02.prob1;

/**
 * @author Tomislav Lovrencic
 *
 *This class represents a Token in {@link Lexer}. Its used to distinguish diffrent type of words in lexer. 
 *
 */
public class Token {
	
	/**
	 *  Type of token.
	 */
	private TokenType type;
	
	/**
	 *  Value of token.
	 */
	private Object obj;

	/**
	 * This is a constructor for token. It takes token type and value.
	 * @param type
	 * @param value
	 */
	public Token(TokenType type, Object value) {
		this.type = type;
		this.obj = value;
	}
	/**
	 * This is a getter for token value.
	 * @return value
	 */
	public Object getValue() {
		return obj;
	}
	
	
	/**
	 * This is a getter for token type.
	 * @return token type.
	 */
	public TokenType getType() {
		return type;
	}
	
	
}
