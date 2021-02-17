package hr.fer.zemris.java.gui.charts;

/**
 * @author Tomislav Lovrencic
 *
 * This class represents one bar on a chart.
 */
public class XYValue {

	/**
	 * value on x - axis.
	 */
	private int x;
	
	/**
	 * value on y - axis.
	 */
	private int y;
	
	/**
	 * This is a basic constructor.
	 * @param x
	 * @param y
	 */
	public XYValue(int x ,int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * This is a getter for x.
	 * @return
	 */
	public int getX() {
		return x;
	}

	/**
	 * This is a getter for y.
	 * @return
	 */
	public int getY() {
		return y;
	}
	
	
}
