package hr.fer.oprpp1.custom.scripting.parser;

import hr.fer.oprpp1.custom.collections.ArrayIndexedCollection;
import hr.fer.oprpp1.custom.collections.ObjectStack;
import hr.fer.oprpp1.custom.scripting.elems.Element;
import hr.fer.oprpp1.custom.scripting.elems.ElementConstantDouble;
import hr.fer.oprpp1.custom.scripting.elems.ElementConstantInteger;
import hr.fer.oprpp1.custom.scripting.elems.ElementFunction;
import hr.fer.oprpp1.custom.scripting.elems.ElementOperator;
import hr.fer.oprpp1.custom.scripting.elems.ElementString;
import hr.fer.oprpp1.custom.scripting.elems.ElementVariable;
import hr.fer.oprpp1.custom.scripting.lexer.SmartScriptLexer;
import hr.fer.oprpp1.custom.scripting.lexer.SmartScriptLexerException;
import hr.fer.oprpp1.custom.scripting.lexer.SmartScriptLexerState;
import hr.fer.oprpp1.custom.scripting.lexer.SmartScriptToken;
import hr.fer.oprpp1.custom.scripting.lexer.SmartScriptTokenType;
import hr.fer.oprpp1.custom.scripting.nodes.DocumentNode;
import hr.fer.oprpp1.custom.scripting.nodes.EchoNode;
import hr.fer.oprpp1.custom.scripting.nodes.ForLoopNode;
import hr.fer.oprpp1.custom.scripting.nodes.Node;
import hr.fer.oprpp1.custom.scripting.nodes.TextNode;

/** 
 * @author Tomislav Lovrencic
 *
 *This class represents a Parser that parses given text into a sctructured tree.
 * It uses {@link Node} elements to create a sytax tree and it uses {@link SmartScriptLexer} to tokenize string 
 * and parse it into {@link Element}.
 *
 */
