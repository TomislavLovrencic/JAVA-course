package hr.fer.oprpp1.custom.collections;

/**
 * @author Tomislav Lovrencic
 * 
 * This interface is used to iterate through elements of a collection.
 *
 */
public interface ElementsGetter {

	/**
	 * This method checks if the collection has next element.
	 * @return True if the collection has next element,false otherwise.
	 */
	boolean hasNextElement();
	
	/**
	 * This method is used to get next element.
	 * @return next element in collection.
	 */
	Object getNextElement();
	
	/**
	 * This method is used to do an operation on elements in collection.
	 * @param p
	 */
	void processRemaining(Processor p);
}
