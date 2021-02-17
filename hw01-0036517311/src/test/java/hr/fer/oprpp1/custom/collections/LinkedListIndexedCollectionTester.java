package hr.fer.oprpp1.custom.collections;



import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LinkedListIndexedCollectionTester {

	LinkedListIndexedCollection list;
	
	 @BeforeEach
	 public void initialize(){
	    list = new LinkedListIndexedCollection();
	 }
	
	
	@Test
	public void testInsert1() {
		list.add("tompa");
		list.add("kompa");
		list.add(Integer.valueOf(4));
		list.insert("MARIJA", 1);
		list.insert("kok", 3);
		
		assertEquals("kok", list.get(3));
	}
	
	@Test
	public void testInsert2() {
		list.add("tompa");
		list.add("kompa");
		list.add(Integer.valueOf(4));
		list.insert("MARIJA", 3);
		
		assertEquals("MARIJA", list.get(3));
	}
	
	@Test
	public void testRemove1() {
		list.add("tompa");
		list.add("kompa");
		list.add(Integer.valueOf(4));
		list.add("llllll");
		list.add("ddddd");
		list.add("ooooo");
		list.remove("kompa");
		list.remove(Integer.valueOf(4));
		
		assertEquals("ddddd",list.get(2));
	}
	
	@Test
	public void testRemove2() {
		list.add("tompa");
		list.add("kompa");
		list.add(Integer.valueOf(4));
		list.add("llllll");
		list.add("ddddd");
		list.add("ooooo");
		list.remove(2);
		list.remove(4);
		
		assertEquals("ddddd",list.get(3));
	}
	
	@Test
	public void testRemove3() {
		list.add("tompa");
		list.add("kompa");
		list.add(Integer.valueOf(4));
		list.add("llllll");
		list.add("ddddd");
		list.add("ooooo");
		list.remove("ooooo");
		list.remove(2);
		
		assertEquals("kompa",list.get(1));
	}
	
	@Test
	public void testRemove4() {
		list.add("tompa");
		list.add("kompa");
		list.add(Integer.valueOf(4));
		list.add("llllll");
		list.add("ddddd");
		list.add("ooooo");
		list.remove("ooooo");
		
		assertEquals("ddddd",list.get(4));
	}
	
	@Test
	public void testGet1() {
		list.add("tompa");
		list.add("kompa");
		list.add(Integer.valueOf(4));
		list.add("llllll");
		list.add("ddddd");
		list.add("ooooo");
		list.add(Integer.valueOf(9));
		list.add("dsd");
		list.add("keko");
		list.add("mleko");
		
		assertEquals("keko",list.get(8));
		
	}
	
	@Test
	public void testGet2() {
		list.add("tompa");
		list.add("kompa");
		list.add(Integer.valueOf(4));
		list.add("llllll");
		list.add("ddddd");
		list.add("ooooo");
		list.add(Integer.valueOf(9));
		list.add("dsd");
		list.add("keko");
		list.add("mleko");
		
		assertEquals("mleko",list.get(list.size()-1));
		
	}
	
	@Test
	public void testIndexOf1() {
		list.add("tompa");
		list.add("kompa");
		list.add(Integer.valueOf(4));
		list.add("llllll");
		
		
		assertEquals(2,list.indexOf(Integer.valueOf(4)));
		
	}
	
	@Test
	public void testIndexOf2() {
		list.add("tompa");
		list.add("kompa");
		list.add(Integer.valueOf(4));
		list.add("llllll");
		
		
		assertEquals(3,list.indexOf("llllll"));
		
	}
	
	@Test
	public void testIndexOf3() {
		list.add("tompa");
		list.add("kompa");
		list.add(Integer.valueOf(4));
		list.add("llllll");
		
		
		assertEquals(0,list.indexOf("tompa"));
		
	}
	
	@Test
	public void testAdd() {
		list.add("tompa");
		list.add("kompa");
		list.add(Integer.valueOf(4));
		list.add("llllll");
		
		
		assertEquals("llllll",list.get(3));
		
	}
	
	@Test
	public void testContains() {
		list.add("tompa");
		list.add("kompa");
		list.add(Integer.valueOf(4));
		list.add("llllll");
		
		assertEquals(true,list.contains("tompa"));
		
	}
	
	@Test
	public void testClear() {
		list.add("tompa");
		list.add("kompa");
		list.add(Integer.valueOf(4));
		list.add("llllll");
		
		list.clear();
		
		assertEquals(0,list.size());
		
	}
	
	@Test
	public void testAddAll() {
		
		ArrayIndexedCollection coll = new ArrayIndexedCollection();
		coll.add("1");
		coll.add("2");
		coll.add("3");
		
		LinkedListIndexedCollection l = new LinkedListIndexedCollection(coll);
		
		boolean bool = true;
		for(int i =0 ; i<l.size();i++) {
			if(!l.get(0).equals(coll.get(0))) {
				bool = false;
			}
		}
		assertEquals(bool,true);
		assertEquals(l.size(),coll.size());
		
	}
	@Test
    public void add() {
        list.add(52);
        list.add("Jure");

        Object[] array = list.toArray();

        assertEquals(52,array[0]);
        assertTrue("Jure".equals(array[1]));
    }

    @Test
    public void addNull(){
    	assertThrows(NullPointerException.class , () -> {
    		list.add(null);
		});
        
    }

    @Test
    public void get() {
        list.add(52);

        assertEquals(52,list.get(0));

    }

    @Test
    public void getInvalidIndex(){
    	assertThrows(IndexOutOfBoundsException.class , () -> {
    		list.get(52);
		});
        
    }

    @Test
    public void clear() {
        list.add(42);
        list.clear();
        list.contains("dsad");
        list.indexOf("dd");
        assertEquals(0,list.size());
    }

    @Test
    public void insert() {
        list.add(42);
        list.add("Stipe");
        list.insert(89,1);

        assertEquals(89,list.get(1));

    }

    @Test
    public void insertNull(){
    	assertThrows(NullPointerException.class, () -> {
    		 list.insert(null,4);
		});
       
    }

    @Test
    public void insertInvalidIndex(){
    	assertThrows(IndexOutOfBoundsException.class , () -> {
    		list.insert(42,42);
		});
        
    }

    @Test
    public void indexOf() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(2,list.indexOf(3));

    }

    @Test
    public void indexOfNull(){
        assertEquals(-1,list.indexOf(null));
    }

    @Test
    public void removeIndex() {
        list.add(5);
        list.add(3);
        list.add(2);
        list.remove(1);

        assertEquals(2,list.get(1));
        assertEquals(2,list.size());

    }

    @Test
    public void removeElement(){
        list.add(5);
        list.add("Stipe");
        list.add(2);

        list.remove("Stipe");

        assertEquals(2,list.get(1));
    }

    @Test
    public void size() {
        list.add(5);
        list.add(9);

        assertEquals(2,list.size());
    }

    @Test
    public void contains() {
        list.add(5);
        list.add(7);

        assertTrue(list.contains(5));
        assertTrue(list.contains(7));

    }

    @Test
    public void toArray() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        Object[] array = list.toArray();

        assertEquals(1,array[0]);
        assertEquals(2,array[1]);
        assertEquals(3,array[2]);
        assertEquals(4,array[3]);
    }

    @Test
    public void forEach() {
        class TestProcessor extends Processor{
            int i=0;

            @Override
            public void process(Object value){
                i+=2;
            }
        }

        list.add(1);
        list.add(2);
        list.add(3);

        TestProcessor testProcessor = new TestProcessor();

        list.forEach(testProcessor);

        assertEquals(6,testProcessor.i);

    }

    @Test
    public void isEmpty() {

        assertTrue(list.isEmpty());

        list.add(5);

        assertFalse(list.isEmpty());
    }

    @Test
    public void addAll() {
        LinkedListIndexedCollection testCollection = new LinkedListIndexedCollection();
        testCollection.add(5);
        testCollection.add(6);
        testCollection.add(7);

        list.addAll(testCollection);

        assertEquals(5,list.get(0));
        assertEquals(6,list.get(1));
        assertEquals(7,list.get(2));

    }
	
	
}
