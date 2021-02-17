package hr.fer.oprpp1.custom.scripting.parser;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;
import hr.fer.oprpp1.custom.scripting.nodes.DocumentNode;

public class ParserTester {
	
	@Test
	public void primjer1() {
        String primjer = loader("primjer1.txt");
        assertEquals(true,compare(primjer));
    }
	@Test
	public void primjer2() {
        String primjer = loader("primjer2.txt");
        assertEquals(true,compare(primjer));
    }
	@Test
	public void primjer3() {
        String primjer = loader("primjer3.txt");
        assertEquals(true,compare(primjer));
    }
	@Test
	public void primjer4() {
	
		assertThrows(SmartScriptParserException.class, () ->  compare(loader("primjer4.txt")));
	}
	@Test
	public void primjer5() {
		assertThrows(SmartScriptParserException.class, () ->  compare(loader("primjer5.txt")));
    }
	@Test
	public void primjer6() {
        String primjer = loader("primjer6.txt");
        assertEquals(true,compare(primjer));
    }
	@Test
	public void primjer7() {
        String primjer = loader("primjer7.txt");
        assertEquals(true,compare(primjer));
    }
	@Test
	public void primjer8() {
        String primjer = loader("primjer8.txt");
        assertEquals(true,compare(primjer));
    }
	@Test
	public void primjer9() {
		assertThrows(SmartScriptParserException.class, () ->  compare(loader("primjer9.txt")));
    }
	
	@Test
	public void doc1() {
        String primjer = loader("doc1.txt");
        assertEquals(true,compare(primjer));
    }
	
	
	
	
	public boolean compare(String text) {
        SmartScriptParser parser = new SmartScriptParser(text);
        DocumentNode document = parser.getDocumentNode();
        String originalDocumentBody = document.toString();
        SmartScriptParser parser2 = new SmartScriptParser(originalDocumentBody);
        DocumentNode document2 = parser2.getDocumentNode();
        return document.equals(document2);
    }
	
	
	private String loader(String filename) {
    	ByteArrayOutputStream bos = new ByteArrayOutputStream();
    	try(InputStream is = this.getClass().getClassLoader().getResourceAsStream(filename)) {
    		byte[] buffer = new byte[1024]; 
    		while(true) { 
    			int read = is.read(buffer);
    			if(read<1) break;
    			bos.write(buffer, 0, read); 
    		} 
    		return new String(bos.toByteArray(), StandardCharsets.UTF_8); 
    		} catch(IOException ex) {
    			return null; 
    		} 
    }

}
