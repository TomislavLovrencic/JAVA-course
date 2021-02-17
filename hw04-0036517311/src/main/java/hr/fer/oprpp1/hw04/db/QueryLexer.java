package hr.fer.oprpp1.hw04.db;

import java.util.Objects;




/**
 * @author Tomislav Lovrencic
 *
 * This is a class that represents a  Querylexer that is used to tokenize string.
 */
public class QueryLexer {

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
	 * This is a basic constructor.
	 * @param text
	 */
	public QueryLexer(String text) {
		data = text.toCharArray();
		currentIndex = 0;
	}
	
	/**
	 *  This function is used to eliminate spaces between two tokens.
	 */
	public void eliminateSpaces() {
		if(currentIndex < data.length) {
			while(data[currentIndex] == ' ' || data[currentIndex] == '\t') {
				currentIndex++;
				if(currentIndex == data.length) break;

			}
		}
		
	}
	
	/**
	 * This function is used to create a variable token.
	 */
	public void charactherIsVariable() {
		String tokenName = "";
		
		while(Character.isLetter(data[currentIndex])) {
				tokenName+= data[currentIndex];
				currentIndex++;	
			if(currentIndex >= data.length) break;
		}
		if(tokenName.toUpperCase().equals("AND")){
			tokenName = tokenName.toUpperCase();
		}
		
		switch(tokenName) {
			case "jmbag":
				token = new Token(TokenType.ATTRIBUTE,tokenName);
				break;
			case "firstName":
				token = new Token(TokenType.ATTRIBUTE,tokenName);
				break;
			case "lastName":
				token = new Token(TokenType.ATTRIBUTE,tokenName);
				break;
			case "AND":
				token = new Token(TokenType.AND,tokenName);
				break;
			case "LIKE":
				token = new Token(TokenType.OPERATOR,tokenName);
				break;
			default:
				throw new QueryLexerException("This word is not allowed in queries");
		}
		
		eliminateSpaces();
	}
	
	

	/** 
	 * This function is used to create an operator token.
	 */
	public void charachterIsOperator() {
		String operator ="";
		if(data[currentIndex] == '<' || data[currentIndex] == '>') {
			operator+=data[currentIndex];
			if(currentIndex < data.length-1 && data[currentIndex] == '=') {
				currentIndex++;
				operator+=data[currentIndex++];
			}
		}
		else {
			if(data[currentIndex] == '!') {
				if(currentIndex < data.length-1 && data[currentIndex] == '=') {
					currentIndex++;
					operator+=data[currentIndex++];
				
				}
				else {
					throw new QueryLexerException("This charachter cant stand alone!");
				}
			}
		}
		currentIndex++;
		
		switch(operator) {
			case "<=":
				token = new Token(TokenType.OPERATOR,operator);
				break;
			case ">=":
				token = new Token(TokenType.OPERATOR,operator);
				break;
			case "!=":
				token = new Token(TokenType.OPERATOR,operator);
				break;
			case "<":
				token = new Token(TokenType.OPERATOR,operator);
				break;
			case ">":
				token = new Token(TokenType.OPERATOR,operator);
				break;
		}
		eliminateSpaces();
		}
	
	
	/**
	 * This function is used for tokenizing a string. Checks for first char in word 
	 * then after the outcome calls the right function to transform it into a token. 
	 */
	public void workAsINSIDETAGS() {
	
		
		eliminateSpaces();
	
		if(currentIndex == data.length) {
			token = new Token(TokenType.EOF,null);
		}
		
		else {
			if(data[currentIndex] == 'q') {
				String tokenName = "";
				while(data[currentIndex] != ' ') {
					tokenName+=data[currentIndex];
					currentIndex++;
					if(currentIndex == data.length) break;
				}
				eliminateSpaces();
				if(!tokenName.equals("query")) throw new QueryLexerException("First word should be query!");
				token = new Token(TokenType.QUERY,tokenName);
			}
	
			else if(data[currentIndex] == '=') {
				char charElem1 = data[currentIndex];
				token = new Token(TokenType.OPERATOR,charElem1);
				currentIndex++;
				eliminateSpaces();
		
			}
			else if(Character.isLetter(data[currentIndex])){
				charactherIsVariable();

			}
			else if(data[currentIndex] == '"') {
				String tokenName ="";
				currentIndex++;
				eliminateSpaces();
				while(data[currentIndex] != '"') {
					tokenName+=data[currentIndex];
					currentIndex++;
				}
				
				currentIndex++;
				token = new Token(TokenType.STRING,tokenName);
				eliminateSpaces();
				
			}
			else {
				charachterIsOperator();
			}
			
		}
	}
	

	
	/**
	 * This function is used to get a next Token in given string.
	 * @return next Token.
	 */
	public Token nextToken() {
		workAsINSIDETAGS();
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
	


}
