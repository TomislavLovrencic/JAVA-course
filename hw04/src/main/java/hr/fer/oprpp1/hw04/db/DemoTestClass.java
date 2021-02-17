package hr.fer.oprpp1.hw04.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;




/**
 * @author Tomislav Lovrencic
 * 
 * This class represents a demo class which is used to create queries and work on created database.
 *
 */
public class DemoTestClass {

	
	/** This is a main method.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		List<String> lines = Files.readAllLines(
                Paths.get("src\\main\\resources\\database.txt"),
                StandardCharsets.UTF_8
        );
		
		

		StudentDatabase db = new StudentDatabase(lines);
	
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		

		while(true) {
		
			System.out.print("> ");
			if(sc.hasNextLine()) {
				String next = sc.nextLine();
				
				if(next.equals("exit")) {
					System.out.println("Goodbye!");
					break;
				}
				
				List<StudentRecord> lista = new ArrayList<>();
				if(next.equals("")) continue;
				
				try {
					QueryParser parser = new QueryParser(next);
					if(parser.isDirectQuery()) {
						StudentRecord r = db.forJMBAG(parser.getQueriedJMBAG());
						if(r != null) lista.add(r);
						if(lista.size() > 0) {
							System.out.println("Using index for record retriveal.");
							System.out.println(createTable(lista));
						}
					}
					else {
						for(StudentRecord r : db.filter(new QueryFilter(parser.getQuery()))) {
								lista.add(r);
						}
						if(lista.size() > 0) {
							System.out.println(createTable(lista));
						}
					}
					System.out.print("Records selected: "+lista.size()+"\n");	
				} catch(QueryParserException e) {
					System.out.println(e.getLocalizedMessage());
					continue;
				}
				
			}
			else {
				break;
			}	
		}
		
		sc.close();
		
	}

	
	/**
	 * This method is used to create a table.
	 * @param lista
	 * @return String 
	 */
	public static String createTable(List<StudentRecord> lista) {
		StringBuilder s = new StringBuilder();

		
		int longestfirstname = 0;
		int longestlastname = 0;
		
		for(StudentRecord rec : lista) {
			if(rec.getFirstName().length() > longestfirstname) {
				longestfirstname = rec.getFirstName().length();
			}
			if(rec.getLastName().length() > longestlastname) {
				longestlastname = rec.getLastName().length();
			}
		}
		
		String header = drawHeaderFooter(longestlastname+2,longestfirstname+2);
		s.append(header+"\n");
		
		for(StudentRecord rec : lista) {
			s.append("| ");
			s.append(rec.getJmbag());
			s.append(" | ");
			s.append(rec.getLastName());
			for(int j=rec.getLastName().length();j<longestlastname;j++) {
				s.append(" ");
			}
			s.append(" | ");
			s.append(rec.getFirstName());
			for(int j=rec.getFirstName().length();j<longestfirstname;j++) {
				s.append(" ");
			}
			s.append(" | ");
			s.append(rec.getFinalGrade());
			s.append(" |\n");
		}
		s.append(header);
		
		return s.toString();
		
	}
	
	/**
	 *  This method is used to draw a footer and header for the table.
	 * @param longestlastname
	 * @param longestfirstname
	 * @return
	 */
	public static String drawHeaderFooter(int longestlastname,int longestfirstname) {
		StringBuilder s = new StringBuilder();
		
		s.append("+");
		for(int i=0;i<12;i++) {
			s.append("=");
		}
		s.append("+");
		
		for(int i=0;i<longestlastname;i++) {
			s.append("=");
		}
		s.append("+");
		for(int i=0;i<longestfirstname;i++) {
			s.append("=");
		}
		s.append("+");
		for(int i=0;i<3;i++) {
			s.append("=");
		}
		s.append("+");
		
		return s.toString();
	}


}
