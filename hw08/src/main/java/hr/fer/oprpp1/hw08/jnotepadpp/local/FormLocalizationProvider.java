package hr.fer.oprpp1.hw08.jnotepadpp.local;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

/**
 * @author Tomislav Lovrencic
 *
 * This class is used as a proxy to {@link LocalizationProvider}.
 *
 */
public class FormLocalizationProvider extends LocalizationProviderBridge {

	/**
	 * This is a basic constructor.
	 * @param p
	 * @param frame
	 */
	public FormLocalizationProvider(ILocalizationProvider p,JFrame frame) {
		super(p);
		WindowListener wind = (WindowListener) new WindowAdapter() {

			@Override
			public void windowOpened(WindowEvent e) {
				connect();	
			}

			@Override
			public void windowClosed(WindowEvent e) {
				disconnect();
			}
		};
		
		frame.addWindowListener(wind);
	}

}
