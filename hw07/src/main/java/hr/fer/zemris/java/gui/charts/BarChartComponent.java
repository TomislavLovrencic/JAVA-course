package hr.fer.zemris.java.gui.charts;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.geom.AffineTransform;


import javax.swing.JComponent;


/**
 * @author Tomislav Lovrencic
 *
 * This class represents a component of bar chart.
 */
public class BarChartComponent extends JComponent{

	private static final long serialVersionUID = 1L;
	
	/**
	 *  This is an instance of barChart.
	 */
	private BarChart chart;
	
	/**
	 *  This is an outside padding.
	 */
	private int outsidePadding = 20;
	
	/**
	 *  This is an inside padding.
	 */
	private int insidePadding = 30;
	
	/**
	 * This is a basic constructor.
	 * @param c
	 */
	public BarChartComponent(BarChart c) {
		this.chart = c;
		this.setSize(getPreferredSize());
		this.setSize(400,500);
        this.setVisible(true);
       
	}

	@Override
	protected void paintComponent(Graphics g) {		
		super.paintComponent(g);
		
		Graphics2D g1 = (Graphics2D) g;
		Insets ins = getInsets();
		
		double scaledWidth = (getWidth() - (2*outsidePadding)) - (ins.left + ins.right);
		double scaledHeight = (getHeight() - (2*outsidePadding)) - (ins.top + ins.bottom);
		
		g1.setColor(Color.white);
		g1.fillRect(outsidePadding + ins.left, outsidePadding + ins.top, (int) scaledWidth , (int) scaledHeight);
		
		
		//Opisi grafa x-os i y-os
		g1.setColor(Color.black);
		AffineTransform oldXForm = g1.getTransform();
        AffineTransform at = new AffineTransform();
        at.rotate(- Math.PI / 2);
        g1.setTransform(at);
        
        int wFontY = g1.getFontMetrics().stringWidth(chart.getOpisY());
        int heightFont = g1.getFontMetrics().getHeight();
        g1.drawString(chart.getOpisY(), (int) -((scaledHeight / 2) + ins.top + outsidePadding + wFontY/2), outsidePadding + ins.left + (insidePadding / 2) + heightFont / 2);
        g1.setTransform(oldXForm);
        int wFontX = g1.getFontMetrics().stringWidth(chart.getOpisX());
		g1.drawString(chart.getOpisX(), (int) (scaledWidth / 2) + outsidePadding + ins.left - wFontX / 2, getHeight() - (ins.bottom +outsidePadding+insidePadding / 2));
		
		
		//added +10 for more clarity
		int longestNumber = g1.getFontMetrics().stringWidth(Integer.toString(chart.getMaxY())) + 10;
		double x = outsidePadding + ins.left + insidePadding + longestNumber;
		double y = getHeight() - (outsidePadding + ins.bottom + insidePadding + heightFont);
		
		//y - os
		g1.drawLine( (int) x, (int) y,(int) x,(int) (getHeight() - (y+heightFont)));
		//x - os
		g1.drawLine( (int) x, (int) y,(int) (outsidePadding + ins.left+ scaledWidth - insidePadding),(int) y);
		
		//making arrows in system
		g1.drawLine((int) x, (int) (getHeight() - (y+heightFont)), (int) x - 8,(int) (getHeight() - (y+heightFont)) + 8 );
		g1.drawLine((int) x, (int) (getHeight() - (y+heightFont)), (int) x + 8,(int) (getHeight() - (y+heightFont)) + 8 );
		g1.drawLine((int) (outsidePadding + ins.left+ scaledWidth - insidePadding), (int) y, (int) (outsidePadding + ins.left+ scaledWidth - insidePadding) - 8,(int) y + 8 );
		g1.drawLine((int) (outsidePadding + ins.left+ scaledWidth - insidePadding), (int) y, (int) (outsidePadding + ins.left+ scaledWidth - insidePadding) - 8,(int) y - 8 );

		
		double scaler1 = (chart.getMaxY()-chart.getMinY()) / chart.getStep();
		int val = g1.getFontMetrics().getMaxAscent() / 3;
		double hlp = (2*y - getHeight() - heightFont) / scaler1;
		x = x - longestNumber;
		
		int brMin = chart.getMinY();
		int numberOfY = (chart.getMaxY() - chart.getMinY()) / chart.getStep();
		for(int i = 0;i < numberOfY;i++) {
			g1.setColor(Color.black);
			g1.drawString(Integer.toString(brMin), (int) x + (longestNumber-10) - g1.getFontMetrics().stringWidth(brMin+""), (int) y + val);
			g1.drawLine( (int) x + longestNumber, (int) y ,(int) (x + longestNumber / 1.2),(int) y);
			g1.setColor(Color.gray.brighter());
			
			g1.drawLine( (int) x + longestNumber+1, (int) y ,(int) (outsidePadding + ins.left+ scaledWidth - insidePadding),(int) y);
			
			brMin +=chart.getStep();
			y-= hlp;
		}
		
		g1.drawLine( (int) x + longestNumber+1,82,(int) (outsidePadding + ins.left+ scaledWidth - insidePadding),82);
		g1.setColor(Color.black);
		g1.drawString(Integer.toString(brMin), (int) x + (longestNumber-10) - g1.getFontMetrics().stringWidth(brMin+""), (int) 86);
		g1.drawLine( (int) x + longestNumber, 82 ,(int) (x + longestNumber / 1.2), 82);
		
		
		x = outsidePadding + ins.left + insidePadding + longestNumber;
		y = getHeight() - (outsidePadding + ins.bottom + insidePadding);
		g1.setColor(Color.black);
		g1.drawLine((int) x, (int) y - heightFont, (int) x, (int) (y - heightFont +8));
		double scaler2 = chart.getLista().size();

		int dec = (int) ((scaledWidth - (ins.left + ins.right + 2*insidePadding +2*outsidePadding + longestNumber - 10)) / scaler2);
		
		
		for(int i=0;i<=chart.getLista().size();i++) {
			g1.setColor(Color.black);			
			g1.drawLine( (int) x , (int) y - heightFont,(int)  x ,(int) (y - heightFont + 8));
			if(!(i == chart.getLista().size()))	{
				int l4 = g1.getFontMetrics().stringWidth(Integer.toString(chart.getLista().get(i).getX()));
				g1.drawString(Integer.toString(chart.getLista().get(i).getX()), (int) (x+dec/2 - l4/2), (int) y);
			}
			g1.setColor(Color.gray.brighter());
			if(i != 0) {
				g1.drawLine( (int) x , (int) y - heightFont,(int)  x ,(int) (getHeight() - (y)));

			}
			
			g1.setColor(Color.cyan.darker());
			if(!(i == chart.getLista().size()))	{
				g1.fillRect((int) x+2, (int) (y - heightFont - ( (double) (chart.getLista().get(i).getY() - chart.getMinY()) / chart.getStep() ) * hlp),
						(int) dec-3, (int) (((double) (chart.getLista().get(i).getY() - chart.getMinY()) / chart.getStep() ) * hlp)+1);
			}
			x+=dec;
		}
		
		x = outsidePadding + ins.left + insidePadding + longestNumber;
		y = getHeight() - (outsidePadding + ins.bottom + insidePadding + heightFont);
	
		g1.setColor(Color.black);
		g1.drawLine( (int) x, (int) y,(int) (outsidePadding + ins.left+ scaledWidth - insidePadding),(int) y);
	
	}


	/**
	 *  This is a getter for bar chart.
	 * @return
	 */
	public BarChart getChart() {
		return chart;
	}
	
	

}
