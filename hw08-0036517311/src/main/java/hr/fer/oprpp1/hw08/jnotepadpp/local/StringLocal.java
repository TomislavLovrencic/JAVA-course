package hr.fer.oprpp1.hw08.jnotepadpp.local;

/**
 * @author Tomislav Lovrencic
 *
 *         This class represents localized string.
 *
 */
public class StringLocal {
	private String text;

	/**
	 * This is a basic constructor.
	 * 
	 * @param key
	 * @param lp
	 */
	public StringLocal(String key, ILocalizationProvider lp) {
		text = lp.getString(key);
		lp.addLocalizationListener(new ILocalizationListener() {
			@Override
			public void localizationChanged() {
				text = lp.getString(key);
			}
		});
	}

	/**
	 * This method is used to get localized string.
	 * 
	 * @return
	 */
	public String getText() {
		return this.text;
	}

}
