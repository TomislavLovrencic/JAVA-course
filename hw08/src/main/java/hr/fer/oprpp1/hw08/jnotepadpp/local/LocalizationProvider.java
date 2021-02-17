package hr.fer.oprpp1.hw08.jnotepadpp.local;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Tomislav Lovrencic
 *
 * This class is used as a language provider. It is a singleton class.
 */
public class LocalizationProvider extends AbstractLocalizationProvider{
	/**
	 * current active language.
	 */
	private String language;
	
	/**
	 * resource bundle.
	 */
	private ResourceBundle bundle;
	/**
	 * Single instance of {@link LocalizationProvider}.
	 */
	private static LocalizationProvider provider = new LocalizationProvider();
	
	
	/**
	 *  This is an private empty constructor.
	 */
	private LocalizationProvider() {
		
	}
	
	/**
	 * This method is used to get a single {@link LocalizationProvider} instance.
	 * @return
	 */
	public static LocalizationProvider getInstance() {
		return provider;
	}
	
	/**
	 * This method is used to change a language.
	 * @param l
	 */
	public void setLanguage(String l) {
		this.language = l;
		Locale locale = Locale.forLanguageTag(this.language);
		this.bundle = ResourceBundle.getBundle("hr.fer.oprpp1.hw08.jnotepadpp.local.prijevodi",locale);
		fire();
		
	}
	
	/**
	 * This method is used to get a localized string value.
	 */
	public String getString(String a) {
		return bundle.getString(a);
	}
	
	/**
	 * This method is used to get a current language set.
	 * @return
	 */
	public String getLanguage() {
		return this.language;
	}
}
