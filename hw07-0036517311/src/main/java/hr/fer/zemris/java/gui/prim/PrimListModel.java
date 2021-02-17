package hr.fer.zemris.java.gui.prim;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 * @author Tomislav Lovrencic
 * 
 * This class represents a model used for JList.
 */
public class PrimListModel implements ListModel<Integer>{
	
	/**
	 *  This is a list of ListDataListeners.
	 */
	private List<ListDataListener> listeners;
	
	/**
	 * This is a list of integers.
	 */
	private List<Integer> numbers;
	
	
	/**
	 *  This is a basic constructor.
	 */
	public PrimListModel() {
		this.listeners = new ArrayList<ListDataListener>();
		this.numbers = new ArrayList<Integer>();
		numbers.add(1);
	}

	@Override
	public int getSize() {
		return this.numbers.size();
	}

	@Override
	public Integer getElementAt(int index) {
		return this.numbers.get(index);
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		listeners.add(l);
		
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		listeners.remove(l);
		
	}
	
	/**
	 *  This is a method that calcucaltes the next prim number.
	 */
	public void next() {
		int br = numbers.get(numbers.size() - 1) + 1;
	
		while(!checkDiv(br)) {
			br++;
		}
		
		numbers.add(br);
		
		
		
	}
	
	/**
	 * This method is used to check whether the number has any dividers.
	 * @param a
	 * @return
	 */
	public boolean checkDiv(Integer a) {
		if(a == 2  || a == 3) return true;
		
		for(int i=2;i<=Math.sqrt(a);i++) {
			if(a % i == 0) return false;
		}
		return true;
	}

}
