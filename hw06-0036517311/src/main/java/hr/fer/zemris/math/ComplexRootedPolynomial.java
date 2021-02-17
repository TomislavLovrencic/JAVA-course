package hr.fer.zemris.math;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Tomislav Lovrencic
 *
 * This class represents a complex rooted polynomial. 
 */
public class ComplexRootedPolynomial {
	/**
	 * Constant complex value.
	 */
	private Complex constant;
	
	/**
	 * List of complex roots for this polynomial.
	 */
	private List<Complex> listOfComplex;
	
	/**
	 * This is a basic constructor.
	 * @param constant
	 * @param roots
	 */
	public ComplexRootedPolynomial(Complex constant, Complex ... roots) {
		this.listOfComplex = new ArrayList<Complex>();
		this.constant = constant;
		for(Complex elem : roots) {
			this.listOfComplex.add(elem);
		}
	}
	/**
	 * This method is used to calculate a value of polynomial with this given complex number.
	 * @param z
	 * @return Value of polynomial for this complex number.
	 */
	public Complex apply(Complex z) {
		Complex helper = this.constant;
		for(int i=0;i<listOfComplex.size();i++) {
			helper.multiply(z.sub(listOfComplex.get(i)));
		}
		return helper;
	}
	/**
	 * This method is used to transform a complex rooted polynomial into a polynomial with coefficients.
	 * @return new polynomial.
	 */
	public ComplexPolynomial toComplexPolynom() {
		 	ComplexPolynomial pol = new ComplexPolynomial(Complex.ONE);

	        for (Complex nmb : listOfComplex) {
	            pol = pol.multiply(new ComplexPolynomial(nmb.negate(), Complex.ONE));
	        }

	        return pol.multiply(new ComplexPolynomial(constant));
	  
		
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("("+this.constant+")");
		for(Complex elem : listOfComplex) {
			sb.append("*(z-"+"("+elem+"))");
		}
		return sb.toString();
	}

	/**
	 * This method is used to find an index of closest root for this given complex number.
	 * @param z
	 * @param treshold
	 * @return index of closest root.
	 */
	public int indexOfClosestRootFor(Complex z, double treshold) {
		Complex num = new Complex();
		for(int i=0;i<listOfComplex.size();i++) {
			num = z.sub(listOfComplex.get(i));
			if(num.module() < treshold) {
				return i;
			}
		}
		return -1;
	}

}
