package hr.fer.oprpp1.hw02.prob1;

/**
 * @author Tomislav Lovrencic
 *
 * This enum represents a Lexer state.
 *
 */
public enum LexerState {
	
	/**
	 * Basic state of lexer , when outside char '#'
	 */
	BASIC,
	
	/**
	 *  Extended state of lexer, when inside the first appearance of '#'
	 */
	EXTENDED
}
