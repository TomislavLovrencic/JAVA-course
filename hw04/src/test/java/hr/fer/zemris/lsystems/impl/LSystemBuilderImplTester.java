package hr.fer.zemris.lsystems.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class LSystemBuilderImplTester {

	@Test
	public void test1() {
		
		LSystemBuilderImpl impl = new LSystemBuilderImpl();
		
		impl.setAxiom("F");
		impl.registerProduction('F', "F+F--F+F");
		
		assertEquals("F+F--F+F",impl.build().generate(1));
		
	}
	
	@Test
	public void test2() {
		
		LSystemBuilderImpl impl = new LSystemBuilderImpl();
		
		impl.setAxiom("F");
		impl.registerProduction('F', "F+F--F+F");
		
		assertEquals("F+F--F+F+F+F--F+F--F+F--F+F+F+F--F+F",impl.build().generate(2));
		
	}
	

	
	
	
}
