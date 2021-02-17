package hr.fer.oprpp1.hw04.db;

/**
 * @author Tomislav Lovrencic
 *
 * This enum represents a SmartScriptToken type.
 */
public enum TokenType {
	
	/**
	 * This is a type for last token in string. 
	 */
	EOF,
	/**
	 * This is a type for attribute. 
	 */
	ATTRIBUTE,
	/**
	 * This is a type for operator.
	 */
	OPERATOR,
	/**
	 * This is a type for string.
	 */
	STRING,
	/**
	 * This is a type for AND.
	 */
	AND,
	/**
	 * This is a type for query.
	 */
	QUERY
}
