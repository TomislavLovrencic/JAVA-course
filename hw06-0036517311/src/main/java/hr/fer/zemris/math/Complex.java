package hr.fer.zemris.math;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Tomislav Lovrencic
 *
 * This class is used to perform various calculations on complex numbers.
 *
 */
public class Complex {
	/**
	 *  Real part of a complex number.
	 */
	private double real;
	/**
	 *  Imaginary part of a complex number.
	 */
	private double img;

	
	/**
	 * This is a complex number that has both values 0;
	 */
	public static final Complex ZERO = new Complex(0,0);
	/**
	 * This is a complex number that has real part value 1 and imaginary value 0;
	 */
	public static final Complex ONE = new Complex(1,0);
	/**
	 * This is a complex number that has real part value -1 and imaginary value 0;
	 */
	public static final Complex ONE_NEG = new Complex(-1,0);
	/**
	 * This is a complex number that has real part value 0 and imaginary value 1;
	 */
	public static final Complex IM = new Complex(0,1);
	/**
	 * This is a complex number that has real part value 0 and imaginary value -1;
	 */
	public static final Complex IM_NEG = new Complex(0,-1);
	
	public Complex() {
		this.real = 0;
		this.img = 0;
	}
	/**
	 * This is a constructor that initializes a complex number using its given real and imaginary part.
	 * @param real Real part of a complex number.
	 * @param img Imaginary part of a complex number.
	 */
	public Complex(double re, double im) {
		this.real = re;
		this.img = im;
	}
	/**
	 *  This method gets us a magnitude of a complex number.
	 * @return magnitude of complex number.
	 */
	public double module() {
		return Math.hypot(real, img);
		
	}
	/**
	 * This method is used to calculate mathematical operation of multiplication this complex number with given.
	 * @param c given complex number.
	 * @return new complex number.
	 */
	public Complex multiply(Complex c) {
		double realPart = c.real * real - c.img * img;
		double imgPart = c.real * img + c.img * real;
		
		return new Complex(realPart,imgPart);
	}
	/**
	 * This method is used to calculate mathematical operation of division this complex number with given.
	 * @param c given complex number.
	 * @return new complex number.
	 */
	public Complex divide(Complex c) {
		double rez = c.real*c.real + c.img*c.img;

		double upper = real * c.real + img * c.img;
		double lower = img * c.real - real * c.img;
		
		return new Complex(upper/rez,lower/rez);
	}
	/**
	 * This method is used to calculate mathematical operation of summary this complex number with given.
	 * @param c given complex number.
	 * @return new complex number.
	 */
	public Complex add(Complex c) {
		return new Complex(this.real + c.real,this.img + c.img);
	}
	
	/**
	 * This method is used to scale this complex number with given number.
	 * @param n
	 * @return Scaled complex number.
	 */
	public Complex scale(int n) {
		return new Complex(this.real * n ,this.img*n);
	}
	
	/**
	 * This method is used to calculate mathematical operation of subtraction this complex number with given.
	 * @param c given complex number.
	 * @return new complex number.
	 */
	public Complex sub(Complex c) {
		return new Complex(this.real-c.real , this.img-c.img);
	}
	
	
	/**
	 * This method is used to transform complex positive number into negative.
	 * @return Negative complex number.
	 */
	public Complex negate() {
		return new Complex(-this.real,-this.img);
	}
	/**
	 * This method uses this complex number and n as its exponent to calculate new value.
	 * @param n exponent.
	 * @return new complex number.
	 */
	public Complex power(int n) {
		if (n < 0) throw new IllegalArgumentException("n cant be negative!");
	      
		double ang = Math.atan2(img, real);
		if(ang < 0.0) {
			ang += 2*Math.PI;
		}
		double magnitude = Math.pow(module(), n);
		double angle = ang * n;
		
		return new Complex(magnitude*Math.cos(angle),magnitude*Math.sin(angle));
	}
	/**
	 * This method uses this complex number and n as its nth root to calculate n values of new complex number.
	 * @param n exponent.
	 * @return new List of complex numbers.
	 */
	public List<Complex> root(int n) {
		List<Complex> numbers = new ArrayList<Complex>();
		if (n <= 0) throw new IllegalArgumentException("n cant be negative or zero!");
		
		double magnitude = Math.pow(module(), 1.0/n);
		double ang = Math.atan2(this.img, this.real);
	
		
		for(int i=0; i<n;i++) {
			double angle = (ang + 2*i*Math.PI)/n;
			numbers.add(new Complex(magnitude*Math.cos(angle),magnitude*Math.sin(angle)));
			
		}
		
		return numbers;
	}
	
