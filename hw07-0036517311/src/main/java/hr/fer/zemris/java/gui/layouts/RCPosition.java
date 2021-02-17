package hr.fer.zemris.java.gui.layouts;

/**
 * @author Tomislav Lovrencic
 * 
 * This class represents a position of a button in layout.
 */
public class RCPosition {

	/**
	 * This is a row for button.
	 */
	private int row;
	/**
	 * This is a column for button.
	 */
	private int column;
	
	
	/**
	 * This is a basic contructor.
	 * @param row
	 * @param column
	 */
	public RCPosition(int row,int column) {
		this.row = row;
		this.column = column;
	}
	
	
	/**
	 * This is a method used to parse string into RCPosition.
	 * @param text
	 * @return
	 */
	public static RCPosition parse(String text) {
		String[] lista = text.split(",");
		try {
			return new RCPosition(Integer.parseInt(lista[0]),Integer.parseInt(lista[1]));
		} catch(Exception e) {
			return null;
		}	
	}


	/**
	 * This is a getter for row.
	 * @return
	 */
	public int getRow() {
		return row;
	}

	/**
	 * This is a getter for column.
	 * @return
	 */
	public int getColumn() {
		return column;
	}

	
}
