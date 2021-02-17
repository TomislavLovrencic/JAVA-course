package hr.fer.oprpp1.hw01;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Tomislav Lovrencic
 *
 * This class represents a example of a complex number with all its mathematical methods.
 *
 */
public class ComplexNumber {
	
	
	
	 /**
	 * Used for comparing double values.
	 */
	final double THRESHOLD = .0001;
	/**
	 *  Real part of a complex number.
	 */
	private double real;
	/**
	 *  Imaginary part of a complex number.
	 */
	private double img;
	
	/**
	 * This is a constructor that initializes a complex number using its given real and imaginary part.
	 * @param real Real part of a complex number.
	 * @param img Imaginary part of a complex number.
	 */
	public ComplexNumber(double real,double img) {
		this.real = real;
		this.img = img;
	}
	
	
	/**
	 * This method constructs a complex number using its given real part of the complex number.
	 * @param real Real part of the complex number.
	 * @return Return new complex number.
	 */
	public static ComplexNumber fromReal(Double real) {
		return new ComplexNumber(real,0);
	}
	
	/**
	 * This method constructs a complex number using its given imaginary part of the complex number.
	 * @param imaginary Imaginary part of the complex number.
	 * @return Return new complex number.
	 */
	public static ComplexNumber fromImaginary(double imaginary) {
		return new ComplexNumber(0,imaginary);
	}
	
	
	/**
	 * This method constructs a complex number using its given magnitude and angle of the complex number.
	 * @param magnitude Magnitude of complex number.
	 * @param angle angle of complex number.
	 * @return New complex number.
	 */
	public static ComplexNumber fromMagnitudeAndAngle(double magnitude , double angle) {
		return new ComplexNumber(magnitude*Math.cos(angle),magnitude*Math.sin(angle));
	}
	
	
	/**
	 * This method parses an string into a new complex number.
	 * 
	 * @param s String that will be parsed into a new complex number.
	 * @return returns a new complex number.
	 */
	public static ComplexNumber parse(String s) {
		
		double real = 0.0;
		double img = 0.0;
	
		Pattern regex1 =  Pattern.compile("([-|+]*[0-9]+\\.*[0-9]*)([-|+]+[0-9]*\\.*[0-9]*)[i$]+");
		Pattern regex2 = Pattern.compile("([-|+]*[0-9]+\\.*[0-9]*)$");
	    Pattern regex3 = Pattern.compile("([-|+]*[0-9]*\\.*[0-9]*)[i$]");
	    
		Matcher match1 = regex1.matcher(s);
		Matcher match2 = regex2.matcher(s);
		Matcher match3 = regex3.matcher(s);
		
	  
	    if(match1.find()) {
	    	try {
	    		real =Double.parseDouble(match1.group(1));
			}catch (NumberFormatException e) {
				System.out.print("Expression u entered cant be parsed!");
			}
	    	
	    	if(match1.group(2).equals("-")) {
	    		img = -1.0;
	    	}
	    	else {
	    		if(match1.group(2).equals("+")) {
	    			img = 1.0;
	    		}
	    		else {
	    			try {
	    				img = Double.parseDouble(match1.group(2));
	    			}catch (NumberFormatException e) {
	    				System.out.print("Expression u entered cant be parsed!");
	    			}
	    			
	    		}
	    		
	    	}
	    	
	    }
	    else if(match2.find()) {
	    	try {
	    		real =Double.parseDouble(match2.group(1));
			}catch (NumberFormatException e) {
				System.out.print("Expression u entered cant be parsed!");
			}
	    	
	    	img = 0.0;
	    }
	    else if(match3.find()) {
	    	real = 0.0;
	    	if(match3.group(1).equals("-")) {
	    		img = -1.0;
	    	}
	    	else {
	    		if(match3.group(1).equals("+")) {
	    			img = 1.0;
	    		}
	    		else {
	    			try {
	    				img = Double.parseDouble(match3.group(1));
	    			}catch (NumberFormatException e) {
	    				System.out.print("Expression u entered cant be parsed!");
	    			}
	    			
	    		}
	    		
	    	}
	    	
	    }
	    else {
	    	System.out.print("Expression u entered cant be parsed!");
	    	System.exit(1);
	    }
	   
	    return new ComplexNumber(real,img);		
	}
	
