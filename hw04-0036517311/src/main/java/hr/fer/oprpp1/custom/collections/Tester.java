package hr.fer.oprpp1.custom.collections;

/**
 * @author Tomislav Lovrencic
 *
 * This is an interface that is used to test something.
 * @param <T> Object of type T that will be tested  in collection
 */
public interface Tester<T> {
	
	/**
	 * This is a method used to test something on given object.
	 * @param obj
	 * @return 
	 */
	boolean test(Object obj);

}
