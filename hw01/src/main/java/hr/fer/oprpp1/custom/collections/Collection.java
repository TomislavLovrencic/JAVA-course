package hr.fer.oprpp1.custom.collections;

/**
 * @author Tomislav Lovrencic
 * 
 * This class represents Collection. It is used as a class that other classes implement and use its methods.
 *
 */
public class Collection {
	
	/**
	 * This is a constructor that takes 0 arguments.
	 */
	protected Collection(){
		
	}
	
	/**
	 * This method checks if the collection is empty or not.
	 * 
	 * @return True if the collection is empty, otherwise returns false.
	 */
	public boolean isEmpty() {
		return size() == 0;
	}
	
	/**
	 * This method gives the information about the size of collection.
	 * 
	 * @return Size of collection.
	 */
	public int size() {
		return 0;
	}
	
	/**
	 * This method adds the element into assigned collection
	 * 
	 * @param value Object that will be added into collection.
	 */
	public void add(Object value) {
		
	}
	
	/**
	 * This method checks if collection contains given object.
	 * 
	 * @param value Object that will be checked whether he is in collection or not.
	 * @return returns true if object is in collection , otherwise returns false.
	 */
	public boolean contains(Object value) {
		return false;
	}
	
	/**
	 * This method removes an given object from a collection.
	 * 
	 * @param value Object that will be removed from collection.
	 * @return returns true if the given object is removec from collection , otherwise returns false.
	 */
	public boolean remove(Object value) {
		return false;
	}
	
	/**
	 * This method converts collection to array of objects.
	 * 
	 * @return return array of objects given from the collection.
	 */
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * This method runs through every elements in collection and calls class Processor.
	 * 
	 * @param processor will be called with its methods on every element from collection.
	 */
	public void forEach(Processor processor) {
		
	}
	
	/**
	 * This method adds every element from given collection into assigned collection.
	 * 
	 * @param other Collection which elements will be added into this collection.
	 */
	public void addAll(Collection other) {
		class AddWithProcessor extends Processor {
			public void process(Object value) {
				add(value);
			}
		}
		AddWithProcessor processor = new AddWithProcessor();
		other.forEach(processor);
	}
	
	/**
	 * This method removes every element from this collection.
	 * 
	 */
	public void clear() {
		
	}

}
