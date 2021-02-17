package hr.fer.zemris.java.hw05.shell;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Tomislav Lovrencic
 *
 * This class is used as an Utility class , it has different static methods used in different commands. 
 */
public class SharedUtilityShell {
	
	/**
	 * @author Tomislav Lovrencic
	 *
	 * This class represents a pair of string and int.
	 */
	public static class Pair {
		String a = null;
		int b = 0;
		
		Pair(String a,int b){
			this.a = a;
			this.b = b;
		}
		
		public int getB() {
			return this.b;
		}
		public String getA() {
			return this.a;
		}
	}
	
	
	/**
	 * This method is used to work with files by reading the bytes from a file and then using a helper object 
	 * for processing values.
	 * @param env Environment
	 * @param is InputStream
	 * @param buff array of bytes.
	 * @param helper Object of interface Helper.
	 */
	public static void readFiles(Environment env , InputStream is,byte[] buff,Helper helper) {
		
		while(true) {
			
			helper.before(buff);
			
			int k = 0;
			try {
				k = is.read(buff);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(k < 1) break;
			
			helper.after(buff,k,env);
		}
		
	}

	
	/**
	 * This method is used to parse an user's input into a Pair that consists of string that user
	 * gave and the number of the last char in string.
	 * @param path
	 * @return
	 */
	public static Pair parse(String path) {
		StringBuilder sb = new StringBuilder();
		
		if(path.length() == 0) {
			return new Pair(null,0);
		}
		
		int index = 0;
		if(path.charAt(0) == '"') {
			index++;
			while(true) {
				if(path.charAt(index) == '"' && path.charAt(index-1) != '\\') break;
			
				
				if(path.charAt(index) == '\\') {
					index++;
					if(index < path.length() - 1) {

						if(path.charAt(index) == '"' || path.charAt(index) == '\\') {
							sb.append(path.charAt(index));
							index++;
							
						}
						else {
							sb.append(path.charAt(index-1));
							
						}
					}
					else {
						throw new IllegalArgumentException();
					}
				}
				else {
					sb.append(path.charAt(index));
					index++;
					
				}

			}
		}
		else {
			while(path.charAt(index) != ' ') {
				sb.append(path.charAt(index));
				index++;
				if(index == path.length()) {
					break;
				}
			
			}
		}
		
		Pair pair = new Pair(sb.toString(),index);
		
		
		return pair;
		
	}
}
