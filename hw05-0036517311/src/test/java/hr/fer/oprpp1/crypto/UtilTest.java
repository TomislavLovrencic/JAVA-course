package hr.fer.oprpp1.crypto;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import hr.fer.oprpp1.hw05.crypto.Util;




public class UtilTest {

	 @Test
	    public void testHextobyteEmpty() {
	        String keyText = "";

	        byte[] expected = new byte[]{};

	        byte[] actual = Util.hextobyte(keyText);

	        assertArrayEquals(expected, actual);
	    }


	   @Test
	    public void testHextobyteInvalid() {
	    	
	        String keyText = "01a";
	        
	        assertThrows(IllegalArgumentException.class,() -> {
	        	   byte[] actual = Util.hextobyte(keyText);
	        });  

	    }




		@Test
	    public void testHextobyte() {
	        String keyText = "01aE22";

	        byte[] expected = new byte[]{1, -82, 34};

	        byte[] actual = Util.hextobyte(keyText);

	        assertArrayEquals(expected, actual);

	    }

	    @Test
	    public void testBytetohex() {
	        byte[] bytes = new byte[]{1, -82, 34};

	        String expected = "01ae22";

	        String actual = Util.bytetohex(bytes);

	        assertEquals(expected, actual);
	    }
}
