package hr.fer.oprpp1.hw05.crypto;


import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption; 

/**
 * @author Tomislav Lovrencic
 *
 * This class represents working with files and checking their "protective sum" to find out if 
 * the file was corrupted or somehow changed. It also provides a possibilty to encrypt files 
 * and decrypting files.
 *
 */
public class Crypto {

	
	/**
	 * This is a main method of the class.
	 * @param args
	 */
	public static void main(String[] args)  {
		
		if(!args[0].equals("checksha") && !args[0].equals("encrypt") && !args[0].equals("decrypt")) {
			System.out.println("Wrong arguments!");
			System.exit(1);
		}
		
		if(args.length != 2 && args.length != 3) {
			System.out.println("Wrong number of arguments!");
			System.exit(1);
		}
		
		if(args[0].equals("checksha")) {
			System.out.println("Please provide expected sha-256 digest for "+args[1]);
			System.out.print("> ");
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			String line = sc.nextLine();
			try {
				checksha(line,args[1]);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("Please provide password as hex-encoded text (16 bytes, i.e. 32 hex-digits): ");
			System.out.print("> ");
			@SuppressWarnings("resource")
			Scanner sc2 = new Scanner(System.in);
			String line2 = sc2.nextLine();
			System.out.println("Please provide initialization vector as hex-encoded text (32 hex-digits): ");
			System.out.print("> ");
			String line3 = sc2.nextLine();
			if(args[0].equals("encrypt"))
				try {
					crypt(line2,line3,args[1],args[2],true);
				} catch (IOException e) {
					e.printStackTrace();
				}
			else {
				try {
					crypt(line2,line3,args[1],args[2],false);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}		
	}
	
	/**
	 * This method is used to encrypt and decrypt a file into a new file.
	 * @param password Password used to create an instance of SecretKey.
	 * @param vector Vector used to create an instance of ParameterSpec.
	 * @param path1 Path to first file that was being encrypted/decrypted.
	 * @param path2 Path to file that is being created.
	 * @param crypt Boolean to see if the Cipher is in mode encrypt or decrypt.
	 * @throws IOException
	 */
	private static void crypt(String password, String vector, String path1, String path2,boolean crypt) throws IOException {
		String keyText = password;
		String ivText = vector;
		SecretKeySpec keySpec = new SecretKeySpec(Util.hextobyte(keyText), "AES");
		AlgorithmParameterSpec paramSpec = new IvParameterSpec(Util.hextobyte(ivText));
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e1) {
			e1.printStackTrace();
		}
		try {
			cipher.init(crypt ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE, keySpec, paramSpec);
		} catch (InvalidKeyException | InvalidAlgorithmParameterException e1) {
			
			e1.printStackTrace();
		}

		InputStream is = null;
		OutputStream os = null;
		try {
			is = Files.newInputStream(Paths.get(path1), StandardOpenOption.READ);
			os = Files.newOutputStream(Paths.get(path2), StandardOpenOption.CREATE);

			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		
		byte[] buff = new byte[4096];
		while(true) {
			int r = is.read(buff);
			if(r < 1) break;
			os.write(cipher.update(buff,0,r));
		}		
		
		try {
			os.write(cipher.doFinal());
		
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		String encrypt = crypt ? "Encryption": "Decryption";
		System.out.println( encrypt + " completed. Generated file " + path2+ " based on file "+path1+".");
		
		is.close();
		os.close();
	}

	public static void checksha(String shaToCompare,String path) throws IOException {
		try {
			InputStream is = null;
			try {
				is = Files.newInputStream(Paths.get(path),StandardOpenOption.READ);
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			
			byte[] buff = new byte[4096];
			while(true) {
				int r = is.read(buff);
				if(r < 1) break;
				md.update(buff, 0, r);
			}
			
			byte[] digest = md.digest();
			
			String digestToHex = Util.bytetohex(digest);
			
			if(digestToHex.compareTo(shaToCompare) == 0) {
				System.out.println("Digesting completed. Digest of "+path+" matches expected digest.");
			}
			else {
				System.out.println("Digesting completed. Digest of "+path+" does not match the expected digest.\nDigest was: "+digestToHex);
			}
			
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	
}
