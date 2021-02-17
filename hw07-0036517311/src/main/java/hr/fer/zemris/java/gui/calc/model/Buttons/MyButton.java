package hr.fer.zemris.java.gui.calc.model.Buttons;

import java.awt.Color;

import javax.swing.JButton;


/**
 * @author Tomislav Lovrencic
 *
 * This class represents a class for all used buttons with same background.
 */
public class MyButton extends JButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * This is a basic constructor.
	 * @param value
	 */
	public MyButton(String value) {
		super(value);
		this.setBackground(new Color(102, 153, 204));
	}

}
