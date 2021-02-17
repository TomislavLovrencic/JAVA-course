package hr.fer.zemris.java.gui.charts;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * @author Tomislav Lovrencic
 *
 * This class represents a demo for drawing a chart.
 */
public class BarChartDemo extends JFrame{

	private static final long serialVersionUID = 1L;
	
	/**
	 *  This is a bar chart component.
	 */
	private BarChartComponent chart;
	
	
	/**
	 *  This is a text used to store a path of a file;
	 */
	private String text;
	
	/**
	 * This is a basic constructor.
	 * @param c
	 * @param text
	 */
	public BarChartDemo(BarChartComponent c,String text) {
		super();
		this.chart = c;
		this.text = text;
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("BarChart");
		initGui();
		setMinimumSize(new Dimension(300,300));
		setSize(800,500);
	}

	/**
	 *  This method is used to initialize gui components.
	 */
	private void initGui() {
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(new JLabel(text, SwingConstants.CENTER), BorderLayout.NORTH);
		cp.add(chart,SwingConstants.CENTER);
		
	}
	
	/**
	 *  This is a main method.
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		File f1 = new File(args[0]);
	
		@SuppressWarnings("resource")
		Scanner s1 = new Scanner(f1);
		
		List<String> text2 = new ArrayList<String>();
		
		while (s1.hasNextLine()) {
			String b = s1.nextLine();
			text2.add(b);
		}
		
		BarChart br = parseStringIntoBarChart(text2);
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new BarChartDemo(new BarChartComponent(br),args[0]).setVisible(true);;
				
			}
		});
		
		
	}

	/**
	 * This method is used to parse string from file to an instance of {@link BarChart}.
	 * @param text2
	 * @return new BarChart.
	 */
	private static BarChart parseStringIntoBarChart(List<String> text2) {
		
		if(text2.size() < 6) {
			System.out.println("Text in file is not in good format!");
			System.exit(1);
		}
		
		try {
			String opisX = text2.get(0);
			
			String opisY = text2.get(1);
			
			List<XYValue> lista = new ArrayList<XYValue>();
			
			String[] array = text2.get(2).split(" ");
			for(int i=0;i<array.length;i++) {
				String[] elems = array[i].split(",");
				XYValue v = new XYValue(Integer.parseInt(elems[0]),Integer.parseInt(elems[1]));
				lista.add(v);
			}
	
			int min = Integer.parseInt(text2.get(3));
			int max = Integer.parseInt(text2.get(4));
			int step = Integer.parseInt(text2.get(5));	
			
			return new BarChart(lista,opisX,opisY,min,max,step);
		} catch(NumberFormatException e){
			System.out.println("Text in file is not in good format!");
			System.exit(1);
		}
		
		return null;
	}

}
