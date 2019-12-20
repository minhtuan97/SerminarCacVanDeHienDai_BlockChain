package BC_Hospital.Project.DPaaS.DataManagement;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class HashIntegrity {

	Boolean dataDecryption(String privatekey, String cyphertext) {
		byte[] privateKey = convertStringToByte(privatekey);

		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privateKey);
		KeyFactory factory = null;
		try {
			factory = KeyFactory.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			System.out.print(e.toString());
		}
		PrivateKey priKey = null;

		try {
			priKey = factory.generatePrivate(spec);
		} catch (InvalidKeySpecException e) {
			return false;
		}

		// Giai ma du lieu
		Cipher c = null;
		try {
			c = Cipher.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			return false;
		} catch (NoSuchPaddingException e) {
			return false;
		}

		try {
			c.init(Cipher.DECRYPT_MODE, priKey);
		} catch (InvalidKeyException e1) {
			return false;
		}

		try {
			c.doFinal(Base64.getDecoder().decode(cyphertext));
		} catch (IllegalBlockSizeException e) {
			return false;
		} catch (BadPaddingException e) {
			return false;
		}

		return true;
	}

	// ==================== Utilities Function ====================

	// Convert String to byte
	private byte[] convertStringToByte(String stringData) {
		byte[] bytes = Base64.getDecoder().decode(stringData);
		return bytes;
	}

}
