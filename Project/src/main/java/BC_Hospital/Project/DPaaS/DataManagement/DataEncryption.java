package BC_Hospital.Project.DPaaS.DataManagement;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


public class DataEncryption {
	public static PublicKey obtainPublicKey() {
		return null;
	}

	// Data Encryption
	public static String dataEncryption(String publickey, Object Data) {
		byte[] byteData = convertStringToByte(publickey);
		X509EncodedKeySpec spec = null;
		KeyFactory factory = null;

		try {
			spec = new X509EncodedKeySpec(byteData);
			factory = KeyFactory.getInstance("RSA");
		} catch (Exception e) {
			System.out.print(e.toString());
		}

		PublicKey pubKey = null;

		try {
			pubKey = factory.generatePublic(spec);
		} catch (Exception e) {
			System.out.print(e.toString());
		}

		Cipher c = null;
		try {
			c = Cipher.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			System.out.print(e.toString());
		} catch (NoSuchPaddingException e) {
			System.out.print(e.toString());
		}

		try {
			c.init(Cipher.ENCRYPT_MODE, pubKey);
		} catch (InvalidKeyException e) {
			System.out.print(e.toString());
		}

		byte[] msg = convertObjectToByte(Data);
		byte encryptOut[] = null;
		try {
			encryptOut = c.doFinal(msg);
		} catch (IllegalBlockSizeException e) {
			System.out.print(e.toString());
		} catch (BadPaddingException e) {
			System.out.print(e.toString());
		}

		String strEncrypt = Base64.getEncoder().encodeToString(encryptOut);
		return strEncrypt;
	}

	// Data Descyption
	public static Object dataDecryption(String privatekey, String cyphertext) {
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
			System.out.print(e.toString());
		}

		// Giai ma du lieu
		Cipher c = null;
		try {
			c = Cipher.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			System.out.print(e.toString());
		} catch (NoSuchPaddingException e) {
			System.out.print(e.toString());
		}

		try {
			c.init(Cipher.DECRYPT_MODE, priKey);
		} catch (InvalidKeyException e1) {
			System.out.print(e1.toString());
		}

		byte decryptOut[] = null;
		try {
			decryptOut = c.doFinal(Base64.getDecoder().decode(cyphertext));
		} catch (IllegalBlockSizeException e) {
			System.out.print(e.toString());
		} catch (BadPaddingException e) {
			System.out.print(e.toString());
		}

		Object object = null;
		try {
			object = convertByteToObject(decryptOut);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return object;
	}

	// ==================== Utilities Function ====================

	// Convert String to byte
	private static byte[] convertStringToByte(String stringData) {
		byte[] bytes = Base64.getDecoder().decode(stringData);
		return bytes;
	}

	// Convert Object to byte
	private static byte[] convertObjectToByte(Object object) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(bos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			oos.writeObject(object);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] data = bos.toByteArray();
		return data;
	}

	// Convert byte to Object
	private static Object convertByteToObject(byte[] data) throws IOException, ClassNotFoundException {
		ByteArrayInputStream in = new ByteArrayInputStream(data);
		ObjectInputStream is = new ObjectInputStream(in);
		return is.readObject();
	}

}
