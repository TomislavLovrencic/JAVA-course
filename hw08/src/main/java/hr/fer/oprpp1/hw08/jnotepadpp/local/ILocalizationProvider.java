package hr.fer.oprpp1.hw08.jnotepadpp.local;

/**
 * @author Tomislav Lovrencic
 *
 * This is an interface with provider functionalities.
 * 
 */
public interface ILocalizationProvider {

	/**
	 * This is a method that add {@link ILocalizationListener}.
	 * @param listener
	 */
	public void addLocalizationListener(ILocalizationListener listener);
	
	/**
	 * This method is used to get a value using given string as a key.
	 * @param a
	 * @return
	 */
	public String getString(String a);
	
	/**
	 * This method is used to remove {@link ILocalizationListener}.
	 * @param listener
	 */
	public void removeLocalizationListener(ILocalizationListener listener);
	
}
