package hr.fer.oprpp1.custom.collections;


/**
 * @author Tomislav Lovrencic
 *
 * This class represents a stack filled with objects. It uses array to store elements. This 
 * class is a "delegator" that uses ArrayIndexCollections methods wraped in its own methods with new names.
 */
public class ObjectStack {
	
	/**
	 *  Array representing a stack.
	 */
	ArrayIndexedCollection array = new ArrayIndexedCollection();
	
	/**
	 * This method checks if the stack is empty.
	 * @return Returns true if the stack is empty, false otherwise.
	 */
	public boolean isEmpty() {
		return array.isEmpty();
	}
	
	/**
	 * This method gives us the information about size of a stack.
	 * @return number of elements in  a stack.
	 */
	public int size() {
		return array.size();
	}
	
	/**
	 * This method adds a given object into stack.
	 * @param value Object that will be added into stack on last position.
	 */
	public void push(Object value) {
		array.add(value);
	}
	
	/**
	 * This method removes and element from a last position and returns it.
	 * @return Object that is removed from a stack.
	 * @throws new {@link EmptyStackException}
	 */
	public Object pop() {
		if(isEmpty() == true) throw new EmptyStackException("Stack is empty!");
		Object obj = array.get(size() -1);
		array.remove(array.get(size()-1));
		
		return obj;
	}
	
	/**
	 * This method gives us last Object on the stack , without removing them.
	 * @return Object that is on biggest index.
	 */
	public Object peek() {
		return array.get(size() -1);
	}
	
	/**
	 * This method removes all elements from stack.
	 */
	public void clear() {
		 array.clear();
	}
	
}
