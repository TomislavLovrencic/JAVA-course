package hr.fer.oprpp1.custom.collections;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.Objects;



/**
 * @author Tomislav Lovrencic
 *
 * This class represents a linked list collection. It stores object into an linked list using private class ListNode.
 *
 */
public class LinkedListIndexedCollection implements List {
	
	
	private long modificationCount = 0L;
	/**
	 * Current size of a list.
	 */
	private static int size;
	
	/**
	 * Reference to first ListNode.
	 */
	ListNode first;
	
	/**
	 * Reference to last ListNode.
	 */
	ListNode last;
	
	/**
	 * @author Tomislav Lovrencic
	 *
	 * This class represents one copy of Node that is a  part of a Linked list collection.
	 */
	private static class ListNode{
		
		/**
		 * Reference to previous ListNode.
		 */
		ListNode previous;
		
		/**
		 * Reference to next ListNode.
		 */
		ListNode next;
	
		/**
		 * Object stored in one ListNode.
		 */
		Object value;
		
		
		/**
		 * This is a constructor that initializes one Node with one argument object, setting references to next and previous
		 * node as null values.
		 * @param value Object whose value will be stored in Nodes value.
		 */
		ListNode(Object value){
			Objects.requireNonNull(value,"Value cant be null!");
			
			this.previous = null;
			this.next = null;
			this.value = value;
		}
	}
	
	/**
	 * This is a constructor which initializes an linked list , with first and last set to null. It takes no arguments.
	 */
	public LinkedListIndexedCollection() {
		first = null;
		last = null;
		size = 0;
		
	}
	
	/**
	 * This is a constructor which initializes a liked list with argument collection.
	 * @param other Collection whose elements will be added to this linked list.
	 */
	public LinkedListIndexedCollection(Collection other) {
		Objects.requireNonNull(other,"Collection must not be null!");
		
		size = 0;
		addAll(other);
	}
	
	
	/**
	 * This method removes an element from a linked list on a given position.
	 * @param index shows on which position to remove an element.
	 */
	public void remove(int index) {
		if(index < 0 || index > size-1) throw new IndexOutOfBoundsException();
		
		ListNode curr = first;
		
		if(first == null) return;

		int i = 0;
		
		if(index == 0) {
			first = curr.next;
			curr = null;
			
		}
		else {
			while(i < index) {
				curr = curr.next;
				i++;
			}
			
			if(curr.next == null) {
				last = curr.previous;
				last.next = null;
			}
			else {
				 curr.previous.next = curr.next;
	             curr.next.previous = curr.previous;
			}	
		}
		
		modificationCount++;
		size--;
	}
	
	
	/**
	 * This method is used to get an element from a linked list on a given position.
	 * @param index given position.
	 * @return Returns the object on a given position if the position is in valid boundaries.
	 */
	public Object get(int index) {
		if(index < 0 || index > size-1) throw new IndexOutOfBoundsException();
		int i = 0;
		ListNode curr;
		if(index < size/2) {
			curr = first;
			while (i < index) {
				curr = curr.next;
				i++;
			}
		}
		else {
			curr = last;
			i = index;
			while (i < size-1) {
				curr = curr.previous;
				i++;
			}
		}
		
		
		return curr.value;
		
	}
	
	/**
	 * This method is used to insert an object into a linked list on a given position.
	 * @param value Object that will be stored in a linked list.
	 * @param position Position on which the element will be stored.
	 */
	public void insert(Object value , int position) {
		Objects.requireNonNull(value);
		
		if(position < 0 || position > size) throw new IndexOutOfBoundsException();
		
		int index = 0;
		ListNode curr = first;
		ListNode prev = first;
		ListNode temp = new ListNode(value);
		
		
		if(position == 0) {
			temp.next = first;
			first.previous = temp;
			first = temp;
		}
		
		if(position == size) {
			temp.previous = last;
			last.next = temp;
			last = temp;
		}
		
		if(position != 0 && position != size) {
			while(index < position) {
				curr = curr.next;
				prev = curr.previous;
				index++;
			}
			
			prev.next = temp;
			temp.previous = prev;
			temp.next = curr;
			curr.previous = temp;
		}
				
		modificationCount++;
		size++;
		
	}
	
	/**
	 * This method is used to get a index of a given object in this linked list.
	 * @param value Object whose index we are searching for.
	 * @return return the index of an object in this list if found , -1 otherwise.
	 */
	public int indexOf(Object value) {

		ListNode curr = first;
		
		int i = 0;
		while (curr != null) {
			if(curr.value.equals(value)) {
				return i;
			}
			i++;
			curr = curr.next;
		}
		
		return -1;
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
	public void add(Object value) {
		Objects.requireNonNull(value,"Value cant be null!");
		
		ListNode temp = new ListNode(value);
		
		
		if(first == null) {
			first = temp;
			last = temp;
			first.previous = null;
			last.next = null;
		}
		else {
			temp.previous = last;
			last.next = temp;
			last = temp;
			last.next = null;
		}
		
		modificationCount++;
		size++;
	}
		

	@Override
	public boolean contains(Object value) {
		boolean found = false;
		
		ListNode curr = first;
		
		if(curr == null) return false;
		
		while (curr.next != null) {
			if(curr.value.equals(value)) found = true;
			curr = curr.next;
		}
		
		return found;
		
	}

	@Override
	public boolean remove(Object value) {
		Objects.requireNonNull(value,"Value cant be null!");

		int index = indexOf(value);
		
		remove(index);
		
		modificationCount++;
		return index != -1;
		
	}

	@Override
	public Object[] toArray() {
		Object[] array = new Object[size];
		int i = 0;
		ListNode curr =  first;
		
		if(curr == null) return array;
		
		while(curr.next != null) {
			array[i] = curr.value;
			curr = curr.next;
			i++;
		}
		
		array[i] = curr.value;
		
		return array;
	}


	@Override
	public void clear() {
		ListNode curr = first;
		if(curr == null) return;
		
		curr.previous = null;
		
		while(curr.next != null) {
			curr = curr.next;
			curr.previous = null;
		}
		
		first = null;
		last = null;
		size = 0;
		modificationCount++;
	}
	
	private static class getElements implements ElementsGetter  {
		
		private LinkedListIndexedCollection list;
		private ListNode temp;
		private long savedModificationCount;
		
		public getElements(LinkedListIndexedCollection other) {
			list = other;
			temp = other.first;
			savedModificationCount = other.modificationCount;
		}
		
		
		public boolean hasNextElement() {
			if(savedModificationCount != list.modificationCount) {
				throw new ConcurrentModificationException();
			}
			return temp != null;
			
		}
		public Object getNextElement() {
			
			if(savedModificationCount != list.modificationCount) {
				throw new ConcurrentModificationException();
			}
			if(temp == null) {
				throw new NoSuchElementException();
			}
			Object obj = temp.value;
			temp = temp.next;
			return obj;
		}	
		
		@Override
		public void processRemaining(Processor p) {
			while(temp.next != null) {
				p.process(temp.value);
				temp = temp.next;
			}
			p.process(temp.value);
			
		}
	}
	
	@Override
	public ElementsGetter createElementsGetter() {
		return new getElements(this);
	}
	

}
