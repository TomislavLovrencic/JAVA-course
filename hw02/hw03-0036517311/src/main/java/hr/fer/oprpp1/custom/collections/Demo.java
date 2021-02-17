package hr.fer.oprpp1.custom.collections;

import java.util.Iterator;

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
		
		SimpleHashtable<String, Integer> examMarks = new SimpleHashtable<>(2);
		
		examMarks.put("Ivana", 2); 
		examMarks.put("Ante", 2);
		examMarks.put("Jasna", 2);
		examMarks.put("Kristina", 5); 
		examMarks.put("Klara", 2);
		examMarks.put("Balen", 5);
		examMarks.put("Koome", 5); 
		
	
		
		Integer kristinaGrade = examMarks.get("Kristina");
		
		

		System.out.println("Kristina's exam grade is: " + kristinaGrade);
		System.out.println("Golam's exam grade is: " + examMarks.get("Golam"));
		
		System.out.println("Number of stored pairs: " + examMarks.containsValue(5)); 
		
		
		System.out.println(examMarks.toString());
		
		System.out.println("------------------------");
		
		
		ArrayIndexedCollection<Integer> col = null;
		
		
		
		
		
	
		
//				
//		
//		
//		Iterator<SimpleHashtable.TableEntry<String,Integer>> iter = examMarks.iterator();
//		
//		while(iter.hasNext()) {
//			SimpleHashtable.TableEntry<String,Integer> pair = iter.next();
//			System.out.printf("%s => %d%n", pair.getKey(), pair.getValue()); 
//			iter.remove();
//		} 
//		System.out.printf("Veličina: %d%n", examMarks.size());
		
//		SimpleHashtable<String,Integer> examMarks = new SimpleHashtable<>(2);
//		// fill data:
//		examMarks.put("Ivana", 2); examMarks.put("Ante", 2); 
//		examMarks.put("Jasna", 2); examMarks.put("Kristina", 5); 
//		examMarks.put("Ivana", 5);
//		// overwrites old grade for Ivana 
//		for(SimpleHashtable.TableEntry<String,Integer> pair : examMarks)
//		{ 
//			System.out.printf("%s => %d%n", pair.getKey(), pair.getValue());
//		}
		
//		for(SimpleHashtable.TableEntry<String,Integer> pair1 : examMarks) { 
//			for(SimpleHashtable.TableEntry<String,Integer> pair2 : examMarks)
//			{ 
//				System.out.printf( "(%s => %d) - (%s => %d)%n", pair1.getKey(), pair1.getValue(), pair2.getKey(), pair2.getValue());
//				} 
//		}
		
//		Iterator<SimpleHashtable.TableEntry<String,Integer>> iter = examMarks.iterator(); 
//		while(iter.hasNext()) { 
//			SimpleHashtable.TableEntry<String,Integer> pair = iter.next(); 
//			if(pair.getKey().equals("Ivana")) { 
//				iter.remove();  // sam iterator kontrolirano uklanja trenutni element } }
//			}
//		}
//		
//		System.out.println(examMarks.toString());
		
//		Iterator<SimpleHashtable.TableEntry<String,Integer>> iter1 = examMarks.iterator(); 
//		while(iter1.hasNext()) { 
//			SimpleHashtable.TableEntry<String,Integer> pair = iter1.next(); 
//			if(pair.getKey().equals("Ivana")) {
//				iter1.remove(); 
//				iter1.remove(); 
//			} 
//		}
		
//		Iterator<SimpleHashtable.TableEntry<String,Integer>> iter = examMarks.iterator(); 
//		while(iter.hasNext()) { 
//			SimpleHashtable.TableEntry<String,Integer> pair = iter.next();
//			if(pair.getKey().equals("Ivana")) {
//				examMarks.remove("Ivana"); 
//			} 
//		}
		
		
	}
}
