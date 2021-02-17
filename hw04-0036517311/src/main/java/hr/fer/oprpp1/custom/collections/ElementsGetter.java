package hr.fer.oprpp1.custom.collections;

/**
 * @author Tomislav Lovrencic
 * 
 * This is an interface used as a Iterator through our collections. Its purpose is to 
 * make it easire for us to loop through elements inside our collections.
 *
 * @param <T> Object of type T that is stored in collection
 */
public interface ElementsGetter<T> {

	/**
	 * This method tells us if this collection has next element.
	 * @return True if it has , false otherwise.
	 */
	boolean hasNextElement();
	
	/**
	 * This method returns the next element in our collection.
	 * @return next element.
	 */
	T getNextElement();
	
	/**
	 * This method is used to process elements in our collection and do some action on them.
	 * @param p
	 */
	void processRemaining(Processor<? super T> p);

}
