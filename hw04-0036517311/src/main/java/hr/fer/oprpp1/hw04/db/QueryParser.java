package hr.fer.oprpp1.hw04.db;

import java.util.ArrayList;
import java.util.List;




/** 
 * @author Tomislav Lovrencic
 *
 *This class represents a Parser that parses given text into a Conditional Expressions.
 * It uses  {@link QueryLexer} to tokenize strings.
 *
 */
public class QueryParser {
	
	/**
	 *  List of queries.
	 */
	private List<ConditionalExpression> lista;
		
	/**
	 * Lexer used for tokenization.
	 */
	private QueryLexer lexer;
	
	
	/**
	 * This is a basic constructor.
	 * @param ulaz
	 */
	public QueryParser(String ulaz) {
		lexer = new QueryLexer(ulaz);
		lista = new ArrayList<>();
		
		try {
			parse(ulaz);

		} catch(QueryLexerException e) {
			throw new QueryParserException(e.toString());
		}
		
	}

	/**
	 * This method checks if the query is direct.
	 * @return true if the query is direct, false otherwise.
	 */
	public boolean isDirectQuery() {
		if(lista.size() != 1) return false;
		if(!lista.get(0).getComparisonOperator().equals(ComparisonOperators.EQUALS)) return false;
		if(!lista.get(0).getFieldValueGetter().equals(FieldValueGetters.JMBAG)) return false;
		return true;
		
	}
	
	/** This method returns jmbag of direct query.
	 * @return Jmbag if the query is direct , throws {@link IllegalArgumentException} othwerwise
	 * @throws IllegalArgumentException.
	 */
	public String getQueriedJMBAG() {
		if(!isDirectQuery()) {
			throw new IllegalStateException();
		}
		else {
			return lista.get(0).getStr();
		}
	}
	
	/**
	 * This method returns the list of queries.
	 * @return
	 */
	public List<ConditionalExpression> getQuery(){
		return lista;
	}
	
	/**
	 * This method is used to parse tokenized text into conditional Expressions.
	 * @param text
	 */
	public void parse(String text) {
		
		boolean andIsActivated = false;
		
		while(true) {
			Token token = lexer.nextToken();
			
			if(token.getType() == TokenType.EOF) {
				break;
			}
			
			if(token.getType() == TokenType.QUERY || andIsActivated) {
				
				if(!andIsActivated) token = lexer.nextToken();

				andIsActivated = false;
		
				if(token.getType() == TokenType.ATTRIBUTE) {
					IFieldValueGetter fieldGetter = null;
					switch(token.getValue().toString()) {
						case "jmbag":
							fieldGetter = FieldValueGetters.JMBAG;
							break;
						case "firstName":
							fieldGetter = FieldValueGetters.FIRST_NAME;
							break;
						case "lastName":
							fieldGetter = FieldValueGetters.LAST_NAME;
							break;
						default:
							throw new QueryParserException("Wrong attribute!");
					}
					
					token = lexer.nextToken();
					
					if(token.getType() == TokenType.OPERATOR) {
						IComparisonOperator compOperator = null;
						switch(token.getValue().toString()) {
							case "=":
								compOperator = ComparisonOperators.EQUALS;
								break;
							case "<=":
								compOperator = ComparisonOperators.LESS_OR_EQUALS;
								break;
							case ">=":
								compOperator = ComparisonOperators.GREATER_OR_EQUALS;
								break;
							case "!=":
								compOperator = ComparisonOperators.NOT_EQUALS;
								break;
							case "<":
								compOperator = ComparisonOperators.LESS;
								break;
							case ">":
								compOperator = ComparisonOperators.GREATER;
								break;
							case "LIKE":
								compOperator = ComparisonOperators.LIKE;
								break;
						}
						
						token = lexer.nextToken();
						
						if(token.getType() == TokenType.STRING) {
							
							String str = token.getValue().toString();
							ConditionalExpression cond = new ConditionalExpression(fieldGetter, str, compOperator);
							lista.add(cond);
							token=lexer.nextToken();
							if(token.getType() != TokenType.EOF) {
								if(token.getType() == TokenType.AND) {
									andIsActivated = true;
								}
								else {
									throw new QueryParserException("Queries needs to be seperated by operator and");
								}
							}
							else {
								break;
							}
							
						}
						else {
							throw new QueryParserException("forth element needs to be string literal");
						}

					}
					else {
						throw new QueryParserException("Third element needs to be operator");
					}
								
				}
				else {
					throw new QueryParserException("Second element needs to be attribute");
				}
			}
			else {
				throw new QueryParserException("First element needs to be query");
			}
		}
		
	}
	
	
}
