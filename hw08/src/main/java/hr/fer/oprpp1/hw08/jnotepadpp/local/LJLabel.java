package hr.fer.oprpp1.hw08.jnotepadpp.local;

import javax.swing.JLabel;

/**
 * @author Tomislav Lovrencic
 *
 * This class represents localized JLabel.
 *
 */
public class LJLabel extends JLabel{

	private static final long serialVersionUID = 1L;
	private ILocalizationProvider p;
	
	/**
	 * This i a basic constructor.
	 * @param key
	 * @param lp
	 */
	public LJLabel(String key, ILocalizationProvider lp) {
		this.p = lp;
		this.setText(p.getString(key));
		
		lp.addLocalizationListener(new ILocalizationListener() {
			@Override
			public void localizationChanged() {
				LJLabel.this.setText(p.getString(key));
			}
		});
	}
	

}
