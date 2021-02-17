package hr.fer.zemris.lsystems.impl.demo;

import hr.fer.zemris.lsystems.LSystem;
import hr.fer.zemris.lsystems.LSystemBuilderProvider;
import hr.fer.zemris.lsystems.gui.LSystemViewer;
import hr.fer.zemris.lsystems.impl.LSystemBuilderImpl;


/**
 * @author Tomislav Lovrencic
 *
 * This is a class that is used to test creating a koch2 fractal.
 *
 */
public class Glavni2 {

	/**
	 * This is a main method.
	 * @param args
	 */
	public static void main(String[] args) {
		
		LSystemViewer.showLSystem(createKochCurve2(LSystemBuilderImpl::new));
	}
	
	/**
	 * This method is used to generate a koch2 fractal.
	 * @param provider
	 * @return
	 */
	private static LSystem createKochCurve2(LSystemBuilderProvider provider) {
		String[] data = new String[] { 
				"origin                 0.05 0.4",
				"angle                  0",
				"unitLength             0.9",
				"unitLengthDegreeScaler 1.0 / 3.0",
				"",
				"command F draw 1",
				"command + rotate 60",
				"command - rotate -60",
				"",
				"axiom F",
				"",
				"production F F+F--F+F" 
				};
	return provider.createLSystemBuilder().configureFromText(data).build(); }
}
