package hr.fer.oprpp1.hw08.jnotepadpp.local;

import javax.swing.JMenu;

/**
 * @author Tomislav Lovrencic
 *
 * This class represents localized JMenu.
 *
 */
public class LJMenu extends JMenu{

	private static final long serialVersionUID = 1L;
	private ILocalizationProvider p;
	
	/**
	 * This is a basic constructor.
	 * @param key
	 * @param lp
	 */
	public LJMenu(String key, ILocalizationProvider lp) {
		this.p = lp;
		this.setText(p.getString(key));
		
		lp.addLocalizationListener(new ILocalizationListener() {
			@Override
			public void localizationChanged() {
				LJMenu.this.setText(p.getString(key));
			}
		});
	}
	

}
