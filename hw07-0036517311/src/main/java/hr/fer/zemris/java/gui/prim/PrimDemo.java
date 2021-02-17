package hr.fer.zemris.java.gui.prim;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;


/**
 * @author Tomislav Lovrencic
 * 
 * This class represents a demo used for drawing components on screen and testing its functions. 
 */
public class PrimDemo extends JFrame {

	
	private static final long serialVersionUID = 1L;


	/**
	 * This is a basic constructor.
	 */
	public PrimDemo() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("PrimDemo");
		setSize(300,300);
		initGUI();
	}
	
	
	/**
	 * This method is used to initialize gui components.
	 */
	private void initGUI() {
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(0,2));
		
		PrimListModel model = new PrimListModel();
		
		
		JList<Integer> lista1 = new JList<>(model);
		lista1.setBorder(BorderFactory.createLineBorder(Color.black));
		JList<Integer> lista2 = new JList<>(model);
		lista2.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setViewportView(lista1);
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setViewportView(lista2);
		
		panel1.add(scrollPane1);
		panel1.add(scrollPane2);

		cp.add(panel1,BorderLayout.CENTER);
		JButton button = new JButton("sljedeči");
		button.addActionListener((a) -> model.next());
		
		cp.add(button,BorderLayout.PAGE_END);
		
	}


	/**
	 * This is a main method.
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new PrimDemo().setVisible(true);
				
			}
		});
	}
}
