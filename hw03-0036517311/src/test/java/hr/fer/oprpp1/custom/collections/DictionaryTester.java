package hr.fer.oprpp1.custom.collections;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



public class DictionaryTester {

	Dictionary<Integer,String> dictionary = new Dictionary<>(); 
	
	@Test
	public void testPut1() {
		dictionary.put(1, "jedan");
		dictionary.put(2, "dva");
		dictionary.put(3, "tri");
		
		assertEquals("dva",dictionary.get(2));
		
	}
	
	@Test
	public void testPut2() {
		dictionary.put(1, "jedan");
		dictionary.put(2, "dva");
		dictionary.put(3, "tri");
		dictionary.put(2, "nova vrijednost");
		
		assertEquals("nova vrijednost",dictionary.get(2));
		
	}

	
	@Test
	public void testRemove1() {
		dictionary.put(1, "jedan");
		dictionary.put(2, "dva");
		dictionary.put(3, "tri");
		dictionary.put(2, "nova vrijednost");
		dictionary.remove(3);
		
		assertEquals(null,dictionary.get(3));
	}
	
	@Test
	public void testRemove2() {
		dictionary.put(1, "jedan");
		dictionary.put(2, "dva");
		dictionary.put(3, "tri");
		dictionary.remove(1);
		
		assertEquals(2,dictionary.size());
	}

	
	@Test
	public void testGet1() {
		dictionary.put(1, "jedan");
		dictionary.put(2, "dva");
		dictionary.put(3, "tri");
		dictionary.put(8, "osam");
		
		assertEquals("osam",dictionary.get(8));
	}

	
	@Test
	public void testValues() {
		dictionary.put(2, null);
		assertEquals(null,dictionary.get(2));
	}
	
	@Test
	public void testClear() {
		dictionary.put(1, "jedan");
		dictionary.put(2, "dva");
		dictionary.put(3, "tri");
		dictionary.put(8, "osam");
		dictionary.clear();
		assertEquals(0,dictionary.size());
	}
	
	@Test
    public void testAddValueAndReturn(){
        Dictionary<String, Integer> dictionary = new Dictionary<>();

        dictionary.put("First", 1);
        dictionary.put("Second", 2);

        assertEquals(1,dictionary.get("First"));
    }

    @Test
    public void testAddValueAndRemove(){
        Dictionary<String, Integer> dictionary = new Dictionary<>();

        dictionary.put("First", 1);
        dictionary.put("Second", 2);

        dictionary.remove("First");
        assertNull(dictionary.get("First"));
    }

    @Test
    public void testGetSize(){
        Dictionary<String, Integer> dictionary = new Dictionary<>();

        dictionary.put("First", 1);
        dictionary.put("Second", 2);

        assertEquals(2,dictionary.size());
    }

    @Test
    public void testEmpty(){
        Dictionary<String, Integer> dictionary = new Dictionary<>();

        assertTrue(dictionary.isEmpty());
    }

    @Test
    public void testIsEmptyAfterClear(){
        Dictionary<String, Integer> dictionary = new Dictionary<>();

        dictionary.put("First", 1);
        dictionary.put("Second", 2);

        dictionary.clear();

        assertTrue(dictionary.isEmpty());
    }

    @Test
    public void testRemoveGetsOldValue(){
        Dictionary<String, Integer> dictionary = new Dictionary<>();

        dictionary.put("First", 1);
        dictionary.put("Second", 2);

        assertEquals(1,dictionary.remove("First"));
    }

    @Test
    public void testPutOverWrites(){
        Dictionary<String, Integer> dictionary = new Dictionary<>();

        dictionary.put("First", 1);
        dictionary.put("Second", 2);

        dictionary.put("First",3);

        assertEquals(3,dictionary.get("First"));
    }

    @Test
    public void testRemove(){
        Dictionary<String, Integer> dictionary = new Dictionary<>();

        dictionary.put("First", 1);
        dictionary.put("Second", 2);

        dictionary.remove("First");

        assertEquals(1,dictionary.size());
    }
	
	
}
