package hr.fer.oprpp1.hw02.prob1;

/**
 * @author Tomislav Lovrencic
 *
 * Enum for token type.
 */
public enum TokenType {
	
	/**
	 * Token type for last token in string. 
	 */
	EOF,
	
	/**
	 * Token type for words. 
	 */
	WORD,
	
	/**
	 * Token type for numbers.
	 */
	NUMBER,
	
	/**
	 * Token type for symbols.
	 */
	SYMBOL
}
