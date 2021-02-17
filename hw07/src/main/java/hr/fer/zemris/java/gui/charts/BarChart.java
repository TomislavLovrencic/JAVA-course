package hr.fer.zemris.java.gui.charts;

import java.util.List;

/**
 * @author Tomislav Lovrencic
 *
 * This class represents a description of a chart.
 */
public class BarChart {
	
	/**
	 *  This is a list of XYValues.
	 */
	private List<XYValue> lista;
	/**
	 *  This is a description of x axis.
	 */
	private String opisX;
	/**
	 *  This is a description of y axis.
	 */
	private String opisY;
	/**
	 *  This is a minimum y value.
	 */
	private int minY;
	/**
	 *  This is a maximum y value.
	 */
	private int maxY;
	/**
	 *  This is a step value.
	 */
	private int step;

	/**
	 * This is a basic constructor.
	 * @param l
	 * @param oX
	 * @param oY
	 * @param min
	 * @param max
	 * @param step
	 */
	public BarChart(List<XYValue> l,String oX,String oY,int min,int max,int step ) {
		if(min < 0) throw new IllegalArgumentException();
		if(!(max > min)) throw new IllegalArgumentException();
		
		for(XYValue elem : l) {
			if(elem.getY() < min) throw new IllegalArgumentException();
		}
			
		this.lista = l;
		this.minY = min;
		this.maxY = max + ((max - min) % step);
		this.opisX = oX;
		this.opisY = oY;
		this.step = step;
		
	}

	/**
	 * This is a getter for list.
	 * @return
	 */
	public List<XYValue> getLista() {
		return lista;
	}

	/**
	 * This is a setter for list.
	 * @return
	 */
	public void setLista(List<XYValue> lista) {
		this.lista = lista;
	}

	/**
	 * This is a getter for opisX.
	 * @return
	 */
	public String getOpisX() {
		return opisX;
	}

	/**
	 * This is a setter for opisX.
	 * @return
	 */
	public void setOpisX(String opisX) {
		this.opisX = opisX;
	}

	/**
	 * This is a getter for opisY.
	 * @return
	 */
	public String getOpisY() {
		return opisY;
	}

	/**
	 * This is a setter for opisY.
	 * @return
	 */
	public void setOpisY(String opisY) {
		this.opisY = opisY;
	}

	/**
	 * This is a getter for minY.
	 * @return
	 */
	public int getMinY() {
		return minY;
	}

	/**
	 * This is a setter for minY.
	 * @return
	 */
	public void setMinY(int minY) {
		this.minY = minY;
	}

	/**
	 * This is a getter for maxY.
	 * @return
	 */
	public int getMaxY() {
		return maxY;
	}

	/**
	 * This is a setter for maxY.
	 * @return
	 */
	public void setMaxY(int maxY) {
		this.maxY = maxY;
	}

	/**
	 * This is a getter for step.
	 * @return
	 */
	public int getStep() {
		return step;
	}

	/**
	 * This is a setter for step.
	 * @return
	 */
	public void setStep(int step) {
		this.step = step;
	}
	
}
