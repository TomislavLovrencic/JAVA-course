package hr.fer.oprpp1.hw08.jnotepadpp.local;


/**
 * @author Tomislav Lovrencic
 *
 * This class is used as a bridge for {@link LocalizationProvider}. Its used to store all the references and listeners
 * in one place.
 */
public class LocalizationProviderBridge extends AbstractLocalizationProvider{

	/**
	 * checks if object is connected.
	 */
	private boolean connected;
	/**
	 *  provider.
	 */
	private ILocalizationProvider provider;
	/**
	 *  listener.
	 */
	private ILocalizationListener listener;

	
	/**
	 * This is a basic constructor.
	 * @param p
	 */
	public LocalizationProviderBridge(ILocalizationProvider p) {
		this.provider = p;
		this.listener = new ILocalizationListener() {
			@Override
			public void localizationChanged() {
				fire();
			}
		};
	}
	
	/**
	 * This method is used to disconnect a listener.
	 */
	public void disconnect() {
		if(connected) {
			provider.removeLocalizationListener(listener);
		}
		
	}
	
	/**
	 *  This method is used to connect listener.
	 */
	public void connect() {
		if(!connected) {
			provider.addLocalizationListener(listener);
		}
	}
	
	/**
	 * This method is used to get a localized string with given string key.
	 */
	public String getString(String a) {
		return provider.getString(a);
	}
}
