package hr.fer.oprpp1.hw08.jnotepadpp.local;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tomislav Lovrencic
 *
 * This is an abstract class that implements {@link ILocalizationProvider}. It has its own listeners 
 * and its able to notify listeners.
 *
 */
public abstract class AbstractLocalizationProvider implements ILocalizationProvider{
	
	/**
	 *  list of listeners.
	 */
	private List<ILocalizationListener> listeners = new ArrayList<>();
	
	/**
	 *  basic constructor.
	 */
	public AbstractLocalizationProvider() {
	}
	
	/**
	 * This method is used to add {@link ILocalizationListener}.
	 */
	public void addLocalizationListener(ILocalizationListener listener) {
		this.listeners.add(listener);
	}
	
	/**
	 * This method is used to remove {@link ILocalizationListener}.
	 */
	public void removeLocalizationListener(ILocalizationListener listener) {
		this.listeners.remove(listener);
	}
	
	/**
	 *  This method is used to notify all listeners.
	 */
	public void fire() {
		for(ILocalizationListener l : listeners) {
			l.localizationChanged();
		}
	}
	
}
