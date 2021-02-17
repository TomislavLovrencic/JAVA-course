package hr.fer.oprpp1.hw05.crypto;

/**
 * @author Tomislav Lovrencic
 *
 * This class is used for converting hex number into bytes and also
 * bytes into hex.
 * 
 */
public class Util {

	/**
	 * This class is used to create a byte array of hex numbers transformed into bytes.
	 * @param keyText Representation of hex numbers.
	 * @return byte array.
	 */
	public static byte[] hextobyte(String keyText) {
		
		if(keyText.length()%2!=0) throw new IllegalArgumentException();
		for(int i=0;i<keyText.length();i++) {
			if(!Character.isAlphabetic(keyText.charAt(i)) &&
					!Character.isDigit(keyText.charAt(i))) {
				throw new IllegalArgumentException();
			}
		}
		
		byte[] array = new byte[keyText.length() == 0 ? 0 : keyText.length()/2];
		
		int i=0;
		int brojac = 0;
		while (i < keyText.length()) {
			
			String niz = "";
			
			niz+=hexToBin(keyText.charAt(i));
			niz+=hexToBin(keyText.charAt(i+1));
			int number = 0;
			int eks = 0;
			for(int j=niz.length()-1;j>=0;j--) {
				if(j == 0 && Character.getNumericValue(niz.charAt(j)) == 1){
					number -= Character.getNumericValue(niz.charAt(j)) *Math.pow(2, eks);
				}
				else {
					
					number +=Character.getNumericValue(niz.charAt(j)) *Math.pow(2, eks);
				}
				eks++;
			}
			
			array[brojac] =  (byte) number;
			brojac++;
			i+=2;
			
		}
		
		return array;
		
	}
	
	/**
	 * This method is used to covert array of bytes into string of hex numbers.
	 * @param bytearray
	 * @return
	 */
	public static String bytetohex(byte[] bytearray) {
		StringBuilder sb = new StringBuilder();
		
		for(int i=0;i<bytearray.length;i++) {
			int value = bytearray[i] & 0xFF;
			
			String v = processValueToString(value);
			
			
			if(v.length() == 1) {
				v = "0"+v;
			}
			
			sb.append(v);
		}
		
		return sb.toString();
	}
	
	/**
	 * This method is used to transform numbers from 10 to 15 to appropriate value in hexadecimal.
	 * @param s
	 * @return
	 */
	public static String transformNumberToLetter(int s) {
		if(s == 10) return "a";
		if(s == 11) return "b";
		if(s == 12) return "c";
		if(s == 13) return "d";
		if(s == 14) return "e";
		if(s == 15) return "f";
		return "";
	}
	
	/**
	 * This method i used to proces integer value into hexadecimal value.
	 * @param value
	 * @return
	 */
	public static String processValueToString(int value) {
		StringBuilder sb = new StringBuilder();
		while(value > 0) {
			int ostatak = value % 16;
			if(ostatak > 9) {
				sb.append(transformNumberToLetter(ostatak));
			}
			else {
				sb.append(ostatak);
			}
			value /= 16;
		}
		sb = sb.reverse();
		return sb.toString();
	}
	
	/**
	 * This method is used to convert hexadecimal value to binary.
	 * @param hexdec
	 * @return
	 */
	public static String hexToBin(char hexdec) {
        StringBuilder sb = new StringBuilder();
        switch (hexdec) {
        case '0':
            sb.append("0000");
            break;
        case '1':
        	sb.append("0001");
            break;
        case '2':
        	sb.append("0010");
            break;
        case '3':
        	sb.append("0011");
            break;
        case '4':
        	sb.append("0100");
            break;
        case '5':
        	sb.append("0101");
            break;
        case '6':
        	sb.append("0110");
         
            break;
        case '7':
        	sb.append("0111");
         
            break;
        case '8':
        	sb.append("1000");
     
            break;
        case '9':
        	sb.append("1001");
            
            break;
        case 'A':
        case 'a':
        	sb.append("1010");
          
            break;
        case 'B':
        case 'b':
        	sb.append("1011");
         
            break;
        case 'C':
        case 'c':
        	sb.append("1100");
           
            break;
        case 'D':
        case 'd':
        	sb.append("1101");
           
            break;
        case 'E':
        case 'e':
        	sb.append("1110");
         
            break;
        case 'F':
        case 'f':
        	sb.append("1111");
            
            break;
        }
        return sb.toString();
	}
	
}
