package hr.fer.oprpp1.custom.scripting.lexer;

/**
 * @author Tomislav Lovrencic
 *
 * This enum represents a SmartScriptToken type.
 */
public enum SmartScriptTokenType {
	
	/**
	 * This is a type for last token in string. 
	 */
	EOF,
	/**
	 * This is a type for word. 
	 */
	WORD,
	/**
	 * This is a type for variable. 
	 */
	VARIABLE,
	/**
	 * This is a type for integer. 
	 */
	CONSINT,
	/**
	 * This is a type for double.
	 */
	CONSDOUBLE,
	/**
	 * This is a type for function name.
	 */
	FUNCTION,
	/**
	 * This is a type for operator.
	 */
	OPERATOR,
	/**
	 * This is a type for endtag ('}').
	 */
	ENDTAG,
	/**
	 * This is a type for begintag ('{').
	 */
	BEGINTAG,
	/**
	 * This is a type for '$'.
	 */
	DOLLARSIGN,
	/**
	 * This is a type for equal sign ('=').
	 */
	EQUALSIGN,
	/**
	 * This is a type for string.
	 */
	STRING
}
