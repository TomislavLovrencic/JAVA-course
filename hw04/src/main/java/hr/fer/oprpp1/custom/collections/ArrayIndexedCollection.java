package hr.fer.oprpp1.custom.collections;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * @author Tomislav Lovrencic
 *
 * This class represents array indexed collection. Its used for storing objects with the help of array. The class extends
 * Collection class.
 *
 *	@param <T> Object of type T that will be stored in collection
 */

public class ArrayIndexedCollection<T> implements List<T> {
	
	private int size;
	private Object[] elements;
	private long modificationCount = 0L;
	
	
	/**
	 * This is a basic constructor with zero arguments , it initializes the array on default capacity that is 16.
	 */
	public ArrayIndexedCollection() {
		this(16);
		
	}

	/**
	 * This is a constructor that initializes the array on given capacity.
	 * @param initalCapacity 
	 */
	public ArrayIndexedCollection(int initalCapacity) {
		if(initalCapacity < 1) {
			throw new IllegalArgumentException("InitalCapacity cant must be greater than 0");
		}
		
		elements = new Object[initalCapacity];
		
		size = 0;
	}

	/**
	 * This is a constructor that initializes an array with given collection and its elements , on a default capacity
	 * which is 16.
	 * @param other Collection which elements are implemented in this array.
	 */
	public ArrayIndexedCollection(Collection<? extends T> other) {
		this(other,16);
		
	}

	/**
	 * This is a constructor that initializes an array with given collection and given capacity.
	 * 
	 * @param other Collection which elements are implemented in this array.
	 * @param initalCapacity Sets the capacity of this array.
	 */
	public ArrayIndexedCollection(Collection<? extends T> other,int initalCapacity) {
		Objects.requireNonNull(other,"Collection cant be null");
		
		if(initalCapacity < other.size()) {
			
			elements = new Object[other.size()];
			size = other.size();
		}
		else {
			
			elements = new Object[initalCapacity];
			size = 0;
		}
		addAll(other);
		
	}
	
	/**
	 * This method is used for getting an element on this given index.
	 * 
	 * @param index Index on which the elements we want is located.
	 * @return returns the object on given index.
	 */
	@SuppressWarnings("unchecked")
	public T get(int index) {
		if(index < 0 || index > size-1) {
			throw new IndexOutOfBoundsException("Index out of range!");
		}
		return (T) elements[index];
	}
	
	/**
	 * This method is used for insertion of an given object into given position in array.
	 * 
	 * @param value T that will be inserted into this array.
	 * @param position Position on which the object will be inserted.
	 */
	public void insert(T value,int position) {
		Objects.requireNonNull(value,"Null element is not allowed to store in array!");
		
		if(position < 0 || position > size) {
			throw new IndexOutOfBoundsException("Index out of range!");
		}
		
		if(size == elements.length) {
			elements = Arrays.copyOf(elements, elements.length*2);
		}
		
		for(int i=size-1;i>=position;i--) {
			elements[i+1] = elements[i];
		}
		
		modificationCount++;
		elements[position] = value;
		size++;
			
	}
	
	/**
	 * This method gives us an index of a given object in this array.
	 * @param value Object of which we are looking for index in array.
	 * @return Returns the index of given object if found , -1 otherwise.
	 */
	public int indexOf(Object value) {
		
		int index = -1;
		for(int i=0; i<size; i++) {
			if(elements[i].equals(value)) {
				index = i;
				break;
			}
		}
		return index;
	}
	
	/**
	 * This method removes an element on given position.
	 * @param index position on which we want to remove an element.
	 */
	public void remove(int index) {
		if(index < 0 || index > size-1) {
			throw new IndexOutOfBoundsException("Index out of range!");
		}
		elements[index] = null;
		for(int i=index; i< size-1; i++) {
			elements[i] = elements[i+1];
		}
		elements[size-1] = null;
		size--;
		modificationCount++;
	}

	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	
	@Override
	public int size() {
		return size;
	}

	@Override
	public void add(T value) {
		Objects.requireNonNull(value,"Null element is not allowed to store in array!");
		
		if(size == elements.length) {
			elements = Arrays.copyOf(elements, elements.length*2);
		}
		
		modificationCount++;
		elements[size] = value;
		size++;
	}

	@Override
	public boolean contains(Object value) {
		
		for(Object elem : elements) {
			if(elem != null && elem.equals(value)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean remove(Object value) {
		Objects.requireNonNull(value,"Object cant be null!");
		
		int index = indexOf(value);
		
		if(index != -1) {
			remove(index);
			modificationCount++;
			return true;
		}
		return false;
		
	}

	@Override
	public Object[] toArray() {
		Object[] tempElem = new Object[size];
		
		for(int i=0;i<size;i++) {
			tempElem[i] = elements[i];
		}
		return tempElem;
	}


	@Override
	public void clear() {
		for(int i =0;i<size;i++) {
			elements[i] = null;
		}
		size = 0;
		modificationCount++;
	}
	
	/**
	 * @author Tomislav Lovrencic
	 * This is a private static nested class used to create an iterator that can go through our collection.
	 *
	 * @param <T> T element that will be used and stored in collection.
	 */
	private static class getElements<T> implements ElementsGetter<T>  {
		private int position = 0;
		private ArrayIndexedCollection<T> array;
		private long savedModificationCount;
		
		
		/**
		 * This is a basic constructor.
		 * @param other
		 */
		public getElements(ArrayIndexedCollection<T> other) {
			array = other;
			savedModificationCount = other.modificationCount;
		}
		
		/**
		 * This method is used to see if there is a  next element in collection.
		 */
		public  boolean hasNextElement() {
			if(savedModificationCount != array.modificationCount) {
				throw new ConcurrentModificationException();
			}
			return array.elements[position] != null;
			
		}
		/**
		 * This method is used to get next element in collection.
		 */
		public T getNextElement() {
			if(savedModificationCount != array.modificationCount) {
				throw new ConcurrentModificationException();
			}
			@SuppressWarnings("unchecked")
			T obj = (T) array.elements[position];
			if(!hasNextElement()) {
				throw new NoSuchElementException();
			}
			position++;
			return obj;
		}
		
		@Override
		public void processRemaining(Processor<? super T> p) {
			for(int i = position;i<array.size;i++) {
				p.process(array.elements[i]);
			}	
		}
		
		
	}
	
	@Override
	public ElementsGetter<T> createElementsGetter() {
		return new getElements<T>(this);
	}
	



	
	
	
	
	

	
	
	
	
}
