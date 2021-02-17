package hr.fer.oprpp1.hw08.jnotepadpp.local;

/**
 * @author Tomislav Lovrencic
 *
 * This interface is used as a listener for {@link ILocalizationProvider}.
 */
public interface ILocalizationListener {

	/**
	 *  This method is used to preform when the localization is changed.
	 */
	public void localizationChanged();
}
