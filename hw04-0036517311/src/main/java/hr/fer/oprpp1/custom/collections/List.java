package hr.fer.oprpp1.custom.collections;

/**
 * @author Tomislav Lovrencic
 * 
 * This is an interface that represents Lists and has their most used methods.
 *
 * @param <T> Object of type T that will be stored in collection
 */
public interface List<T> extends Collection<T> {

	/**
	 * This is a method that returns element on given index.
	 * @param index
	 * @return element on given index.
	 */
	T get(int index); 
	
	/**
	 * This is a method that inserts an given element on given position.
	 * @param value Object of type T Element to be inserted.
	 * @param position Postion on which the element will be inserted.
	 */
	void insert(T value, int position);
	
	/**
	 * This is a method that returns the index of given element in collection.
	 * @param value Element which index we want to know.
	 * @return Index of element in collection.
	 */
	int indexOf(Object value);
	
	
	/**
	 * This is a method that removes an element from collection on given index.
	 * @param index
	 */
	void remove(int index);
}
