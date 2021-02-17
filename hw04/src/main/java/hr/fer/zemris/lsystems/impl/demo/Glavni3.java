package hr.fer.zemris.lsystems.impl.demo;

import hr.fer.zemris.lsystems.gui.LSystemViewer;
import hr.fer.zemris.lsystems.impl.LSystemBuilderImpl;



/**
 * @author Tomislav Lovrencic
 * 
 * This class is used to test creating all fractals.
 *
 */
public class Glavni3 {

	/**
	 * This is a main method.
	 * @param args
	 */
	public static void main(String[] args) {
		
		LSystemViewer.showLSystem(LSystemBuilderImpl::new);
	}
}
