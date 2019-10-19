package demo;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {

	private static SecretKeySpec secretKey;
	private static byte[] key;
	private static IvParameterSpec ivSpec;
	
	public static void setKey(String sKey) {
		
		//Convert Based64 to Byte
		key = Base64.getDecoder().decode(sKey);
		
		//Create SecretKeySpec
		secretKey = new SecretKeySpec(key, "AES");
		
		System.out.println("Key in byte: " + Util.bytesToHex(key));
	}
	
	public static void setIv(String sIv) {
		//Convert Based64 to Byte
		byte[] bIV = Base64.getDecoder().decode(sIv);
		
		System.out.println("Key in byte: " + Util.bytesToHex(bIV));
				
		ivSpec = new IvParameterSpec(bIV);
	}
	
	
	public static String encrypt(String strToEncrypt) throws Exception {
		

		//Convert Based64 to Byte
		byte[] bInput = Base64.getDecoder().decode(strToEncrypt);
				
		//Get Cipher Instance
		Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");

		//Init Cipher for Encryption
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);

		//Perform Encryprion
		byte[] cipherText = cipher.doFinal(bInput);

		System.out.println("Encrypted cipher text: " + Util.bytesToHex(cipherText));

		
		//Encode back to based 64
		return Base64.getEncoder().encodeToString(cipherText);
		
	}
	
	public static String generateKey() throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(256);
		SecretKey key = keyGenerator.generateKey();
		String encodedKey = Base64.getEncoder().encodeToString(key.getEncoded());
		
		return encodedKey;
	}
}
