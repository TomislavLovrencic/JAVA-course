package hr.fer.oprpp1.custom.collections;

/**
 * @author Tomislav Lovrencic
 *
 * This interface is used to create some basic functions for collection implementations.
 *
 */
public interface List extends Collection {

	/**
	 * This method is used to get an Object on given index.
	 * @param index
	 * @return object on given index.
	 */
	Object get(int index); 
	
	/**
	 *  This method is used to insert an object on given postion 
	 * @param value
	 * @param position
	 */
	void insert(Object value, int position);
	
	/**
	 * This method is used to get an index of given object in this collection.
	 * @param value
	 * @return
	 */
	int indexOf(Object value);
	
	/** This method is used to remove an element from collection.
	 * @param index
	 */
	void remove(int index);
}
