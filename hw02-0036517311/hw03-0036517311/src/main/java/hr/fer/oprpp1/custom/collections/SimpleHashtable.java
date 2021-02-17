package hr.fer.oprpp1.custom.collections;


import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;



/**
 * @author Tomislav Lovrencic
 *
 * This class represents SimpleHashtable , its used to store  elements(key:value) into array using hashing for
 * more efficency of getting and storing elements. For storing such elements this class uses {@link TableEntry}.
 *
 * @param <K>
 * @param <V>
 */
public class SimpleHashtable<K,V> implements Iterable<SimpleHashtable.TableEntry<K,V>>{

	/**
	 * Number of elements stored.
	 */
	private int size;
	
	/**
	 *  Array of TableEntries , used for storing elements.
	 */
	private TableEntry<K,V>[] tableEntry;
	
	/**
	 *  Variable used to keep track of modification with the hashtable.
	 */
	private int modificationCount;
	
	/**
	 * This is a basic constructor.
	 */
	@SuppressWarnings("unchecked")
	public SimpleHashtable() {
		tableEntry = new TableEntry[16];
		size = 0;
		modificationCount = 0;
	}
	
	/**
	 * This is a basic constructor which sets the tableEntry size on a quantity that is the power of number 2 that is 
	 * first greater than or equal to the given capacity.
	 * @param capacity
	 */
	@SuppressWarnings("unchecked")
	public SimpleHashtable(int capacity) {
		if(capacity < 1) throw new IllegalArgumentException();
		Integer a = (int) Math.ceil(Math.sqrt(capacity));
		tableEntry = new TableEntry[a];
		size = 0;
		modificationCount = 0;
	}
	
	/**
	 * This method is used to put a TableEntry<K,V> elem inside a TableEntry<K,V> array , if the spot is full its added to
	 * the element on that position imitating linked list.
	 * @param key
	 * @param value
	 * @return Value of elem if the key matches the existing key , null otherwise.
	 */
	public V put(K key,V value) {
		Objects.requireNonNull(key);
		
		V object = null;
		TableEntry<K,V> elem = new TableEntry<K,V>(key,value);
		Integer hash = Math.abs(key.hashCode()) % tableEntry.length;
		
		
		if(tableEntry[hash] == null) {
			if(checkIfOverFlow()) {
				doubleTheSize();
				put(key,value);
			}
			else {
				tableEntry[hash] = elem;
				size++;
				modificationCount++;
			}
		}
		else {
			TableEntry<K,V> pom = tableEntry[hash];
			 do {
				if(pom.getKey().equals(key)) {
					object = pom.getValue();
					pom.setValue(value);
					return object;
				}
				if(pom.next != null) pom = pom.next;
			}while(pom.next != null);
			
			if(pom.getKey().equals(key)) {
				object = pom.getValue();
				pom.setValue(value);
				return object;
			}
			if(checkIfOverFlow()) {
				doubleTheSize();
				put(key,value);
			}
			else {
				pom.next = elem;
				size++;
				modificationCount++;
			}
		}
		return null;
	}
	
	/**
	 * This method is used to get an element from hashtable with given key.
	 * @param key
	 * @return null if the element is not in hashtable, value of elemenet otherwise.
	 */
	public V get(Object key) {
		if(key == null) return null;
		Integer hash = Math.abs(key.hashCode()) % tableEntry.length;
		if(tableEntry[hash] == null) return null;
		TableEntry<K,V> pom = tableEntry[hash];
		

		do {
			if(pom.getKey().equals(key)) {
				return pom.getValue();
			}
			pom = pom.next;
		}while(pom != null);
		return null;
	}
	
	/**
	 * This is a method that returns number of elements stored in hashtable.
	 * @return
	 */
	public int size() {
		return this.size;
	}
	
	/**
	 * This method is used to check whether there is an element with given key in hashtable.
	 * @param key
	 * @return true if the element is inside the hasthable,false otherwise.
	 */
	public boolean containsKey(Object key) {
		return get(key) != null;
	}
	