	/**
	 * This method is used to parse a complex number from shell.
	 * @param s
	 * @return Parsed complex number.
	 */
	public static Complex parseComplexNumber(String s) {
		double real = 0.0;
		double img = 0.0;
	
		Pattern regex1 =  Pattern.compile("([-|+]*[0-9]+.*[0-9]*)([-|+]+[ ]*[i]+)([0-9]*.*[0-9]*)");
		Pattern regex2 = Pattern.compile("([-|+]*[0-9]+.*[0-9]*)");
	    Pattern regex3 = Pattern.compile("([-|+]*[i]+)([0-9]*.*[0-9]*)");
	    
		Matcher match1 = regex1.matcher(s);
		Matcher match2 = regex2.matcher(s);
		Matcher match3 = regex3.matcher(s);
		
	  
	    if(match1.find()) {
	    	try {
	    		real =Double.parseDouble(match1.group(1));
			}catch (NumberFormatException e) {
				return null;
			}
	    	String[] list = match1.group(2).split(" ");
	    	String sign = "";
	    	if(list.length == 1) {
	    		sign = Character.toString(list[0].charAt(0));
	    	}
	    	else {
	    		sign = list[0];
	    	}
	    	
	    	if(match1.group(3).equals("")) {
	    		if(sign.equals("-")) {
	    			img = -1.0;
	    		}
	    		else if(sign.equals("+")) {
	    			img = 1.0;
	    		}
	    	}
	    	else {
	    		try {
	    			img = Double.parseDouble(match1.group(3));
	    			if(sign.equals("-"))  img = -img;
				}catch (NumberFormatException e) {
					return null;
				}
	    	}
	    }
	    else if(match3.find()) {
	    	real = 0.0;
	    	if(match3.group(2).equals("")) {
	    		
	    		if(s.length() >= 2) {
	    			if( !(s.charAt(s.length()-2) == '-' || s.charAt(s.length()-2) == '+') ){
	    				return null;
	    			}
	  	    	}
	    
	    		if(match3.group(1).equals("-i")) {
	    			img = -1.0;
	    		}
	    		else if(match3.group(1).equals("+i") || match3.group(1).equals("i")) {
	    			img = 1.0;
	    		}
	    		
	    	}
	    	else {
	    		try {
	    			img = Double.parseDouble(match3.group(2));
	    			if(match3.group(1).equals("-i"))  img = -img;
				}catch (NumberFormatException e) {
					return null;
				}
	    	}
	    	
	    }
	    else if(match2.find()) {
	    	try {
	    		real =Double.parseDouble(match2.group(1));
			}catch (NumberFormatException e) {
				return null;
			}
	    	
	    	img = 0.0;
	    }
	    else {
	    	return null;
	    }
	   
	    return new Complex(real,img);	
	}
	@Override
	public String toString() {
		if(this.real >= 0 && this.img >= 0) {
			return Math.abs(real)+"+"+"i"+Math.abs(img);
		}
		if(this.real >=0) {
			return Math.abs(real)+"-i"+Math.abs(img);
		}
		if(this.img >= 0) {
			return "-"+Math.abs(real)+"+i"+Math.abs(img);
		}
		return "-"+Math.abs(real) +"-i"+Math.abs(img);

	}
}
