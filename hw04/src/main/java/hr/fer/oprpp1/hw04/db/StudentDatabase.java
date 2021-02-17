package hr.fer.oprpp1.hw04.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Tomislav Lovrencic
 * 
 * This is a class that represents student database.
 *
 */
public class StudentDatabase {
	
	/**
	 * List of student records.
	 */
	private List<StudentRecord> list;
	
	/**
	 *  Map of student records with jmbag as keys.
	 */
	private HashMap<String,StudentRecord> map;
	
	/**
	 * This is a constructor for a database.
	 * @param lista
	 */
	public StudentDatabase(List<String> lista) {
		list = new ArrayList<>();
		map = new HashMap<>();
		
		for(int i=0;i<lista.size();i++) {
			String[] elems = lista.get(i).split("\t");
			if(map.get(elems[0]) == null) {
				if( elems.length == 4 && Integer.valueOf(elems[3]) >= 1 && Integer.valueOf(elems[3]) <= 5) {
					StudentRecord value = new StudentRecord(elems[0],elems[1],elems[2],Integer.valueOf(elems[3]));
					map.put(elems[0], value);
					list.add(value);
				}
				
			}
		}
	}

	
	/**
	 * This is a method that uses map to get student record using given jmbag as key.
	 * @param jmbag
	 * @return
	 */
	public StudentRecord forJMBAG(String jmbag) {
		return map.get(jmbag);
		
	}
	
	/** This method filters studentRecords using given filter.
	 * @param filter
	 * @return list of student records that was accpeted by this filter.
	 */
	public List<StudentRecord> filter(IFilter filter){
		List<StudentRecord> novaLista = new ArrayList<>();
		for(StudentRecord record : list) {
			if(filter.accepts(record)) {
				novaLista.add(record);
			}
		}
		return novaLista;
	}
}