	/**
	 * This method is used to check whether there is an element with given value in hashtable.
	 * @param value
	 * @return true if the element is inside the hasthable,false otherwise.
	 */
	public boolean containsValue(Object value) {
		for(int i=0;i<tableEntry.length;i++) {
			TableEntry<K,V> pom = tableEntry[i];
			if(pom == null) continue;
			do {
				if(pom.getValue().equals(value)) {
					return true;
				}
				pom = pom.next;
			} while(pom != null);
		}
		return false;
	}
	
	
	/**
	 * This is a method that removes an element from hastable if the given key mathces a key from hashtable.
	 * @param key
	 * @return Value of removed element, null otherwise.
	 */
	public V remove(Object key) {
		if(key == null) return null;
		V objectValue = null;
		Integer hash = Math.abs(key.hashCode()) % tableEntry.length;
		
		if(tableEntry[hash] == null) return null;
		TableEntry<K,V> prev = tableEntry[hash];
		
		if(prev.next != null) {
			if(prev.getKey().equals(key)) {
				objectValue = prev.getValue();
				tableEntry[hash] = prev.next;
				modificationCount++;
				size--;
				return objectValue;
			}
			TableEntry<K,V> pom = prev.next;
			do {
				if(pom.getKey().equals(key)) {
					objectValue = pom.getValue();
					if(pom.next != null) {
						prev.next = pom.next;
					}
					else {
						prev.next = null; 

					}
					modificationCount++;
					size--;
					return objectValue;
				}
				pom = pom.next;
				prev = prev.next;
			}while(pom  != null);
		}
		else {
			if(prev.getKey().equals(key)) {
				objectValue = prev.getValue();
				tableEntry[hash] = null;
				modificationCount++;
				size--;
				return objectValue;
			}
		}
		
		return objectValue;
	}
	
	/**
	 * This is a method that shows whether there are elements inside this hashtable.
	 * @return true if the table is empty , false otherwise.
	 */
	public boolean isEmpty() {
		return this.size == 0;
	}
	
	/**
	 * This method is used to show elements from hashtable in a string.
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();
		TableEntry<K,V>[] newTable = toArray();
		
		str.append("[");
		for(int i =0;i<newTable.length-1;i++) {
			if(newTable[i] != null) {
				str.append(newTable[i].getKey()+"="+newTable[i].getValue()+", ");
			}
		}
		if(newTable[newTable.length-1] != null) {
			str.append(newTable[newTable.length-1].getKey()+"="+newTable[newTable.length-1].getValue());

		}
		str.append("]");
		return str.toString();
	}
	
	/**
	 * This method is used to create a single array from this table.
	 * @return new array of all elements inside this hashtable.
	 */
	@SuppressWarnings("unchecked")
	public TableEntry<K,V>[] toArray(){
		int index = 0;
		TableEntry<K,V>[] newTable = new TableEntry[size];
		
		for(int i=0;i<tableEntry.length;i++) {
			TableEntry<K,V> pom = tableEntry[i];
			if(pom == null) continue;
			 do{
				newTable[index] = pom;
				index++;
				pom = pom.next;
			}while(pom != null);
		}
		
		return newTable;
	}
	
	/**
	 * This method is used to check whether the ratio of the contents of the variable size to the size of the internal 
	 * reference field becomes equal to or greater than 75% of the number of slots
	 * @return True if flooded , false otherwise.
	 */
	private boolean checkIfOverFlow() {
		return ((double) size/tableEntry.length) >= 0.75;
		
	}
	

	
	/**
	 * This method is used then the method CheckIfOverFlow is true. It doubles the size of this table.
	 */
	@SuppressWarnings("unchecked")
	public void doubleTheSize() {
		TableEntry<K, V>[] newTable = new TableEntry[tableEntry.length * 2];
		TableEntry<K,V>[] helper = toArray();
		
		for(int i=0;i<helper.length;i++) {
			if(helper[i] != null) {
				helper[i].next = null;
			}
		}
		
		for(int i=0;i<helper.length;i++) {
			Integer hash = Math.abs(helper[i].getKey().hashCode()) % (tableEntry.length * 2);
			
			if(newTable[hash] == null) {
				newTable[hash] = helper[i];
			}
			else {
				TableEntry<K,V> elem = newTable[hash];
				while(elem.next != null) {
					elem = elem.next;
				}
				elem.next = helper[i];
			}
		}
		tableEntry = newTable;
	
	}
	
