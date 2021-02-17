package hr.fer.oprpp1.hw02.prob1;

import java.util.Objects;

/**
 * @author Tomislav Lovrencic
 *This class represents a lexer. It parses strings to tokens using {@link Token}.
 * It has two states (BASIC and EXTENDED).
 *
 */
public class Lexer { 
	
	
	/**
	 * array of chars. 
	 */
	private char[] data;
	
	/**
	 * Last token generated. 
	 */
	private Token token;
	
	/**
	 *  currentIndex in array.
	 */
	private int currentIndex; 
	
	
	/**
	 * Current state of lexer.
	 */
	private LexerState state;
 
	/** This is a constructor for lexer. it initializes data , state and currentIndex.
	 * @param text String ready to be parsed using lexer.
	 */
	public Lexer(String text) {
		text = text.trim().replaceAll("[\\n\\t\\r ]", " ");
		data = text.toCharArray();
		state = LexerState.BASIC;
		currentIndex = 0;
	}
	
	/**
	 *  This function is used to eliminate spaces between two tokens.
	 */
	public void eliminateSpaces() {
		if(currentIndex < data.length) {
			while(data[currentIndex] == ' ') {
				currentIndex++;
			}
		}
		
	}
	
	/**
	 * This function is used to tokenize a number into Long value.
	 */
	public void charactherIsDigit() {
		String tokenName = "";
		while(Character.isDigit(data[currentIndex])) {
			tokenName+= data[currentIndex];
			currentIndex++;
			if(currentIndex == data.length) break;
		}
		try {
			Long number = Long.parseLong(tokenName);
			token = new Token(TokenType.NUMBER,number);
		}catch(NumberFormatException e) {
			System.out.println("Error while parsing a string to long");
			System.exit(1);
		}
		eliminateSpaces();
	}
	
	/**
	 *  This function is used to tokenize a word.
	 */
	public void charactherIsLetter() {
		String tokenName = "";
		while(Character.isLetter(data[currentIndex]) || data[currentIndex] == '\\') {
			if(data[currentIndex] == '\\') {
				if(currentIndex < data.length-1 && (Character.isDigit(data[currentIndex+1])
						|| data[currentIndex+1] == '\\') ) {
					tokenName+=data[currentIndex+1];
					currentIndex = currentIndex + 2;
				}
				else {
					throw new LexerException("U can only escape numbers!");
				}
			}
			else {
				tokenName+= data[currentIndex];
				currentIndex++;	
			}
			if(currentIndex >= data.length) break;
		}
		token = new Token(TokenType.WORD,tokenName);
		eliminateSpaces();
	}
	
	/**
	 *  This function is used to tokenize a word , while lexer in state EXTENDED.
	 */
	public void charactherIsLetterEXTENDED() {
		String tokenName = "";
		while(data[currentIndex] != ' ' && data[currentIndex] != '#') {
				tokenName+= data[currentIndex];
				currentIndex++;	
			if(currentIndex >= data.length) break;
		}
		token = new Token(TokenType.WORD,tokenName);
		eliminateSpaces();
	}
	
	
	/**
	 *  This function is used to work as long as lexer is in state BASIC. It checks for first charachter of word , then it 
	 *  parses it given the outcome and calls appropriate function.
	 */
	public void workAsStateBasic() {
		if(token != null && token.getType().equals(TokenType.EOF)){
			throw new LexerException("U cant call next token while the previous is Type EOF");
		}
		
		eliminateSpaces();
		
		if(currentIndex == data.length) {
			token = new Token(TokenType.EOF,null);
		}
		else {

			if(Character.isDigit(data[currentIndex])) {
				charactherIsDigit();
				
			}
			else if(Character.isLetter(data[currentIndex]) || data[currentIndex] == '\\' ) {
				charactherIsLetter();
			}
			else {
				char charElem = data[currentIndex];
				currentIndex++;
				token = new Token(TokenType.SYMBOL,charElem);
				if(data[currentIndex] == '#') {
					state = LexerState.EXTENDED;
				}
				
			}
				
		}
	}
	
	
	/**
	 * This function is used as long as lexer is in state EXTENDED. It checks for current characther in data and 
	 * pareses it given the outcome and calls appropriate function.
	 */
	public void workAsStateExtended() {
		if(token != null && token.getType().equals(TokenType.EOF)){
			throw new LexerException("Error");
		}
		
		eliminateSpaces();
		
		if(currentIndex == data.length) {
			token = new Token(TokenType.EOF,null);
		}
		else {
			if(data[currentIndex] != '#') {
				charactherIsLetterEXTENDED();
			}else {
				if(data[currentIndex] == '#') {
					char charElem = data[currentIndex];
					token = new Token(TokenType.SYMBOL,charElem);
					currentIndex++;
					state = LexerState.BASIC;
				}
			}
		}
	}
 
	/**
	 * This function is used to get a next Token in given string.
	 * @return next Token.
	 */
	public Token nextToken() {
		switch(state) {
			case BASIC:
				workAsStateBasic();
				break;
			case EXTENDED:
				workAsStateExtended();
		}
	
		return token;
		
	}
		
	/**
	 * This is a getter for current token.
	 * 
	 * @return current token.
	 */
	public Token getToken() {
		return token;
	}
	
	
	/**
	 * This is a setter for lexer state.
	 * @param state (BASIC,EXTENDED).
	 */
	public void setState(LexerState state) {
		Objects.requireNonNull(state);
		this.state = state;
	}
}