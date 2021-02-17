package hr.fer.zemris.java.gui.calc.model;

import java.awt.Color;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

import hr.fer.zemris.java.gui.calc.model.Buttons.BinaryButton;
import hr.fer.zemris.java.gui.calc.model.Buttons.MyButton;
import hr.fer.zemris.java.gui.calc.model.Buttons.NumberButton;
import hr.fer.zemris.java.gui.calc.model.Buttons.UnaryButton;
import hr.fer.zemris.java.gui.layouts.CalcLayout;

/**
 * @author Tomislav Lovrencic
 *
 * This class represents a calculator.
 */
public class Calculator extends JFrame {
	
	
	/**
	 *  This is a model used for this calculator.
	 */
	private CalcModelImpl calculatorModel = new CalcModelImpl();
	
	/**
	 * This is a stack of Double values. 
	 */
	private Stack<Double> stack = new Stack<Double>();

	private static final long serialVersionUID = 1L;
		
	/**
	 *  This is a list of unary buttons.
	 */
	private List<UnaryButton> invButtons = new ArrayList<UnaryButton>();
	
	
	
	
	/**
	 *  This is a basic constructor.
	 */
	public Calculator() {
		super();
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Calculator");
		setLocation(20, 20);
		initGUI();
		pack();
	}
	
	
	/**
	 *  This method is used to initialize gui components.
	 */
	private void initGUI() {
		Container cp = getContentPane();
		JPanel panel = new JPanel(new CalcLayout(3));
		
		//label ulaz
		JLabel ulaz = new JLabel(calculatorModel.toString(),SwingConstants.RIGHT);
		ulaz.setFont(ulaz.getFont().deriveFont(30f));
		ulaz.setBackground(Color.yellow);
		ulaz.setOpaque(true);
		ulaz.setBorder(LineBorder.createBlackLineBorder());
		calculatorModel.addCalcValueListener((m) -> ulaz.setText(m.toString()));
		panel.add(ulaz, "1,1");
		
			
		//checkbox
		JCheckBox box = new JCheckBox("Inv");
		box.addActionListener((a) -> calculatorModel.checkBox());
		panel.add(box,"5,7");
		
		
		//UnaryOperators
		UnaryButton invers = new UnaryButton("1/x",null,calculatorModel,(a) -> 1 / a, null);
		panel.add(invers,"2,1");
		
		UnaryButton log = new UnaryButton("log","10^x",calculatorModel,(a) -> Math.log10(a),(a) -> Math.pow(a, 10));
		panel.add(log,"3,1"); invButtons.add(log);
		
		UnaryButton ln = new UnaryButton("ln","e^x",calculatorModel,(a) -> Math.log(a),(a) -> Math.pow(Math.E, a));
		panel.add(ln,"4,1"); invButtons.add(ln);
		
		UnaryButton sin = new UnaryButton("sin","arcsin",calculatorModel,(a) -> Math.sin(a),(a) -> Math.asin(a));
		panel.add(sin,"2,2"); invButtons.add(sin);
		
		UnaryButton cos = new UnaryButton("cos","arccos",calculatorModel,(a) -> Math.cos(a), (a) -> Math.acos(a));
		panel.add(cos,"3,2"); invButtons.add(cos);
		
		UnaryButton tan = new UnaryButton("tan","arctan",calculatorModel,(a) -> Math.tan(a),(a) -> Math.atan(a));
		panel.add(tan,"4,2"); invButtons.add(tan);
		
		UnaryButton ctg = new UnaryButton("ctg","arcctg",calculatorModel,(a) -> 1/Math.tan(a), (a) -> Math.PI/2 - Math.atan(a));
		panel.add(ctg,"5,2"); invButtons.add(ctg);
			
		//Equal
		MyButton equal = new MyButton("=");
		equal.addActionListener((a) -> {
			calculatorModel.setValue(calculatorModel.getPendingBinaryOperation()
					.applyAsDouble(calculatorModel.getActiveOperand(), calculatorModel.getValue()));
			calculatorModel.setPendingBinaryOperation(null);
		});
		
		
		panel.add(equal,"1,6");
		
		//Swap
		MyButton swap = new MyButton("+/-");
		swap.addActionListener((a) -> calculatorModel.swapSign());
		panel.add(swap,"5,4");
		
		//dot
		MyButton dot = new MyButton(".");
		dot.addActionListener((a) -> calculatorModel.insertDecimalPoint());
		panel.add(dot,"5,5");
		
		//clear
		MyButton clear = new MyButton("clr");
		clear.addActionListener((a) -> calculatorModel.clear());
		panel.add(clear,"1,7");
		
		//reset
		MyButton reset = new MyButton("reset");
		reset.addActionListener((a) -> calculatorModel.clearAll());
		panel.add(reset,"2,7");
		
		//Stack operators
		MyButton push = new MyButton("push");
		push.addActionListener((a) -> {
			stack.push(calculatorModel.getValue());
		});
		panel.add(push,"3,7");
		
		MyButton pop = new MyButton("pop");
		pop.addActionListener((a) -> {
			if(stack.isEmpty()) {
				calculatorModel.clearAll();
				ulaz.setText("Stack is empty!");
				
			}
			else {
				calculatorModel.clear();
				calculatorModel.setValue(stack.pop());
			}
		});
		
		panel.add(pop,"4,7");

		//Binary Operators
		BinaryButton potent = new BinaryButton("x^n","x^(1/n)",calculatorModel,(a,b) -> Math.pow(a, b),(a,b) -> Math.pow(a, 1/b));
		panel.add(potent,"5,1");
		BinaryButton plus = new BinaryButton("+",null,calculatorModel,(a,b) -> a + b,null);
		panel.add(plus,"5,6");
		BinaryButton minus = new BinaryButton("-",null,calculatorModel, (a,b) -> a - b,null);
		panel.add(minus,"4,6");
		BinaryButton mul = new BinaryButton("*",null,calculatorModel,(a,b) -> a*b,null);
		panel.add(mul,"3,6");
		BinaryButton div = new BinaryButton("/",null,calculatorModel,(a,b) -> a / b,null);
		panel.add(div,"2,6");
		
		calculatorModel.addCalcValueListener((a) -> {
			for(UnaryButton elem : invButtons) {
				elem.actionListener(calculatorModel.getCheckedBox());
			}
			potent.actionListener(calculatorModel.getCheckedBox());
		});
		
		String[] numberPositions = new String[] {"5,3","4,3","4,4","4,5","3,3","3,4","3,5","2,3","2,4","2,5"}; 
		for(int i=0;i<10;i++) {
			NumberButton button = new NumberButton(Integer.toString(i),calculatorModel);
			panel.add(button ,numberPositions[i]);
		}
		
		cp.add(panel);
		
	}
	
	/**
	 * This is a main method.
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new Calculator().setVisible(true);
			}
		});
	}

}
