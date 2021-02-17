package hr.fer.oprpp1.custom.collections.demo;


import hr.fer.oprpp1.custom.collections.EmptyStackException;
import hr.fer.oprpp1.custom.collections.ObjectStack;

/**
 * @author Tomislav Lovrencic
 *
 * This class is being used to evaluate and calculate mathematical operations through stack.
 * 
 */
public class StackDemo {

	
	/**
	 *  This is a main method of the program.
	 * @param args One single string argument gives as a mathematical expression.
	 */
	public static void main(String[] args) {
		
		ObjectStack stack = new ObjectStack();
		
		String[] expression = args[0].split(" ");
		
	
		for(String element : expression) {
			boolean num = true;
		
			try {
				 Integer.parseInt(element);
			} catch(NumberFormatException e) {
				num = false;
			}
			
			if(num) {
				stack.push(element);
			}
			else {
				
				try {
					int number2 = Integer.parseInt(stack.pop().toString());
					int number1 = Integer.parseInt(stack.pop().toString());
					
					switch(element){
					case "+":
						stack.push(number1 + number2);
						break;
					case "-":
						stack.push(number1 - number2);
						break;
					case "/":
						try {
							stack.push(number1 / number2);
						} catch(ArithmeticException e) {
							System.out.println("Division by 0 is not possible!");
						}
						break;
					case "*":
						stack.push(number1 * number2);
						break;
					case "%":
						stack.push(number1 % number2);
						break;
				
					}
				} catch (EmptyStackException e) {
					System.out.println("Error : "+ e.getMessage() + "\nExpression : "+ "("+args[0]+ ") is not valid");
					System.exit(1);
					
				}
				
			}
		}
		
		if(stack.size() != 1) {
			System.out.println("ERROR : Expression : "+ "("+args[0]+")" +" is not valid");
			
		}
		else {
			System.out.print(stack.pop());
		}
		
	}
}