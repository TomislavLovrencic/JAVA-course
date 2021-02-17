package hr.fer.zemris.java.gui.calc.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleBinaryOperator;

/**
 * @author Tomislav Lovrencic
 * 
 * This class represents calculator model implementation.
 */
public class CalcModelImpl implements CalcModel{
	
	/**
	 *  This variable checks if the model is editable.
	 */
	private boolean isEditable;
	
	/**
	 *  This variable checks if the number is positive.
	 */
	private boolean isPositive;
	
	/**
	 *  This is a representation of number in string.
	 */
	private String numberInString;
	/**
	 *  This is current number in model.
	 */
	private double decimalValueOfNumber;
	/**
	 * This is a frozen value.
	 */
	private String frozenValue;
	/**
	 * This is a current active operand.
	 */
	private Double activeOperand;
	/**
	 *  This is a current active operation.
	 */
	private DoubleBinaryOperator operation;
	/**
	 *  This is a list of {@link CalcValueListener}.
	 */
	private List<CalcValueListener> lista;
	/**
	 * This is a variable to check if the inverse is turned on.
	 */
	private boolean checked = false;
	
	/**
	 *  This is a basic constructor.
	 */
	public CalcModelImpl() {
		this.isPositive = true;
		this.frozenValue = null;
		this.numberInString = "";
		this.decimalValueOfNumber = 0;
		this.isEditable = true;
		this.activeOperand = null;
		this.operation = null;
		this.lista = new ArrayList<>();
		
		
	}
	
	
	@Override
	public void addCalcValueListener(CalcValueListener l) {
		this.lista.add(l);
		
	}

	@Override
	public void removeCalcValueListener(CalcValueListener l) {
		this.lista.remove(l);
	}
	
	/**
	 *  This method is used to notify all listeners.
	 */
	private void notifyListeners() {
		for(CalcValueListener elem : lista) {
			elem.valueChanged(this);
		}
	}
	
	/**
	 *  This method is use to inverse unary operators.
	 */
	public void checkBox() {
		if(checked) {
			checked = false;
		}
		else {
			checked = true;
		}
	
		notifyListeners();
	}
	
	/** This method is used to check whether the checkbox is clicked for inverse.
	 * @return
	 */
	public boolean getCheckedBox() {
		return checked;
	}
	
	@Override
	public double getValue() {
		return isPositive ? decimalValueOfNumber : -decimalValueOfNumber;
	}

	@Override
	public void setValue(double value) {
		this.decimalValueOfNumber = Math.abs(value);
		numberInString = Double.toString(Math.abs(value));
		
		if(value > 0) {
			isPositive = true;
		}
		else {
			isPositive = false;
		}
		
		frozenValue = null;
		isEditable = false;
		notifyListeners();
		
	}

	@Override
	public boolean isEditable() {
		return isEditable;
	}

	@Override
	public void clear() {
		numberInString = "";
		decimalValueOfNumber = 0;
		isPositive = true;
		isEditable = true;
		notifyListeners();
		
	}

	@Override
	public void clearAll() {
		clear();
		activeOperand = null;
		operation = null;
		frozenValue = null;
		notifyListeners();
		
	}

	@Override
	public void swapSign() throws CalculatorInputException {
		if(isEditable == false) throw new CalculatorInputException();
		
		if(isPositive) {
			isPositive = false;
		}
		else {
			isPositive = true;
		}
		
		
		frozenValue = null;
		notifyListeners();
		
	}

	@Override
	public void insertDecimalPoint() throws CalculatorInputException {
		if(isEditable == false || numberInString.length() == 0) throw new CalculatorInputException();
		
		for(int i=0;i<numberInString.length();i++) {
			if(numberInString.charAt(i) == '.') {
				throw new CalculatorInputException();
			}
		}
		numberInString+=".";
		frozenValue = null;
		notifyListeners();
		
	}

	@Override
	public void insertDigit(int digit) throws CalculatorInputException, IllegalArgumentException {
		if(isEditable == false) {
			throw new CalculatorInputException();
		}
		
		if(numberInString.length() == 308) {
			throw new CalculatorInputException();
		}
		
		try {
			decimalValueOfNumber = Double.parseDouble(numberInString+digit);
		}catch(IllegalArgumentException e) {
			throw new CalculatorInputException();
		}
		
		if(numberInString.equals("0") && digit != 0) {
			numberInString = Integer.toString(digit);
		}
		else {
			if(!(numberInString.equals("0") && digit == 0)) {
				numberInString+=digit;
			}
		}
		
	
		frozenValue = null;
		notifyListeners();
	}

	@Override
	public boolean isActiveOperandSet() {
		if(activeOperand == null) return false;
		return true;
	}

	@Override
	public double getActiveOperand() throws IllegalStateException {
		if(!isActiveOperandSet()) throw new IllegalStateException();
		return this.activeOperand;
		
	}

	@Override
	public void setActiveOperand(double activeOperand) {
		this.activeOperand = activeOperand;
		notifyListeners();
	}

	@Override
	public void clearActiveOperand() {
		this.activeOperand = null;
		notifyListeners();
	}

	@Override
	public DoubleBinaryOperator getPendingBinaryOperation() {
		return this.operation;
	}

	@Override
	public void setPendingBinaryOperation(DoubleBinaryOperator op) {
		operation = op;
		notifyListeners();
	}

	@Override
	public String toString() {
		if(frozenValue != null) {
			return frozenValue;
		}
		if(numberInString.length() == 0) {
			if(isPositive) {
				return "0";
			}
			return "-0";
		}
		
		return isPositive ? numberInString : "-"+numberInString;
		
	}
	
	@Override
	public void freezeValue(String value) {
		frozenValue = value;
		
	}


	@Override
	public boolean hasFrozenValue() {
		return frozenValue != null;
	}

}