public class SmartScriptParser {

	
	/**
	 * Root Node of generated syntax tree.
	 */
	private DocumentNode documentBody;
	
	
	/**
	 * Stack used for creating a syntax tree.
	 */
	private ObjectStack stack;
	
	
	/**
	 * Lexer used for tokenizing parts of string.
	 */
	private SmartScriptLexer lexer;
	
	
	/**
	 * This is a basic constructor.
	 * 
	 * @param text Text to be parsed.
	 */
	public SmartScriptParser(String text) {
		this.documentBody = new DocumentNode();
		this.stack = new ObjectStack();
		this.lexer = new SmartScriptLexer(text);
		stack.push(documentBody);
		
		
		try {
			parse();
		} catch(SmartScriptLexerException e) {
			throw new SmartScriptParserException(e.getMessage());
		}
			
	}
	
	
	/**
	 * This is a function used to parse a string into diffrent Nodes and then to create a syntax tree out of nodes 
	 * starting at root node documentBody.
	 */
	public void parse() {
		while (true) {
			
			lexer.setState(SmartScriptLexerState.TEXT);
			
			SmartScriptToken token = lexer.nextToken();
			
			if(token.getType() == SmartScriptTokenType.EOF) {
				break;
			}
		
			else if(token.getType() == SmartScriptTokenType.WORD) {
				
				String str = token.getValue().toString();
				TextNode node = new TextNode(str);

				Node obj = (Node) stack.peek();
				obj.addChildNode(node);
				if(token.getType() == SmartScriptTokenType.EOF) {
					break;
				}
			}
			
			else if(token.getType() == SmartScriptTokenType.BEGINTAG) {
				lexer.setState(SmartScriptLexerState.INSIDETAGS);
				
				token = lexer.nextToken();

				if(token.getType() == SmartScriptTokenType.EQUALSIGN) {
					
					token = lexer.nextToken();
					
					ArrayIndexedCollection col = new ArrayIndexedCollection();
					
					while(token.getType() != SmartScriptTokenType.ENDTAG) {
						if(token.getType() == SmartScriptTokenType.OPERATOR) {
							if(checkOperator(token)) {
								Element add = parseElement(token);
								if(add != null) {
									col.add(parseElement(token));
								}
								else {
									throw new SmartScriptParserException("String contains elements that are not allowed!");
								}
							}	
							else {
								throw new SmartScriptParserException("u cant use that operator in echo node");
							}
						}
						else {
							Element add = parseElement(token);
							if(add != null) {
								col.add(parseElement(token));
							}
							else {
								throw new SmartScriptParserException("String contains elements that are not allowed!");
							}

						}
						token = lexer.nextToken();
					}
					Element[] elements = new Element[col.size()];
					
					
					for(int i=0;i<col.size();i++) {
						elements[i] = (Element) col.get(i);
					}
				
					
					EchoNode node = new EchoNode(elements);
				
					Node obj = (Node) stack.peek();
					obj.addChildNode(node);
					if(token.getType() == SmartScriptTokenType.EOF) {
						break;
					}
		
					
				}
				else if(((String) token.getValue()).equalsIgnoreCase("END")) {
					stack.pop();
					token = lexer.nextToken();
					if(token.getType() == SmartScriptTokenType.EOF) {
						break;
					}
					
		
				}
				else if(((String) token.getValue()).equalsIgnoreCase("FOR")){

					token = lexer.nextToken();
					
					if(token.getType() != SmartScriptTokenType.VARIABLE) {
						throw new SmartScriptParserException("First element should be a variable");
					}
					
			
					ElementVariable var = (ElementVariable) parseElement(token);
					token = lexer.nextToken();
					
					Element[] el = new Element[3];
					for(int i=0;i<3;i++) {
						if(i < 2) {
							if(token.getType() != SmartScriptTokenType.CONSINT && token.getType() != SmartScriptTokenType.CONSDOUBLE &&
						token.getType() != SmartScriptTokenType.VARIABLE && token.getType() != SmartScriptTokenType.STRING) {
								throw  new SmartScriptParserException("Wrong elements in For loop");

							}
						}
						if(token.getType() == SmartScriptTokenType.STRING) {
							if(checkIfGood(token)) {
								el[i] = parseElement(token);
							}
						}
						else {
							if(i == 2 && token.getType() == SmartScriptTokenType.ENDTAG) {
								el[i] = null;
							}
							else {
								el[i] = parseElement(token);
							}
						}
						
						token = lexer.nextToken();
			
					}
					
					ForLoopNode node = new ForLoopNode(var,el[0],el[1],el[2]);
					Node obj = (Node) stack.peek();
					obj.addChildNode(node);
					stack.push(node);
					if(token.getType() == SmartScriptTokenType.EOF) {
						break;
					}
					
				}
				else {
					throw new SmartScriptParserException("InsideTags izraz krivo zapocinje");
				}
			}
			else {
				throw new SmartScriptParserException("There was something wrong while parsing");
			}
		} 
			
	}

	
	/**
	 * This function is used to check if the operator is valid.
	 * @param token
	 * @return true if operator is valid , false oterwise
	 */
	public boolean checkOperator(SmartScriptToken token) {
		if(token.getValue().toString().equals("+")) return true;
		if(token.getValue().toString().equals("-")) return true;
		if(token.getValue().toString().equals("*")) return true;
		if(token.getValue().toString().equals("/")) return true;
		return false;
	}
	
	
	/**
	 * This function checks if token with type string can be in For loop node.
	 * @param token
	 * @return true if its integer,double or variable.
	 */
	public boolean checkIfGood(SmartScriptToken token) {
		
		String text = (String) token.getValue();
		boolean same = true;
		try {
			int a = Integer.parseInt(text);
			return true;
		}catch(NumberFormatException e) {
			same= false;
		}
		try {
			double a = Double.parseDouble(text);
			return true;
		}catch(NumberFormatException e) {
			same = false;
		}
		
		char p[] = text.toCharArray();
		
			
		if(p[0] == '_' || Character.isDigit(p[0])) {
			same =  false;
		}
		else {
			same = true;
		}
		return same;
	}
	
	/**
	 * This is a function used to parse a token into an {@link Element}.
	 * @param token Token to parse
	 * @return Element.
	 */
	public Element parseElement(SmartScriptToken token) {
		Element novi = null;
		if(token.getType() == SmartScriptTokenType.FUNCTION) {
			novi = new ElementFunction(token.getValue().toString());
		}
		if(token.getType() == SmartScriptTokenType.CONSDOUBLE) {
			double value = Double.parseDouble(token.getValue().toString());
			novi = new ElementConstantDouble(value);
		}
		if(token.getType() == SmartScriptTokenType.CONSINT) {
			int value = Integer.parseInt(token.getValue().toString());
			novi = new ElementConstantInteger(value);
		}
		if(token.getType() == SmartScriptTokenType.OPERATOR) {
			novi = new ElementOperator(token.getValue().toString());
		}
		if(token.getType() == SmartScriptTokenType.WORD) {
			novi = new ElementVariable(token.getValue().toString());
		}
		if(token.getType() == SmartScriptTokenType.VARIABLE) {
			novi = new ElementVariable(token.getValue().toString());
		}
		if(token.getType() == SmartScriptTokenType.STRING) {
			novi = new ElementString(token.getValue().toString());
		}
		
		return novi;
		
	}


	/**
	 * This is a getter for documentNode
	 * @return documentNode
	 */
	public DocumentNode getDocumentNode() {
		return documentBody;
		
	}
}
