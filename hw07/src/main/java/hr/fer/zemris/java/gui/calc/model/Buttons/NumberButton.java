package hr.fer.zemris.java.gui.calc.model.Buttons;


import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import hr.fer.zemris.java.gui.calc.model.CalcModelImpl;

/**
 * @author Tomislav Lovrencic
 *
 * This class represents buttons with numbers.
 */
public class NumberButton extends MyButton{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



    /**
     * This is a basic constructor.
     * @param value
     * @param m
     */
    public NumberButton(String value,CalcModelImpl m) {
        super(value);
        this.setFont(this.getFont().deriveFont(30f));
        
        this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				m.insertDigit(Integer.parseInt(value));	
			}
		});
    }
    
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
    }

	

}