	/**
	 *  This method gets us a real part of a complex number.
	 * @return real part of complex number.
	 */
	public double getReal() {
		return this.real;
	}
	
	/**
	 *  This method gets us a imaginary part of a complex number.
	 * @return imaginary part of complex number.
	 */
	public double getImaginary() {
		return this.img;
	}
	
	/**
	 *  This method gets us a magnitude of a complex number.
	 * @return magnitude of complex number.
	 */
	public double getMagnitude() {
		return Math.hypot(real, img);
	}
	
	/**
	 *  This method gets us a angle of a complex number in radians.
	 * @return angle of complex number.
	 */
	public double getAngle() {
		double angle = Math.atan2(img, real);
		if(angle < 0.0) {
			angle += 2*Math.PI;
		}
		return angle;
		
	}
	
	
	/**
	 * This method is used to calculate mathematical operation of summary this complex number with given.
	 * @param c given complex number.
	 * @return new complex number.
	 */
	public ComplexNumber add(ComplexNumber c) {
		return new ComplexNumber(this.real+c.real , this.img+c.img);
		
	}
	
	/**
	 * This method is used to calculate mathematical operation of subtraction this complex number with given.
	 * @param c given complex number.
	 * @return new complex number.
	 */
	public ComplexNumber sub(ComplexNumber c) {
		return new ComplexNumber(this.real - c.real,this.img-c.img);
		
	}
	
	/**
	 * This method is used to calculate mathematical operation of multiplication this complex number with given.
	 * @param c given complex number.
	 * @return new complex number.
	 */
	public ComplexNumber mul(ComplexNumber c) {
		double realPart = c.real * real - c.img * img;
		double imgPart = c.real * img + c.img * real;
		
		return new ComplexNumber(realPart,imgPart);
	}
	
	/**
	 * This method is used to calculate mathematical operation of division this complex number with given.
	 * @param c given complex number.
	 * @return new complex number.
	 */
	public ComplexNumber div(ComplexNumber c) {
		double rez = c.real*c.real + c.img*c.img;
		
		
		if(rez < 2 * Double.MIN_VALUE) {
			throw new IllegalArgumentException("U cant divide with 0");
		}
		
		double upper = real * c.real + img * c.img;
		double lower = img * c.real - real * c.img;
		
		return new ComplexNumber(upper/rez,lower/rez);
		
	}
	
	/**
	 * This method uses this complex number and n as its exponent to calculate new value.
	 * @param n exponent.
	 * @return new complex number.
	 */
	public ComplexNumber power(int n) {
		if (n < 0) throw new IllegalArgumentException("n cant be negative!");
      
		double magnitude = Math.pow(getMagnitude(), n);
		double angle = getAngle() * n;
		
		return fromMagnitudeAndAngle(magnitude, angle);
	}
	
	
	/**
	 * This method uses this complex number and n as its nth root to calculate n values of new complex number.
	 * @param n exponent.
	 * @return new Array of complex numbers.
	 */
	public ComplexNumber[] root(int n) {
		if (n <= 0) throw new IllegalArgumentException("n cant be negative or zero!");
		
		double magnitude = Math.pow(getMagnitude(), 1.0/n);
		double ang = Math.atan2(getImaginary(), getReal());
	
		ComplexNumber[] numbers = new ComplexNumber[n];
		
		for(int i=0; i<n;i++) {
			double angle = (ang + 2*i*Math.PI)/n;
			numbers[i] = fromMagnitudeAndAngle(magnitude, angle);
			
		}
		
		return numbers;
		
	}
	
	/**
	 * This method is used to get a string representative of a complex number.
	 *
	 */
	public String toString() {
		return this.img > 0 ? this.real +"+"+ this.img+"i" : this.real+""+ this.img+"i";
	}
	
	
	
	
}
