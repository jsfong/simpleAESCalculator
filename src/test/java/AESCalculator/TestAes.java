package AESCalculator;

import static org.junit.Assert.*;

import java.util.Base64;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.AESCalculator.AES;

import junit.framework.Assert;

public class TestAes {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNorminal() {
		
		String output =null;
		AES.setKey("Xy70ynh4DkTsGRC1fcT49hjZ2c4K+tH35/94FFgzx9s=");
		AES.setIv("AAAAAAAAAAAAAAAAAAAAAA==");
		try {
			output = AES.encrypt("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA==");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals("Invalid output", "l9BNkWTr5uGtnq+NccS1DsExzhpqx2V5s84ATLbcTzI=", output);
	}

	@Test
	public void testNorminal2() {
		String output =null;
		AES.setKey("Xy70ynh4DkTsGRC1fcT49hjZ2c4K+tH35/94FFgzx9s=");
		AES.setIv("AAAAAAAAAAAAAAAAAAAAAA==");
		try {
			output = AES.encrypt("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals("Invalid output", "l9BNkWTr5uGtnq+NccS1DoA/m/HUxLx+yZwanlX/wZ8pflryQB3rWZxr3YQbq694", output);
	}
	
	
	@Test
	public void testDecrypt01() {
		String output =null;
		AES.setKey("Xy70ynh4DkTsGRC1fcT49hjZ2c4K+tH35/94FFgzx9s=");
		AES.setIv("AAAAAAAAAAAAAAAAAAAAAA==");
		try {
			output = AES.decrypt("l9BNkWTr5uGtnq+NccS1DoA/m/HUxLx+yZwanlX/wZ8pflryQB3rWZxr3YQbq694");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String clearExpectedOutput = new String (Base64.getDecoder().decode("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA="));
		assertEquals("Invalid output", clearExpectedOutput, output);
	}
	
	
	@Test
	public void testEncryptDecrypt() throws Exception {
		
		String sampleMsg 	= "testinssg";
		String sampleMsgBase64 = Base64.getEncoder().encodeToString(sampleMsg.getBytes());
		String keyBase64 	= "Xy70ynh4DkTsGRC1fcT49hjZ2c4K+tH35/94FFgzx9s=";
		String ivBase64 	= "AAAAAAAAAAAAAAAAAAAAAA==";
		
		AES.setKey(keyBase64);
		AES.setIv(ivBase64);
		String encryptedString = AES.encrypt(sampleMsgBase64);
		String decryptedString = AES.decrypt(encryptedString);
		
		
		assertEquals("Decrypted clear string is not equal to input string", decryptedString, sampleMsg);
		
	}
}
