package hr.fer.oprpp1.custom.scripting.lexer;



/**
 * @author Tomislav Lovrencic
 *
 *This class represents a Token in {@link SmartScriptLexer}. Its used to distinguish diffrent type of words in lexer. 
 *
 */
public class SmartScriptToken {
	
	/**
	 *  Type of token.
	 */
	private SmartScriptTokenType type;
	
	/**
	 *  Value of token.
	 */
	private Object obj;

	/**
	 * This is a constructor for token. It takes token type and value.
	 * @param type
	 * @param value
	 */
	public SmartScriptToken(SmartScriptTokenType type, Object value) {
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
	public SmartScriptTokenType getType() {
		return type;
	}
	
	
}