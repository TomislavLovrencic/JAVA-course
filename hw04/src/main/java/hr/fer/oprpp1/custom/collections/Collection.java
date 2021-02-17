package hr.fer.oprpp1.custom.collections;


/**
 * @author Tomislav Lovrencic
 * 
 * This class represents Collection. It is used as a class that other classes implement and use its methods.
 *
 * @param <T> Object of type T that will be stored in collection
 */

public interface Collection<T> {
	

	
	/**
	 * This method checks if the collection is empty or not.
	 * 
	 * @return True if the collection is empty, otherwise returns false.
	 */
	default boolean isEmpty() {
		return size() == 0;
	}
	
	/**
	 * This method gives the information about the size of collection.
	 * 
	 * @return Size of collection.
	 */
	int size();
	
	/**
	 * This method adds the element into assigned collection
	 * 
	 * @param value of type T that will be added into collection.
	 */
	void add(T value);
	
	/**
	 * This method checks if collection contains given object.
	 * 
	 * @param value Object that will be checked whether he is in collection or not.
	 * @return returns true if object is in collection , otherwise returns false.
	 */
	 boolean contains(Object value);
	/**
	 * This method removes an given object from a collection.
	 * 
	 * @param value Object that will be removed from collection.
	 * @return returns true if the given object is removec from collection , otherwise returns false.
	 */
	 boolean remove(Object value);
	
	/**
	 * This method converts collection to array of objects.
	 * 
	 * @return return array of objects given from the collection.
	 */
	 Object[] toArray();
	
	/**
	 * This method runs through every elements in collection and calls class Processor.
	 * 
	 * @param processor will be called with its methods on every element from collection.
	 */
	 default public void forEach(Processor<? super T> processor) {
		ElementsGetter<T> getter = this.createElementsGetter();
		for(int i=0;i<this.size();i++) {
			processor.process(getter.getNextElement());
		}
			
		}
	
	/**
	 * This method adds every element from given collection into assigned collection.
	 * 
	 * @param other Collection which elements will be added into this collection.
	 */
	default void addAll(Collection<? extends T> other) {
		class AddWithProcessor implements Processor<T> {
			@SuppressWarnings("unchecked")
			public void process(Object value) {
				add((T) value);
			}
		}
		AddWithProcessor processor = new AddWithProcessor();
		other.forEach(processor);
	}
	
	/**
	 * This method removes every element from this collection.
	 * 
	 */
	 void clear();
	 
	 
	 /**
	  * Thus method creates a new instance of ElementsGetter.
	 * @return ElementsGetter
	 */
	ElementsGetter<T> createElementsGetter();
	 
	
	 /**
	  * This function is used to copy all elements that satisfy tester from collection col to this collection ,
	  * it uses Tester to determine which one of those elements will go into the collection.
	 * @param col
	 * @param tester
	 */
	default public void addAllSatisfying(Collection<? extends T> col, Tester<? super T> tester) {
		ElementsGetter<? extends T> getter = col.createElementsGetter();
		
		while(getter.hasNextElement()) {
			T obj = getter.getNextElement();
			if(tester.test(obj)) {
				this.add(obj);
			}
		}
	}


	 
	 
	 
	 

}
