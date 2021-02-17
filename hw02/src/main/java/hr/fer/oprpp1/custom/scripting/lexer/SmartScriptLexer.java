package hr.fer.oprpp1.custom.scripting.lexer;

import java.util.Objects;





/**
 * @author Tomislav Lovrencic
 *
 * This is a class that represents a SmartScript lexer that is used to tokenize string into
 *  {@link SmartScriptToken}. This lexer works in two states , TEXT and INSIDETAGS.
 */
public class SmartScriptLexer {
	/**
	 * array of chars. 
	 */
	private char[] data;     
	/**
	 * Last token generated. 
	 */
	private SmartScriptToken token;   
	/**
	 *  currentIndex in array.
	 */
	private int currentIndex; 
	/**
	 * Current state of lexer.
	 */
	private SmartScriptLexerState state;
	
	
 
	/**
	 * This is a basic constructor.
	 * @param text
	 */
	public SmartScriptLexer(String text) {
		data = text.toCharArray();
		state = SmartScriptLexerState.TEXT;
		currentIndex = 0;
	}
	
	/**
	 *  This function is used to eliminate spaces between two tokens.
	 */
	public void eliminateSpaces() {
		if(currentIndex < data.length) {
			while(data[currentIndex] == ' '  || data[currentIndex] == '\r') {
				currentIndex++;
			}
		}
		
	}
		
	/**
	 * This function is used to create a Integer token or double token depending on if there is a '.' in the number.
	 * @param a '+' if the number is positive , '-' otherwise
	 * @return True if the tokenization was successfull , false otherwise.
	 */
	public void charactherIsDigit(char a ) {
		String tokenName = "";
		boolean doub = false;
		while(Character.isDigit(data[currentIndex]) || data[currentIndex] == '.') {
			if(data[currentIndex] == '.') {
				doub = true;
			}
			tokenName+= data[currentIndex];
			currentIndex++;
			if(currentIndex == data.length) break;
		}
		try {
			if(doub == false) {
				Integer number = Integer.parseInt(tokenName);
				if(a == '-') {
					token = new SmartScriptToken(SmartScriptTokenType.CONSINT,-number);

				}
				else {
					token = new SmartScriptToken(SmartScriptTokenType.CONSINT,number);

				}
			}
			else {
				double number = Double.parseDouble(tokenName);
				if(a == '-') {
					token = new SmartScriptToken(SmartScriptTokenType.CONSDOUBLE,-number);

				}
				else {
					token = new SmartScriptToken(SmartScriptTokenType.CONSDOUBLE,number);

				}
			}
		}catch(NumberFormatException e) {
			System.out.println("Error while parsing a string to long");
			System.exit(1);
		}
		eliminateSpaces();
		
	}
	
	/**
	 * This function is used to create a variable token.
	 * @return True if the tokenization was successfull , false otherwise.
	 */
	public void charactherIsVariable() {
		String tokenName = "";
		
		while(Character.isLetter(data[currentIndex]) || Character.isDigit(data[currentIndex]) || data[currentIndex] == '_') {
				tokenName+= data[currentIndex];
				currentIndex++;	
			if(currentIndex >= data.length) break;
		}
		token = new SmartScriptToken(SmartScriptTokenType.VARIABLE,tokenName);
		eliminateSpaces();
	
	}
	
	
	/**
	 * This function is used to create a function token;
	 * @return True if the tokenization was successfull , false otherwise
	 */
	public void charachterIsFunction() {
		String tokenName = "";
		while(Character.isLetter(data[currentIndex]) || Character.isDigit(data[currentIndex]) || data[currentIndex] == '_') {
				tokenName+= data[currentIndex];
				currentIndex++;	
			if(currentIndex >= data.length) break;
		}
		token = new SmartScriptToken(SmartScriptTokenType.FUNCTION,tokenName);
		eliminateSpaces();
		
	}
	
	/** 
	 * This function is used to create an operator token.
	 * @return True if the tokenization was successfull , false otherwise
	 */
	public void charachterIsOperator() {
		char charElem = data[currentIndex];
		currentIndex++;
		token = new SmartScriptToken(SmartScriptTokenType.OPERATOR,charElem);
		eliminateSpaces();
		}
	
	
	/**
	 * This is a function that checks and parses a string inside a tag.
	 */
	public void characterIsString() {
		String tokenName = "";
		while(data[currentIndex] != '"') {
			if(data[currentIndex] == '\\') {
				if(currentIndex < data.length -1) {
					currentIndex++;
					if(data[currentIndex] == '\\') {
						tokenName+=data[currentIndex];
						currentIndex++;
					}
					else if(data[currentIndex] == '"') {
						tokenName+=data[currentIndex-1];
						tokenName+=data[currentIndex];
						currentIndex++;
					}
					else if(data[currentIndex] == 'r' || data[currentIndex] == 't' || data[currentIndex]=='n') {
						if(data[currentIndex] =='r') {
							tokenName+="\r";
						}
						else if(data[currentIndex] == 't') {
							tokenName+="\t";
						}
						else {
							tokenName+="\n";
						}
						currentIndex++;
						
					}
					else {
						throw new SmartScriptLexerException("char '\' is not allowed in that situation");
					}
				}
				else {
					throw new SmartScriptLexerException("char '\' cant stand alone");
				}
			}
			else {
				tokenName+= data[currentIndex];
				currentIndex++;
			}
			if(currentIndex >= data.length) {
				throw new SmartScriptLexerException("index out of bounds");
			}
		}
		currentIndex++;
		token = new SmartScriptToken(SmartScriptTokenType.STRING,tokenName);
		eliminateSpaces();
	}
	
