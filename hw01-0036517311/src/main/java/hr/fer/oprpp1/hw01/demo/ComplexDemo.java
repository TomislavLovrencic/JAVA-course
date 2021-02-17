package hr.fer.oprpp1.hw01.demo;



import hr.fer.oprpp1.hw01.ComplexNumber;

/**
 * @author Tomislav Lovrencic
 *
 * This class is used to perform various calculations on complex numbers.
 *
 */
public class ComplexDemo {

	/**
	 * This is a  main method of the program.
	 * @param args 
	 */
	public static void main(String[] args) {
		
		ComplexNumber c1 = new ComplexNumber(2, 3);
		ComplexNumber c2 = ComplexNumber.parse("2.5-3i");
		ComplexNumber c3 = c1.add(ComplexNumber.fromMagnitudeAndAngle(2, 1.57)) .div(c2).power(3).root(2)[1];
		System.out.println(c3.toString()); 
		System.out.println(c1.toString());
		
		ComplexNumber c4 = new ComplexNumber(-5, 2);
		
		
		
		System.out.println(ComplexNumber.parse("2-3i").toString());
	
		
		
	}
}
