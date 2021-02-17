package hr.fer.zemris.lsystems.impl;

import hr.fer.oprpp1.custom.collections.ObjectStack;

/**
 * @author Tomislav Lovrencic
 *
 * 	This class represents stack of Turtle states used for executing commands and drawing on canvas.
 *
 */
public class Context {
	
	/**
	 *  Stack of turtle states.
	 */
	ObjectStack<TurtleState> stack;
	
	/**
	 * This is a basic constructor.
	 * @param state
	 */
	public Context(TurtleState state) {
		stack = new ObjectStack<>();
		stack.push(state);
	}
	
	
	/**
	 * This is a getter for turtle state.
	 * @return
	 */
	public TurtleState getCurrentState() {
		return stack.peek();
	}
	
	
	/**
	 * This is a method used to push a state on top of stack.s
	 * @param state
	 */
	public void pushState(TurtleState state) {
		stack.push(state);
	}
	
	
	/**
	 * This is a method used to pop a state from a stack.
	 */
	public void popState() {
		stack.pop();
	}

}
