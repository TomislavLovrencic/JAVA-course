package hr.fer.oprpp1.hw01;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

public class ComplexNumberTester {

	final double THRESHOLD = .0001;
	
	@Test
	public  void testParse1() {
		ComplexNumber num = new ComplexNumber(2,-3);
		
		assertEquals(ComplexNumber.parse("2-3i").toString(),num.toString());
		
	}
	
	@Test
	public  void testParse2() {
		ComplexNumber num = new ComplexNumber(3,-1);
		
		assertEquals(ComplexNumber.parse("3-i").toString(),num.toString());
		
	}
	@Test
	public  void testParse3() {
		ComplexNumber num = new ComplexNumber(2,0);
		
		assertEquals(ComplexNumber.parse("+2").toString(),num.toString());
		
	}
	@Test
	public  void testParse4() {
		ComplexNumber num = new ComplexNumber(0,2);
		
		assertEquals(ComplexNumber.parse("+2i").toString(),num.toString());
		
	}
	@Test
	public  void testParse5() {
		ComplexNumber num = new ComplexNumber(3.15,-2.11);
		
		assertEquals(ComplexNumber.parse("+3.15-2.11i").toString(),num.toString());
		
	}
	@Test
	public  void testParse6() {
		ComplexNumber num = new ComplexNumber(0,-2);
		
		assertEquals(ComplexNumber.parse("-2i").toString(),num.toString());
		
	}
	
	@Test
	public  void testParse7() {
		ComplexNumber num = new ComplexNumber(0,1);
		
		assertEquals(ComplexNumber.parse("+i").toString(),num.toString());
		
	}
	@Test
	public  void testParse8() {
		ComplexNumber num = new ComplexNumber(2.71,0);
		
		assertEquals(ComplexNumber.parse("+2.71").toString(),num.toString());
		
	}
	@Test
	public  void testParse9() {
		ComplexNumber num = new ComplexNumber(2.71,1.334);
		
		assertEquals(ComplexNumber.parse("+2.71+1.334i").toString(),num.toString());
		
	}
	@Test
	public  void testParse10() {
		ComplexNumber num = new ComplexNumber(0,-4.1113);
		
		assertEquals(ComplexNumber.parse("-4.1113i").toString(),num.toString());
		
	}
	@Test
	public  void testParse11() {
		ComplexNumber num = new ComplexNumber(-5.2222,-4.1113);
		
		assertEquals(ComplexNumber.parse("-5.2222-4.1113i").toString(),num.toString());
		
	}
	@Test
	public  void testParse12() {
		ComplexNumber num = new ComplexNumber(4444,0);
		
		assertEquals(ComplexNumber.parse("+4444").toString(),num.toString());
		
	}

	@Test
	public void testtMagnitude() {
		ComplexNumber num = new ComplexNumber(2,-3);
		
		assertEquals(num.getMagnitude(),Math.sqrt(13),THRESHOLD);
	}
	
	@Test
	public void testAngle() {
		ComplexNumber num = new ComplexNumber(2,-3);
		
		double angle = Math.atan2(-3, 2);
		if(angle < 0.0) {
			angle += 2*Math.PI;
		}
		assertEquals(num.getAngle(),angle,THRESHOLD);
		
	}
	
	@Test
	public void testAdd() {
		ComplexNumber num = new ComplexNumber(2,-3);
		ComplexNumber num2 = new ComplexNumber(1,-5);
		
		assertEquals(num.add(num2).getReal(),3,THRESHOLD);
		assertEquals(num.add(num2).getImaginary(),-8,THRESHOLD);
		
		
	}
	@Test
	public void testSub() {
		ComplexNumber num = new ComplexNumber(2,-3);
		ComplexNumber num2 = new ComplexNumber(1,-5);
		
		assertEquals(num.sub(num2).getReal(),1,THRESHOLD);
		assertEquals(num.sub(num2).getImaginary(),2,THRESHOLD);
		
		
		
	}
	
	@Test
	public void testMul() {
		ComplexNumber num = new ComplexNumber(2,-3);
		ComplexNumber num2 = new ComplexNumber(1,-5);
		
		assertEquals(num.mul(num2).getReal(),-13,THRESHOLD);
		assertEquals(num.mul(num2).getImaginary(),-13,THRESHOLD);
	}
	
	
	@Test
	public void testDiv1() {
		ComplexNumber num = new ComplexNumber(2,-3);
		ComplexNumber num2 = new ComplexNumber(1,-5);
		num = num.div(num2);
		
		assertEquals(num.getReal(),17.0/26.0,THRESHOLD);
		assertEquals(num.getImaginary(),7.0/26.0,THRESHOLD);
		
	}

	
	@Test
	public void testPower() {
		ComplexNumber num = new ComplexNumber(2,-3);
		int n = 5;
		
		assertEquals(num.power(n).getReal(),122.000,THRESHOLD);
		assertEquals(num.power(n).getImaginary(),597,THRESHOLD);
		assertEquals(num.power(n).getAngle(),1.3692166,THRESHOLD);
		assertEquals(num.power(n).getMagnitude(),609.3381656,THRESHOLD);
		
		
	}
	
	
	@Test
	public void testRoot() {
		ComplexNumber num = new ComplexNumber(2,-3);
		int n = 4;
		
		assertEquals(num.root(n)[0].getReal(),1.336596,THRESHOLD);
		assertEquals(num.root(n)[1].getReal(),0.335171369,THRESHOLD);
		assertEquals(num.root(n)[2].getReal(),-1.336596,THRESHOLD);
		assertEquals(num.root(n)[3].getReal(),-0.335171369,THRESHOLD);
		
		assertEquals(num.root(n)[0].getImaginary(),-0.335171369,THRESHOLD);
		assertEquals(num.root(n)[1].getImaginary(),1.336596,THRESHOLD);
		assertEquals(num.root(n)[2].getImaginary(),0.335171369,THRESHOLD);
		assertEquals(num.root(n)[3].getImaginary(),-1.336596,THRESHOLD);
		
		
	}
	
	@Test
	public void testRoot2() {
		ComplexNumber num = new ComplexNumber(1,2);
		int n = 4;
		
		assertEquals(num.root(n)[0].getReal(),1.1763,THRESHOLD);
		assertEquals(num.root(n)[1].getReal(),-0.33416,THRESHOLD);
		assertEquals(num.root(n)[2].getReal(),-1.1763,THRESHOLD);
		assertEquals(num.root(n)[3].getReal(),0.33416,THRESHOLD);
		
		assertEquals(num.root(n)[0].getImaginary(),0.33416,THRESHOLD);
		assertEquals(num.root(n)[1].getImaginary(),1.1763,THRESHOLD);
		assertEquals(num.root(n)[2].getImaginary(),-0.33416,THRESHOLD);
		assertEquals(num.root(n)[3].getImaginary(),-1.1763,THRESHOLD);
		
		
	}
	
	
	
	
}