	/**
	 * This method is used to delete all elements from this hashtable. It doesnt reset the length of the table.
	 */
	public void clear() {
		for(int i=0;i<tableEntry.length;i++) {
			tableEntry[i] = null;
		}
	}
	
	/**
	 * @author Tomislav Lovrencic
	 *
	 * This is a static nested class used as an element in {@link SimpleHashtable}.
	 * 
	 * @param <K>
	 * @param <V>
	 */
	public static class TableEntry<K,V> {
		
		/**
		 * key value.
		 */
		private K key;
		
		/**
		 *  value of key.
		 */
		private V value;
		
		/**
		 *  Pointer to next element.
		 */
		TableEntry<K,V> next;

		/**
		 * This is a basic constructor.
		 * @param key
		 * @param value
		 */
		public TableEntry(K key,V value) {
			Objects.requireNonNull(key);
			this.key = key;
			this.value = value;
			this.next = null;
			
		}
		
		/**
		 * This is a getter for key
		 * @return
		 */
		public K getKey() {
			return this.key;
		}
		
		/**
		 * This is a getter for value
		 * @return
		 */
		public V getValue() {
			return this.value;
		}
		
		/**
		 * This is a setter for value
		 * @param value
		 */
		public void setValue(V value) {
			this.value = value;
		}
		
		
		/**
		 * This is a method that returns the element as a string.
		 */
		public String toString() {
			return "("+this.key+" : "+this.value+")";
		}
	}

	@Override
	public Iterator<TableEntry<K, V>> iterator() {
		return new IteratorImpl();
	}
	
	/**
	 * @author Tomislav Lovrencic
	 *
	 * This is a private nested class used as instance of iterator for easier looping through elements
	 * in {@link SimpleHashtable}.
	 *
	 */
	private class IteratorImpl implements Iterator<SimpleHashtable.TableEntry<K,V>> {
		
		/**
		 * Variable used to track if this iterator removed an element from class.
		 */
		private int savedModificationCount;
		
		/**
		 * Current element in hashTable.
		 */
		private TableEntry<K,V> current;
		
		/**
		 * Previous element in hashtable.
		 */
		private TableEntry<K,V> prev;
		
		
		/**
		 * Position in array.
		 */
		int position;
		
		/**
		 *  boolean to check whether the iterator has used the remove method 2 times in a row.
		 */
		private boolean firstRemove;
		
		/**
		 * This is a basic constructor.
		 */
		public IteratorImpl() {
			this.savedModificationCount = modificationCount;
			firstRemove = false;
			position  = 0;
			prev = null;
			
			for(int i=0;i<tableEntry.length;i++) {
				if(tableEntry[i] != null) {
					current = tableEntry[i];
					position = i;
					break;
				}
			}
		}
		
		/**
		 * This method is used to see it there is a next element in this array.
		 */
		public boolean hasNext() {
			if(savedModificationCount != modificationCount) {
				throw new ConcurrentModificationException();
			}
			
		
			return current != null;

			
		}
		/**
		 * This is a method that returns the next element in array.
		 */
		public TableEntry<K,V> next() {
			
			if(savedModificationCount != modificationCount) {
				throw new ConcurrentModificationException();
			}
			
			if (!hasNext()) {
                throw new NoSuchElementException();
            }
			
			for(int i=position;i<tableEntry.length;i++) {
				TableEntry<K,V> help = tableEntry[i];
				if(help != null) {
					if(help.equals(current)) {
						position = i;
					}
				}
			}
			
			firstRemove = false;
			
			prev = current;		
		
			if(current.next != null) {
				current = current.next;
			}
			else {
				position++;
				if(position == tableEntry.length) {
					current = null;
				}
				for(int i=position;i<tableEntry.length;i++) {
					TableEntry<K,V> helper = tableEntry[i];
					if(helper != null) {
						current = helper;
						break;
					}
					
				}	
			}
			
			return prev;
		} 
		
		/**
		 * This is a method that is used to remove an element that was last called from method next.
		 */
		public void remove() {
			
			if(savedModificationCount != modificationCount) {
				throw new ConcurrentModificationException();
			}
			
			if(firstRemove) {
				throw new IllegalStateException();
			}
			
			SimpleHashtable.this.remove(prev.key);
			
			savedModificationCount++;
			firstRemove = true;
			
			
		} 
	}
}
