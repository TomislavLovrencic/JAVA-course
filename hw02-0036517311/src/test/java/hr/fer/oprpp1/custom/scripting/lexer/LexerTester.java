package hr.fer.oprpp1.custom.scripting.lexer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;



public class LexerTester {

	@Test
	public void testNotNull() {
		SmartScriptLexer lexer = new SmartScriptLexer("");
		assertNotNull(lexer.nextToken(), "Token was expected but null was returned.");
	}

	
	@Test
	public void testNullInput() {
		// must throw!
		assertThrows(NullPointerException.class, () -> new SmartScriptLexer(null));
	}

	
	@Test
	public void testEmpty() {
		SmartScriptLexer lexer = new SmartScriptLexer("");
		
		assertEquals(SmartScriptTokenType.EOF, lexer.nextToken().getType(), "Empty input must generate only EOF token.");
	}

	
	@Test
	public void testGetReturnsLastNext() {
		// Calling getToken once or several times after calling nextToken must return each time what nextToken returned...
		SmartScriptLexer lexer = new SmartScriptLexer("");
		
		SmartScriptToken token = lexer.nextToken();
		assertEquals(token, lexer.getToken(), "getToken returned different token than nextToken.");
		assertEquals(token, lexer.getToken(), "getToken returned different token than nextToken.");
	}

	
	@Test
	public void testRadAfterEOF() {
		SmartScriptLexer lexer = new SmartScriptLexer("");

		// will obtain EOF
		lexer.nextToken();
		// will throw!
		assertThrows(SmartScriptLexerException.class, () -> lexer.nextToken());
	}
	


	@Test
	public void testTwoWords() {
		// Lets check for several words...
		SmartScriptLexer lexer = new SmartScriptLexer("  Štefanija\r\n\t Automobil   ");

		// We expect the following stream of tokens
		SmartScriptToken correctData[] = {
			new SmartScriptToken(SmartScriptTokenType.WORD, "Štefanija\r\n\t Automobil   "),
			new SmartScriptToken(SmartScriptTokenType.EOF, null)
		};

		checkTokenStream(lexer, correctData);
	}
	
	
	
	@Test
	public void testWordStartingWithEscape() {
		// Lets check for several words...
		SmartScriptLexer lexer = new SmartScriptLexer("This is \\{sample text.");

		// We expect the following stream of tokens
		SmartScriptToken correctData[] = {
			new SmartScriptToken(SmartScriptTokenType.WORD, "This is \\{sample text."),
			new SmartScriptToken(SmartScriptTokenType.EOF, null)
		};

		checkTokenStream(lexer, correctData);
	}

	

	
	@Test
	public void testInvalidEscape() {
		SmartScriptLexer lexer = new SmartScriptLexer("ovo je \\a");
		

		// will throw!
		assertThrows(SmartScriptLexerException.class, () -> lexer.nextToken());
	}

	
	@Test
	public void testSingleEscapedDigit() {
		
		SmartScriptLexer lexer = new SmartScriptLexer("ovo je \\{a");
		lexer.setState(SmartScriptLexerState.TEXT);
		// We expect the following stream of tokens
		SmartScriptToken correctData[] = {
			new SmartScriptToken(SmartScriptTokenType.WORD, "ovo je \\{a"),
			new SmartScriptToken(SmartScriptTokenType.EOF, null)
		};

		checkTokenStream(lexer, correctData);
	}

	
	
	@Test
	public void testSomeSymbols() {
		// Lets check for several symbols...
		SmartScriptLexer lexer = new SmartScriptLexer("{$ -.?  ##   $}");
		lexer.setState(SmartScriptLexerState.INSIDETAGS);

		SmartScriptToken correctData[] = {
			new SmartScriptToken(SmartScriptTokenType.BEGINTAG, Character.valueOf('{')),
			new SmartScriptToken(SmartScriptTokenType.OPERATOR, Character.valueOf('-')),
			new SmartScriptToken(SmartScriptTokenType.OPERATOR, Character.valueOf('.')),
			new SmartScriptToken(SmartScriptTokenType.OPERATOR, Character.valueOf('?')),
			new SmartScriptToken(SmartScriptTokenType.OPERATOR, Character.valueOf('#')),
			new SmartScriptToken(SmartScriptTokenType.OPERATOR, Character.valueOf('#')),
			new SmartScriptToken(SmartScriptTokenType.ENDTAG, Character.valueOf('}')),
			new SmartScriptToken(SmartScriptTokenType.EOF, null)
		};

		checkTokenStream(lexer, correctData);
	}
	
