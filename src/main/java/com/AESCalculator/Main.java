package com.AESCalculator;

import java.util.Scanner;

/*
 * NEED TO DOWNLOAD AND INSTALL JRE (JCE) Unlimited Strength Jurisdiction Policy Files 6
 */
public class Main {

	private static final int EXPECTED_NO_INPUT = 6;
	
	public static void main(String[] args) {
		System.out.println("Simple AES Calculator");
				
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Mode: To decrypt (D) or encrypt (E): ");
		String mode = scan.next();
		System.out.println("Entered " + mode);
		
		System.out.println("Enter AES Key 256 length in based64: ");
		String key = scan.next();
		System.out.println("Entered " + key);
		
		//Setting key
		AES.setKey(key);
		
		System.out.println("Enter IV in based 64: ");
		String iv = scan.next();
		System.out.println("Entered " + iv);
		

		System.out.println("Enter msg: ");
		String msg = scan.next();
		System.out.println("Entered " + msg);

		scan.close();
		

		//Set IV
		AES.setIv(iv);
		
		String output = null;
		if(mode.equalsIgnoreCase("e")) {
			//Encrypt
			try {
				output = AES.encrypt(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		else {
			
			//Encrypt
			try {
				output = AES.decrypt(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		System.out.println("OUTPUT: ");
		System.out.println(output);
		
	}

}
