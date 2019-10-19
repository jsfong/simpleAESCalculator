package com.AESCalculator;

import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;


public class AES {

	private static SecretKeySpec secretKey;
	private static byte[] key;
	private static IvParameterSpec ivSpec;
	
	public static void setKey(String sKey) {
		
		//Convert Based64 to Byte
		key = Base64.getDecoder().decode(sKey);
		
		//Create SecretKeySpec
		secretKey = new SecretKeySpec(key, "AES");
		
		System.out.println("Key in hex byte: " + Util.bytesToHex(key));
	}
	
	public static void setIv(String sIv) {
		//Convert Based64 to Byte
		byte[] bIV = Base64.getDecoder().decode(sIv);
		
		System.out.println("IV in hex byte: " + Util.bytesToHex(bIV));
				
		ivSpec = new IvParameterSpec(bIV);
	}
	
	
	public static String encrypt(String strToEncrypt) throws Exception {
		

		Security.addProvider(new BouncyCastleProvider());
		
		//Convert Based64 to Byte
		byte[] bInput = Base64.getDecoder().decode(strToEncrypt);
		
		/*
		 * PKSCS7 Padding
		 * 
		 * PKSC7 will perform padding as below to the data to form a 16byte block data
		 * PKSC7 will pack number of hex value (full block length - data length) based on the missing number of data
		 * 
		 * example:
		 * <data of 12 byte>04040404
		 * <data of 15 byte>01
		 * 
		 * 
		 * NOTE: PKSC7 will always perform padding even the data is full 16byte block
		 */
		//Get Cipher Instance
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");	
		
		//Init Cipher for Encryption
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);

		//Perform Encryprion
		byte[] cipherText = cipher.doFinal(bInput);

		System.out.println("Encrypted cipher text in hex byte: " + Util.bytesToHex(cipherText));

		
		//Encode back to based 64
		return Base64.getEncoder().encodeToString(cipherText);
		
	}
	
	public static String decrypt(String strToDecrypt) throws Exception {
		Security.addProvider(new BouncyCastleProvider());
		
		//Convert Based64 to Byte
		byte[] bInput = Base64.getDecoder().decode(strToDecrypt);
		
		System.out.println("Msg in hex: " + Util.bytesToHex(bInput));
		
		//Get Cipher Instance
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");	
		cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
		
		byte[] cipherText = cipher.doFinal(bInput);
		
		System.out.println("Decrypted Msg in hex byte: " + Util.bytesToHex(cipherText));
		
		return new String(cipherText);
				
	}
	
	public static String generateKey() throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(256);
		SecretKey key = keyGenerator.generateKey();
		String encodedKey = Base64.getEncoder().encodeToString(key.getEncoded());
		
		return encodedKey;
	}
}