	@Test
	public void testSome() {
		// Lets check for several symbols...
		SmartScriptLexer lexer = new SmartScriptLexer("{$= i i * @sin  \"0.000\" @decfmt $}");
		lexer.setState(SmartScriptLexerState.INSIDETAGS);

		SmartScriptToken correctData[] = {
			new SmartScriptToken(SmartScriptTokenType.BEGINTAG, Character.valueOf('{')),
			new SmartScriptToken(SmartScriptTokenType.EQUALSIGN, '='),
			new SmartScriptToken(SmartScriptTokenType.VARIABLE, "i"),
			new SmartScriptToken(SmartScriptTokenType.VARIABLE, "i"),
			new SmartScriptToken(SmartScriptTokenType.OPERATOR, Character.valueOf('*')),
			new SmartScriptToken(SmartScriptTokenType.FUNCTION, "sin"),
			new SmartScriptToken(SmartScriptTokenType.STRING, "0.000"),
			new SmartScriptToken(SmartScriptTokenType.FUNCTION, "decfmt"),
			new SmartScriptToken(SmartScriptTokenType.ENDTAG, '}'),
			new SmartScriptToken(SmartScriptTokenType.EOF, null)
		};

		checkTokenStream(lexer, correctData);
	}
	
	@Test
	public void testSome2() {
		// Lets check for several symbols...
		SmartScriptLexer lexer = new SmartScriptLexer("{$= i i * \"@sin\"  \"0.000\" @decfmt $}");
		lexer.setState(SmartScriptLexerState.INSIDETAGS);

		SmartScriptToken correctData[] = {
			new SmartScriptToken(SmartScriptTokenType.BEGINTAG, Character.valueOf('{')),
			new SmartScriptToken(SmartScriptTokenType.EQUALSIGN, '='),
			new SmartScriptToken(SmartScriptTokenType.VARIABLE, "i"),
			new SmartScriptToken(SmartScriptTokenType.VARIABLE, "i"),
			new SmartScriptToken(SmartScriptTokenType.OPERATOR, Character.valueOf('*')),
			new SmartScriptToken(SmartScriptTokenType.STRING, "@sin"),
			new SmartScriptToken(SmartScriptTokenType.STRING, "0.000"),
			new SmartScriptToken(SmartScriptTokenType.FUNCTION, "decfmt"),
			new SmartScriptToken(SmartScriptTokenType.ENDTAG, '}'),
			new SmartScriptToken(SmartScriptTokenType.EOF, null)
		};

		checkTokenStream(lexer, correctData);
	}
	
	@Test
	public void testSome3() {
		// Lets check for several symbols...
		SmartScriptLexer lexer = new SmartScriptLexer("{$= i i * \"@sin\" \\   \"0.000\" @decfmt $}");
		lexer.setState(SmartScriptLexerState.INSIDETAGS);

		SmartScriptToken correctData[] = {
			new SmartScriptToken(SmartScriptTokenType.BEGINTAG, Character.valueOf('{')),
			new SmartScriptToken(SmartScriptTokenType.EQUALSIGN, '='),
			new SmartScriptToken(SmartScriptTokenType.VARIABLE, "i"),
			new SmartScriptToken(SmartScriptTokenType.VARIABLE, "i"),
			new SmartScriptToken(SmartScriptTokenType.OPERATOR, Character.valueOf('*')),
			new SmartScriptToken(SmartScriptTokenType.STRING, "@sin"),
			new SmartScriptToken(SmartScriptTokenType.OPERATOR, '\\'),
			new SmartScriptToken(SmartScriptTokenType.STRING, "0.000"),
			new SmartScriptToken(SmartScriptTokenType.FUNCTION, "decfmt"),
			new SmartScriptToken(SmartScriptTokenType.ENDTAG, '}'),
			new SmartScriptToken(SmartScriptTokenType.EOF, null)
		};

		checkTokenStream(lexer, correctData);
	}
	
	
	@Test
	public void testCombinedInput() {
		// Lets check for several symbols...
		SmartScriptLexer lexer = new SmartScriptLexer("Janko 3! Jasmina 5; -24");

		SmartScriptToken correctData[] = {
			new SmartScriptToken(SmartScriptTokenType.WORD, "Janko 3! Jasmina 5; -24"),
			new SmartScriptToken(SmartScriptTokenType.EOF, null),
		
		};

		checkTokenStream(lexer, correctData);
	}
	
	// Helper method for checking if lexer generates the same stream of tokens
	// as the given stream.
	private void checkTokenStream(SmartScriptLexer lexer, SmartScriptToken[] correctData) {
		int counter = 0;
		for(SmartScriptToken expected : correctData) {
			SmartScriptToken actual = lexer.nextToken();
			String msg = "Checking token "+counter + ":";
			assertEquals(expected.getType(), actual.getType(), msg);
			assertEquals(expected.getValue(), actual.getValue(), msg);
			counter++;
		}
	}



	
	@Test
	public void testNullState() {
		assertThrows(NullPointerException.class, () -> new SmartScriptLexer("").setState(null));
	}
	
	

}
