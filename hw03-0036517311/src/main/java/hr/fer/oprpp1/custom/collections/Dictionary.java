package hr.fer.oprpp1.custom.collections;

import java.util.Objects;

/**
 * @author Tomislav Lovrencic
 * 
 * This is is class that represnets a dictionary. It is implemented as an adapter to arrayIndexedCollection.
 *
 * @param <K> Key values of dictionary.
 * @param <V> Values stored with keys.
 */
public class Dictionary<K,V> {

	/**
	 * Reference of nested class that represents a pair of key : value inside a dictionary.
	 */
	DictionaryPair pair;
	
	
	/**
	 *  This is a collection used to store pairs.
	 */
	ArrayIndexedCollection<DictionaryPair> collection;
	
	/**
	 *  This is a basic constructor.
	 */
	public Dictionary() {
		collection = new ArrayIndexedCollection<Dictionary<K,V>.DictionaryPair>();
	}
	
	/**
	 * This method checks if dictionary is empty.
	 * @return
	 */
	public boolean isEmpty() {
		return collection.isEmpty();
	}
	
	public int size() {
		return collection.size();
	}
	
	/**
	 *  this method deletes all elements from ditionary.
	 */
	public void clear() {
		collection.clear();
	}
	
	/**
	 * This method is used to put a pair inside a dictionary.
	 * @param key
	 * @param value
	 * @return Value of pair if the given key already existed in dictionary , null otherwise.
	 */
	public V put(K key, V value) {
		DictionaryPair pair = new DictionaryPair(key,value);
		V object = null;
		for(int i=0;i<collection.size();i++) {
			if(collection.get(i).getKey().equals(key)) {
				object = collection.get(i).getValue();
				collection.get(i).setValue(value);
			}
		}
		if(object == null) {
			collection.add(pair);
		}
		return object;
	
	}
	
	/**
	 * This method tells u the value of the pair with given key.
	 * @param key
	 * @return null if the element isnt in dictionary , value of element otherwise.
	 */
	public V get(Object key) {
		for(int i=0;i<collection.size();i++) {
			if(collection.get(i).getKey().equals(key)) {
				return collection.get(i).getValue();
			}
		}
		return null;
	}
	
	/**
	 * This method is used to remove an element from dictionary that has the same key as given.
	 * @param key
	 * @return null if the element isnt in the dictionary , value of element otherwise.
	 */
	public V remove(K key) {
		V value = null;
		for(int i=0;i<collection.size();i++) {
			if(collection.get(i).getKey().equals(key)) {
				value = collection.get(i).getValue();
				collection.remove(i);
			}
		}
		return value;
	}
	
	/**
	 * @author Tomislav Lovrencic
	 *
	 * This is a private nested class used to store a pair of key : values , that are 
	 * later stored into dictionary.
	 *
	 */
	private class DictionaryPair {
		/**
		 *  key value.
		 */
		private K key;
		
		/**
		 *  value of key.
		 */
		private V value;
		
		/**
		 * This is a basic constructor.
		 * @param key
		 * @param value
		 */
		public DictionaryPair(K key,V value) {
			Objects.requireNonNull(key);
			this.setKey(key);
			this.setValue(value);
		}

		/** 
		 * This is a getter for key.
		 * @return
		 */
		public K getKey() {
			return key;
		}

		/** 
		 * This is a setter for key.
		 * @return
		 */
		public void setKey(K key) {
			this.key = key;
		}

		/** 
		 * This is a getter for value.
		 * @return
		 */
		public V getValue() {
			return value;
		}

		/** 
		 * This is a setter for value.
		 * @return
		 */
		public void setValue(V value) {
			this.value = value;
		}
	}
}
