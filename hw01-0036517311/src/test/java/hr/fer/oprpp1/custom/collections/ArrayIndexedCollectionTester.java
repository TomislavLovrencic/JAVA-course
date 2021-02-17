package hr.fer.oprpp1.custom.collections;




import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;



public class ArrayIndexedCollectionTester {
	
	ArrayIndexedCollection b;
	
	 @BeforeEach
	 public void initialize(){
	    b = new ArrayIndexedCollection();
	 }
	
	
	@Test
	public void testConstructorWithCapacity() {
		assertThrows(IllegalArgumentException.class , () -> {
			ArrayIndexedCollection nova = new ArrayIndexedCollection(-1);
		});
	}
	
	@Test
	public void testConstructorWithCapacityAndCollection() {
		assertThrows(NullPointerException.class , () -> {
			ArrayIndexedCollection nova = new ArrayIndexedCollection(null,2);
		});
	}

	@Test
	public void testConstructorWithCollection() {
		assertThrows(NullPointerException.class , () -> {
			ArrayIndexedCollection nova = new ArrayIndexedCollection(null);
		});
	}
	
	@Test
	public void testGetingElementsException() {
		assertThrows(IndexOutOfBoundsException.class , () -> {
			ArrayIndexedCollection nova = new ArrayIndexedCollection();
			b.get(-1);
		});
		
	}
	
	@Test
	public void testGet() {
		b.add(1);
		assertEquals(b.get(0), 1);
		
	}
	
	
	
	@Test
	public void testInsertingNullElementsToCollection() {
		assertThrows(NullPointerException.class , () -> {
			b.insert(null,1);
		});
		
	}
	
	@Test
	public void testInsertingElementsToCollectionException() {
		assertThrows(IndexOutOfBoundsException.class , () -> {
			Object elem = new Object();
			b.insert(elem,-1);
		});
		
	}
	
	@Test
	public void testInsert1() {
		b.add("ivana");
		b.add("pero");
		b.insert("klara", 1);
		assertEquals(b.get(2), "pero");
		
	}
	
	@Test
	public void testInsert2() {
		b.add("ivana");
		b.add("pero");
		b.insert("klara", 1);
		assertEquals(b.get(1), "klara");
		
	}
	


	
	@Test
	public void testRemovingElementsFromCollectionExcpetion() {
		assertThrows(IndexOutOfBoundsException.class , () -> {
			b.remove(-1);
		});
		
	}
	
	@Test
	public void testRemove() {
		b.add("ivana");
		b.add("pero");
		b.remove("pero");
		assertEquals(b.size(), 1);
		
	}
	
	
	
	@Test
	public void testAddingElementsToCollectionException() {
		assertThrows(NullPointerException.class , () -> {
			b.add(null);
		});
		
	}
	
	@Test
	public void testAdd() {
		b.add("ivana");
		b.add("marija");
		assertEquals(b.get(1),"marija");
		
	}
	
	@Test
	public void testIndexOf1() {
		b.add("ivana");
		b.add("marija");
		int index = b.indexOf("marija");
		assertEquals(index,1);
			
	}
	
	@Test
	public void testIndexOf2() {
		b.add("ivana");
		b.add("ivana");
		int index = b.indexOf("ivana");
		assertEquals(index,0);
			
	}
	
	@Test
	public void testClear() {
		b.add("ivana");
		b.add("marija");
		b.add("pero");
		b.clear();
		assertEquals(0,b.size());
			
	}
	
	@Test
	public void addAllTester() {
		ArrayIndexedCollection coll = new ArrayIndexedCollection();
		coll.add("DASD");
		coll.add("DD");
		coll.add("blabla");
		
		b.addAll(coll);
		

		assertEquals(b.get(2),"blabla");
	}
	
	 

    @Test
    public void add() {
        b.add(52);
        b.add("Jure");

        Object[] array = b.toArray();

        assertEquals(52,array[0]);
        assertTrue("Jure".equals(array[1]));
    }

    @Test
    public void addNull(){
    	assertThrows(NullPointerException.class , () -> {
    		b.add(null);
		});
    	
        
    }

    @Test
    public void get() {
        b.add(52);

        assertEquals(52,b.get(0));

    }

    public void getInvalidIndex(){
    	assertThrows(IndexOutOfBoundsException.class , () -> {
    		b.get(52);
		});
        
    }

    @Test
    public void clear() {
        b.add(42);
        b.clear();
        assertEquals(0,b.size());
    }

    @Test
    public void insert() {
        b.add(42);
        b.add("Stipe");
        b.insert(89,1);

        assertEquals(89,b.get(1));

    }

    @Test
    public void insertNull(){
    	assertThrows(NullPointerException.class , () -> {
    		b.insert(null,4);
		});
        
    }

    @Test
    public void insertInvalidIndex(){
    	assertThrows(IndexOutOfBoundsException.class , () -> {
    		b.insert(42,42);
		});
        
    }

    @Test
    public void indexOf() {
        b.add(1);
        b.add(2);
        b.add(3);

        assertEquals(2,b.indexOf(3));

    }

    @Test
    public void indexOfNull(){
        assertEquals(-1,b.indexOf(null));
    }

    @Test
    public void removeIndex() {
        b.add(5);
        b.add(3);
        b.add(2);
        b.remove(1);

        assertEquals(2,b.get(1));
        assertEquals(2,b.size());

    }

    @Test
    public void removeElement(){
        b.add(5);
        b.add("Stipe");
        b.add(2);

        b.remove("Stipe");

        assertEquals(2,b.get(1));
    }

    @Test
    public void size() {
        b.add(5);
        b.add(9);

        assertEquals(2,b.size());
    }

    @Test
    public void contains() {
        b.add(5);
        b.add(7);
        b.clear();

        assertThrows(IndexOutOfBoundsException.class , () -> {
        	b.remove(1);
			b.get(2);
		});
        
    }

    @Test
    public void toArray() {
        b.add(1);
        b.add(2);
        b.add(3);
        b.add(4);

        Object[] array = b.toArray();

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

        b.add(1);
        b.add(2);
        b.add(3);

        TestProcessor testProcessor = new TestProcessor();

        b.forEach(testProcessor);

        assertEquals(6,testProcessor.i);

    }

    @Test
    public void isEmpty() {

        assertTrue(b.isEmpty());

        b.add(5);

        assertFalse(b.isEmpty());
    }

    @Test
    public void addAll() {
        ArrayIndexedCollection testCollection = new ArrayIndexedCollection();
        testCollection.add(5);
        testCollection.add(6);
        testCollection.add(7);

        b.addAll(testCollection);

        assertEquals(5,b.get(0));
        assertEquals(6,b.get(1));
        assertEquals(7,b.get(2));

    }

	
	
}
