package hr.fer.oprpp1.custom.collections;

/**
 * @author Tomislav Lovrencic
 *
 * This class represents Processor , whose job is to do some kind of action on a given object. Has only one empty method.
 * 
 */
public interface Processor<T> {

	/**
	 * This method performs some kind of operation or process on given object.
	 * @param value Object that will be processed.
	 */
	public void process(Object value);
}
