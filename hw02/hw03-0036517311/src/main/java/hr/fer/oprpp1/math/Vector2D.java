package hr.fer.oprpp1.math;

/**
 * @author Tomislav Lovrencic
 *
 * This is a class that represents an Vector in 2D graph.
 *
 */
public class Vector2D {
	
	
	/**
	 * x value of vector.
	 */
	private double x;
	
	/**
	 * y value of vector. 
	 */
	private double y;
	
	/** 
	 * This is a basic constructor.
	 * @param x
	 * @param y
	 */
	public Vector2D(double x,double y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 *  This is a simple getter for x value.
	 * @return
	 */
	public double getX() {
		return this.x;
	}
	/**
	 *  This is a simple getter for y value.
	 * @return
	 */
	public double getY() {
		return this.y;
	}
	
	/**
	 * This is a method that adds given vector to this one.
	 * @param offset Given vector to be added to this vector.
	 */
	public void add(Vector2D offset) {
		this.x += offset.x;
		this.y += offset.y;
	}
	
	/**
	 * This is a method that adds the given vector to this one.
	 * @param offset
	 * @return return new vector.
	 */
	public Vector2D added(Vector2D offset) {
		return new Vector2D(this.x + offset.x , this.y+offset.y);
		
	}
	
	/**
	 * This is a method that is used to rotate an vector by given angle.
	 * @param angle
	 */
	public void rotate(double angle) {
		double x = Math.cos(angle)* this.x  - Math.sin(angle)* this.y;
		double y = Math.sin(angle)*this.x + Math.cos(angle)*this.y;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * This is a method that is used to rotate an vector by given angle.
	 * @param angle
	 * @return new vector.
	 */
	public Vector2D rotated(double angle) {
		double x = Math.cos(angle)* this.x  - Math.sin(angle)* this.y;
		double y = Math.sin(angle)*this.x + Math.cos(angle)*this.y;
		return new Vector2D(x,y);
		
	}
	
	/**
	 * This is a method used to scale our vector by given value.
	 * @param scaler
	 */
	public void scale(double scaler) {
		this.x *= scaler;
		this.y *= scaler;
		
	}
	
	/**
	 * This is a method used to scale our vector by given value.
	 * @param scaler
	 * @return new vector.
	 */
	public Vector2D scaled(double scaler) {
		return new Vector2D(this.x*scaler,this.y*scaler);
		
	}
	
	/**
	 *  This is a method that gives us a new copy of our vector.
	 * @return new vector.
	 */
	public Vector2D copy() {
		return new Vector2D(this.x,this.y);
	}

}
