package hr.fer.oprpp1.custom.collections;

/**
 * @author Tomislav Lovrencic
 * 
 *  This class represents a model for testing our Linked list and array indexed list.
 *
 */
public class Demo { 
	
	/**
	 * This is a main method of the class
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		//1. primjer 
//		Collection col = new ArrayIndexedCollection(); 
//		col.add("Ivo"); col.add("Ana"); col.add("Jasna");
//		ElementsGetter getter = col.createElementsGetter(); 
//		System.out.println("Ima nepredanih elemenata: " + getter.hasNextElement()); 
//		System.out.println("Jedan element: " + getter.getNextElement()); 
//		System.out.println("Ima nepredanih elemenata: " + getter.hasNextElement()); 
//		System.out.println("Jedan element: " + getter.getNextElement()); 
//		System.out.println("Ima nepredanih elemenata: " + getter.hasNextElement()); 
//		System.out.println("Jedan element: " + getter.getNextElement()); 
//		System.out.println("Ima nepredanih elemenata: " + getter.hasNextElement()); 
//		System.out.println("Jedan element: " + getter.getNextElement());
//		
		
		//2. primjer
//		Collection col =  new ArrayIndexedCollection(); 
//		col.add("Ivo"); col.add("Ana"); col.add("Jasna"); 
//		ElementsGetter getter = col.createElementsGetter(); 
//		System.out.println("Ima nepredanih elemenata: " + getter.hasNextElement()); 
//		System.out.println("Ima nepredanih elemenata: " + getter.hasNextElement());
//		System.out.println("Ima nepredanih elemenata: " + getter.hasNextElement()); 
//		System.out.println("Ima nepredanih elemenata: " + getter.hasNextElement()); 
//		System.out.println("Ima nepredanih elemenata: " + getter.hasNextElement()); 
//		System.out.println("Jedan element: " + getter.getNextElement()); 
//		System.out.println("Ima nepredanih elemenata: " + getter.hasNextElement());
//		System.out.println("Ima nepredanih elemenata: " + getter.hasNextElement()); 
//		System.out.println("Jedan element: " + getter.getNextElement()); 
//		System.out.println("Ima nepredanih elemenata: " + getter.hasNextElement()); 
//		System.out.println("Ima nepredanih elemenata: " + getter.hasNextElement()); 
//		System.out.println("Ima nepredanih elemenata: " + getter.hasNextElement()); 
//		System.out.println("Jedan element: " + getter.getNextElement()); 
//		System.out.println("Ima nepredanih elemenata: " + getter.hasNextElement()); 
//		System.out.println("Ima nepredanih elemenata: " + getter.hasNextElement());
//		
		
		//3.primjer
//		Collection col =  new ArrayIndexedCollection(); 
//		col.add("Ivo"); col.add("Ana"); col.add("Jasna"); 
//		ElementsGetter getter = col.createElementsGetter();
//		System.out.println("Jedan element: " + getter.getNextElement()); 
//		System.out.println("Jedan element: " + getter.getNextElement()); 
//		System.out.println("Jedan element: " + getter.getNextElement()); 
//		System.out.println("Jedan element: " + getter.getNextElement());
//		
//		
		//4.primjer 
//		Collection col = new LinkedListIndexedCollection(); 
//		col.add("Ivo"); col.add("Ana"); col.add("Jasna"); 
//		ElementsGetter getter1 = col.createElementsGetter(); 
//		ElementsGetter getter2 = col.createElementsGetter(); 
//		System.out.println("Jedan element: " + getter1.getNextElement()); 
//		System.out.println("Jedan element: " + getter1.getNextElement());
//		System.out.println("Jedan element: " + getter2.getNextElement()); 
//		System.out.println("Jedan element: " + getter1.getNextElement()); 
//		System.out.println("Jedan element: " + getter2.getNextElement());
//		
		
		//5.primjer
//		Collection col1 = new ArrayIndexedCollection(); 
//		Collection col2 = new ArrayIndexedCollection();
//		col1.add("Ivo"); col1.add("Ana"); col1.add("Jasna"); 
//		col2.add("Jasmina"); col2.add("�tefanija"); col2.add("Karmela"); 
//		ElementsGetter getter1 = col1.createElementsGetter(); 
//		ElementsGetter getter2 = col1.createElementsGetter(); 
//		ElementsGetter getter3 = col2.createElementsGetter(); 
//		System.out.println("Jedan element: " + getter1.getNextElement()); 
//		System.out.println("Jedan element: " + getter1.getNextElement()); 
//		System.out.println("Jedan element: " + getter2.getNextElement()); 
//		System.out.println("Jedan element: " + getter3.getNextElement()); 
//		System.out.println("Jedan element: " + getter3.getNextElement());
//		
		
		//6.primjer
//		Collection col = new ArrayIndexedCollection(); 
//		col.add("Ivo"); col.add("Ana"); col.add("Jasna"); 
//		ElementsGetter getter = col.createElementsGetter();
//		System.out.println("Jedan element: " + getter.getNextElement()); 
//		System.out.println("Jedan element: " + getter.getNextElement()); 
//		col.clear(); 
//		System.out.println("Jedan element: " + getter.getNextElement());
//		
//		
		
		//7.primjer
//		Collection col = new ArrayIndexedCollection(); 
//		col.add("Ivo"); col.add("Ana"); col.add("Jasna"); 
//		ElementsGetter getter = col.createElementsGetter(); 
//		getter.getNextElement(); getter.processRemaining(System.out::println);
//		
//		
		
		//8.primjer
		class EvenIntegerTester implements Tester {  
			public boolean test(Object obj) {    
				if(!(obj instanceof Integer)) return false;    
				Integer i = (Integer)obj;    
				return i % 2 == 0;  
				} 
		}
		
		Collection col1 = new LinkedListIndexedCollection();
		Collection col2 = new ArrayIndexedCollection(); 
		col1.add(2); col1.add(3); col1.add(4); col1.add(5); col1.add(6); col2.add(12); 
		col2.addAllSatisfying(col1, new EvenIntegerTester()); 
		col2.forEach(System.out::println);
		
		//naputak , ako se zeli odkomentirati dio koda i provjeriti moze se napraviti sa Ctrl + 7 na oznacenom
		//dijelu koda
		
		
	}
}
