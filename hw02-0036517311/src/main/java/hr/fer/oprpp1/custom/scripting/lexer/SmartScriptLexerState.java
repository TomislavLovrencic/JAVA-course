package hr.fer.oprpp1.custom.scripting.lexer;

/**
 * @author Tomislav Lovrencic
 * 
 * Enum for SmartScriptLexer state.
 */
public enum SmartScriptLexerState {
	/**
	 * This is a SmartScriptLexer state when outside tags. 
	 */
	TEXT,
	/**
	 * This is a SmartScriptLexer state when inside tags.
	 */
	INSIDETAGS
}
