package hr.fer.zemris.java.gui.calc.model.Buttons;

import java.awt.event.ActionListener;
import java.util.function.UnaryOperator;

import hr.fer.zemris.java.gui.calc.model.CalcModelImpl;

/**
 * @author Tomislav Lovrencic
 * 
 * This class represents unary operations.
 */
public class UnaryButton extends MyButton {
	
	/**
	 * This is a text for button when not inverted.
	 */
	private String value;
	
	/**
	 * This is a text for button when  inverted.
	 */
	private String value2;
	
	/**
	 * This is a operation for button when not inverted.
	 */
	private UnaryOperator<Double> o1;
	
	/**
	 * This is a operation for button when  inverted.
	 */
	private UnaryOperator<Double> o2;
	
	/**
	 * This is a model that this button uses.
	 */
	private CalcModelImpl model;
	
	/**
	 * 
	 */
	private ActionListener action;
	private static final long serialVersionUID = 1L;
	

	/**
	 * This is a basic constructor.
	 * @param value
	 * @param value2
	 * @param model
	 * @param o1
	 * @param o2
	 */
	public UnaryButton(String value,String value2 , CalcModelImpl model,UnaryOperator<Double> o1,UnaryOperator<Double> o2) {
		super(value);
		this.value = value;
		this.value2 = value2;
		this.model = model;
		this.o1 = o1;
		this.o2 = o2;
		this.action = (a) -> {
			Double res = o1.apply(model.getValue());
			model.setValue(res);
		};
		this.addActionListener(action);

	}
	
	/**
	 * This method is used to create an action listener depending whether inverse is on.
	 * @param b
	 */
	public void actionListener(boolean b) {
		this.removeActionListener(action);
		if(b) {
			this.setText(value2);
		}
		else {
			this.setText(value);
		}
		this.action = (a) -> {
			Double res = 0.0;
			if(b) {
				res = o2.apply(model.getValue());
			}
			else {
				res = o1.apply(model.getValue());
			}
			model.setValue(res);
		};
		this.addActionListener(action);
	}
}