	public void charachterIsNewLine() {
		char charElem = data[currentIndex];
		currentIndex++;
		token = new SmartScriptToken(SmartScriptTokenType.WORD,charElem);
		eliminateSpaces();
	}
	

	
	/**
	 * This function is used for tokenizing a string as long as SmartScriptLexer is in state INSIDETAGS.Checks for first char in word 
	 * then after the outcome calls the right function to transform it into a token. 
	 */
	public void workAsINSIDETAGS() {
		if(token != null && token.getType().equals(SmartScriptTokenType.EOF)){
			throw new SmartScriptLexerException("You cant call next token if the token is type EOF");
		}
		
		eliminateSpaces();
		if(currentIndex == data.length) {
			token = new SmartScriptToken(SmartScriptTokenType.EOF,null);
		}
		
		else {
			
			if(data[currentIndex] == '$' && currentIndex < data.length -1 && data[currentIndex+1] == '}') {
				char charElem1 = data[currentIndex+1];
				token = new SmartScriptToken(SmartScriptTokenType.ENDTAG,charElem1);
				currentIndex = currentIndex+2;	
			}
			else {
				if(data[currentIndex] == '=') {
					char charElem1 = data[currentIndex];
					token = new SmartScriptToken(SmartScriptTokenType.EQUALSIGN,charElem1);
					currentIndex++;
					eliminateSpaces();
				}
				else if(data[currentIndex] == '{' && currentIndex < data.length -1 && data[currentIndex+1] == '$') {
					char charElem1 = data[currentIndex];
					token = new SmartScriptToken(SmartScriptTokenType.BEGINTAG,charElem1);
					currentIndex = currentIndex+2;
				}
				else if(Character.isLetter(data[currentIndex])){
					charactherIsVariable();
				}
				else if(Character.isDigit(data[currentIndex])) {
					charactherIsDigit('+');
				}
				else if(data[currentIndex] == '"') {
					currentIndex++;
					characterIsString();
					eliminateSpaces();
				}
				else if(data[currentIndex] == '@'){
					currentIndex++;
					charachterIsFunction();
				}
				else if(data[currentIndex] == '-') {
					if(currentIndex < data.length -1) {
						currentIndex++;
						if(Character.isDigit(data[currentIndex] )) {
							charactherIsDigit('-');
						}
						else {
							currentIndex--;
							charachterIsOperator();
						}
					}
					else {
						charachterIsOperator();
					}
				}
				else if(data[currentIndex] == '\n' || data[currentIndex] == '\r') {
					charachterIsNewLine();
				}
				else {
					charachterIsOperator();
				}
			}
		}
	}
	
	/**
	 *  This function is used to work as long as SmartScriptLexer is in state TEXT. It connects chars from string as long as its in 
	 *  state TEXT. After it has been changed to INSIDETAGS this function will stop. 
	 */
	public void workAsTEXT() {
		if(token != null && token.getType().equals(SmartScriptTokenType.EOF)){
			throw new SmartScriptLexerException("Error");
		}
		
		eliminateSpaces();
		
		if(currentIndex == data.length) {
			token = new SmartScriptToken(SmartScriptTokenType.EOF,null);
		}
		else {
			String tokenName= "";
			while(true) {
				
				if(data[currentIndex] == '\\') {
					
					if(currentIndex < data.length-2) {
						if(data[currentIndex+1] == '\\') {
							tokenName+=data[currentIndex];
							tokenName+=data[currentIndex+1];
							currentIndex = currentIndex+2;
						}
						else if(data[currentIndex+1] == '{') {
							tokenName+=data[currentIndex];
							tokenName+=data[currentIndex+1];
							currentIndex = currentIndex+2;
						}
						else {
							throw new SmartScriptLexerException("error");
						}
					}
					else {
						throw new SmartScriptLexerException("error");
					}
				}
				else {
					if(data[currentIndex] == '{') {
						if(currentIndex < data.length-1) {
							if(data[currentIndex+1] == '$') {
								if(tokenName.length() == 0) {
									token = new SmartScriptToken(SmartScriptTokenType.BEGINTAG,'{');
									currentIndex+=2;
								}
								else {
									token = new SmartScriptToken(SmartScriptTokenType.WORD,tokenName);

								}
								eliminateSpaces();							
								break;
							}
							else {
								tokenName+=data[currentIndex];
								currentIndex++;
							}	
						}
						else {
							tokenName+=data[currentIndex];
							currentIndex++;
						}
					}else {
						tokenName+=data[currentIndex];
						currentIndex++;
						
					}
				}
				if(currentIndex == data.length) {
					token = new SmartScriptToken(SmartScriptTokenType.WORD,tokenName);
					eliminateSpaces();
					break;
				}
				
				
			}
		}
		
	}
	
	/**
	 * This function is used to get a next Token in given string.
	 * @return next Token.
	 */
	public SmartScriptToken nextToken() {
		switch(state) {
			case TEXT:
				workAsTEXT();
				break;
			case INSIDETAGS:
				workAsINSIDETAGS();
				break;
		}
	
		return token;
		
	}
		
	
	/**
	 * This is a getter for current token.
	 * 
	 * @return current token.
	 */
	public SmartScriptToken getToken() {
		return token;
	}
	
	/**
	 * This is a setter for SmartScriptLexer state.
	 * @param state (BASIC,EXTENDED).
	 */
	public void setState(SmartScriptLexerState state) {
		Objects.requireNonNull(state);
		this.state = state;
	}
	
	/**
	 * This is a getter for SmartScriptLexer state.
	 * @return SmartScriptLexer state.
	 */
	public SmartScriptLexerState getState() {
		return this.state;
	}
	

}