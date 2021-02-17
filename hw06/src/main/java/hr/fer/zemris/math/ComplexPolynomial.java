package hr.fer.zemris.math;



/**
 * @author Tomislav Lovrencic
 * 
 * This class represents a polynomial with complex coefficients.
 *
 */
public class ComplexPolynomial {
	
	/**
	 * Array of complex coefficients.
	 */
	private Complex[] factors;

	/**
	 * This is a basic constructor.
	 * @param factors
	 */
	public ComplexPolynomial(Complex ...factors) {
		this.factors = new Complex[factors.length];
		for(int i=0;i<factors.length;i++) {
			this.factors[i] = factors[i];
		}
	}
	/**
	 * This method is used to get order from polynomial.
	 * @return order of polynomial.
	 */
	public short order() {
		return (short) (factors.length -1);
	}
	
	/**
	 * This method is used to multiply this polynomial with given one.
	 * @param p
	 * @return multiplied polynomial.
	 */
	public ComplexPolynomial multiply(ComplexPolynomial p) {
		Complex[] array = new Complex[this.factors.length + p.factors.length -1];
		
		for(int i=0;i<array.length;i++) {
			array[i] = new Complex();
		}
		
		for(int i=0;i<this.factors.length;i++) {
			for(int j=0;j<p.factors.length;j++) {
				array[i+j] = array[i+j].add(this.factors[i].multiply(p.factors[j]));   
			}
		}
		return new ComplexPolynomial(array);
	}
	
	/**
	 * This method is used to derive this polynomial.
	 * @return new polynomial.
	 */
	public ComplexPolynomial derive() {
		Complex[] array = new Complex[this.factors.length -1];
		int index = this.factors.length -2;
		for(int i=this.factors.length-1;i>0;i--) {
			array[index] = this.factors[i].scale(i);
			index--;
		}
		return new ComplexPolynomial(array);
	}
	
	/**
	 * This method is used to calculate a value of polynomial with this given complex number.
	 * @param z
	 * @return Value of polynomial for this complex number.
	 */
	public Complex apply(Complex z) {
		Complex num = new Complex();
		for(int i=this.factors.length-1;i>= 0;i--) {
			num = num.add(this.factors[i].multiply(z.power(i)));
		}
		return num;
	
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i=this.factors.length-1;i>= 0;i--) {
			if(i != 0) {
				sb.append("("+this.factors[i]+")*z^"+i+"+");
				
			}
			else {
				sb.append("("+this.factors[i]+")");
			}
		}
		return sb.toString();
	}

}
