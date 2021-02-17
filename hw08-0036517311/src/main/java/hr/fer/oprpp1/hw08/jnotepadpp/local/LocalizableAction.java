package hr.fer.oprpp1.hw08.jnotepadpp.local;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * @author Tomislav Lovrencic
 *
 * This class represents an action that is localized depending the
 * language set.
 * 
 */
public class LocalizableAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	/**
	 * This is a basic constructor.
	 * 
	 * @param key
	 * @param lp
	 */
	public LocalizableAction(String key, ILocalizationProvider lp) {
		this.putValue(NAME, lp.getString(key));

		lp.addLocalizationListener(new ILocalizationListener() {
			@Override
			public void localizationChanged() {
				LocalizableAction.this.putValue(NAME, lp.getString(key));
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
