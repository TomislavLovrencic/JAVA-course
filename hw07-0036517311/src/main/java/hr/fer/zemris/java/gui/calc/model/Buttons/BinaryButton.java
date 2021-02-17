package hr.fer.zemris.java.gui.calc.model.Buttons;

import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.util.function.DoubleBinaryOperator;

import hr.fer.zemris.java.gui.calc.model.CalcModelImpl;
import hr.fer.zemris.java.gui.layouts.CalcLayoutException;

/**
 * @author Tomislav Lovrencic
 *
 * This class represents class for Binary operations.
 */
public class BinaryButton extends MyButton {

	/**
	 * text label for not inversed button.
	 */
	private String value1;
	/**
	 * text label for inversed button.
	 */
	private String value2;
	/**
	 * model that this button uses.
	 */
	private CalcModelImpl model;
	/**
	 * Operation for not inversed button.
	 */
	private DoubleBinaryOperator o1;
	/**
	 * Operation for  inversed button.
	 */
	private DoubleBinaryOperator o2;
	/**
	 * Action listener.
	 */
	private ActionListener action;
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * This is a constructor for button.
	 * @param value1
	 * @param value2
	 * @param model
	 * @param o1
	 * @param o2
	 */
	public BinaryButton(String value1,String value2,CalcModelImpl model,DoubleBinaryOperator o1,DoubleBinaryOperator o2) {
		super(value1);
		this.value1 = value1;
		this.value2 = value2;
		this.model = model;
		this.o1 = o1;
		this.o2 = o2;
		this.action = (a) -> {
			if(model.hasFrozenValue()) throw new CalcLayoutException();
			
			if(model.getPendingBinaryOperation() != null) {
				Double res = model.getPendingBinaryOperation().applyAsDouble(model.getActiveOperand(), model.getValue());
				model.setActiveOperand(res);
				model.setPendingBinaryOperation(o1);
				model.freezeValue(Double.toString(res));
				model.clear();
			}
			else {
				model.setPendingBinaryOperation(o1);
				model.setActiveOperand(model.getValue());
				model.freezeValue(model.toString());
				model.clear();
			}
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
			this.setText(value1);
		}
		this.action = (a) -> {
			if(model.getPendingBinaryOperation() != null) {
				Double res = model.getPendingBinaryOperation().applyAsDouble(model.getActiveOperand(), model.getValue());
				model.setActiveOperand(res);
				if(b) {
					model.setPendingBinaryOperation(o2);

				}
				else {
					model.setPendingBinaryOperation(o1);

				}
				model.freezeValue(Double.toString(res));
				model.clear();
			}
			else {
				if(b) {
					model.setPendingBinaryOperation(o2);

				}
				else {
					model.setPendingBinaryOperation(o1);

				}
				model.setActiveOperand(model.getValue());
				model.freezeValue(model.toString());
				model.clear();
			}
		};
		this.addActionListener(action);
	}

	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
    }
	
	
}
