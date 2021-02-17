package hr.fer.zemris.lsystems.impl;

import java.awt.Color;

import hr.fer.oprpp1.math.Vector2D;

/**
 * @author Tomislav Lovrencic
 * This class represents a turtle state. Each state has currentPosition,currentDirection , color and shiftLenght.
 */
public class TurtleState {
	
	/**
	 *  Current position.
	 */
	private Vector2D currentPosition;
	
	
	/**
	 *  Current direction.
	 */
	private Vector2D currentDirection;
	
	/**
	 *  color.
	 */
	private Color color;
	
	/**
	 * shift length.
	 */
	private double shiftLength;
	
	
	/**
	 * This is a contructor for turtle state.
	 * @param curr
	 * @param dir
	 * @param color
	 * @param shi
	 */
	public TurtleState(Vector2D curr , Vector2D dir , Color color , double shi) {
		this.currentPosition = curr;
		this.currentDirection = dir;
		this.color = color;
		this.shiftLength = shi;
	}
	
	
	
	/**
	 * This is a getter for current position.
	 * @return
	 */
	public Vector2D getCurrentPosition() {
		return currentPosition;
	}


	/**
	 * This is a setter for current position.
	 * @return
	 */
	public void setCurrentPosition(Vector2D currentPosition) {
		this.currentPosition = currentPosition;
	}


	/**
	 * This is a getter for current direction.
	 * @return
	 */
	public Vector2D getCurrentDirection() {
		return currentDirection;
	}


	/**
	 * This is a setter for current direction.
	 * @return
	 */
	public void setCurrentDirection(Vector2D currentDirection) {
		this.currentDirection = currentDirection;
	}


	/**
	 * This is a getter for color.
	 * @return
	 */
	public Color getColor() {
		return color;
	}


	/**
	 * This is a setter for color.
	 * @return
	 */
	public void setColor(Color color) {
		this.color = color;
	}


	/**
	 * This is a getter for shift length.
	 * @return
	 */
	public double getShiftLength() {
		return shiftLength;
	}


	/**
	 * This is a setter for shift length.
	 * @return
	 */
	public void setShiftLength(double shiftLength) {
		this.shiftLength = shiftLength;
	}



	/**
	 * This method is used to create a new instace of turtle state by copying this one.
	 * @return
	 */
	public TurtleState copy() {
		return new TurtleState(currentPosition,currentDirection,color,shiftLength);
	}
}
