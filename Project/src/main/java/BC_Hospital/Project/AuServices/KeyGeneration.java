package BC_Hospital.Project.AuServices;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import BC_Hospital.Project.Model.Node;

public class KeyGeneration {

	// Hàm tạo Key (cặp private key và public key)
	public static Node Generate() {
		Node node = null;
		try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
			/// SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			SecureRandom random = new SecureRandom();
			// ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256k1");
			// Initialize the key generator and generate a KeyPair
			keyGen.initialize(1024, random); // 512 bytes provides an acceptable security level
			KeyPair keyPair = keyGen.generateKeyPair();
			// Set the public and private keys from the keyPair
			PrivateKey privateKey = keyPair.getPrivate();
			PublicKey publicKey = keyPair.getPublic();

			// System.out.println("\nPrivate key: " +
			// Base64.getEncoder().encodeToString(privateKey.getEncoded()) + "\nPublic key:
			// " + Base64.getEncoder().encodeToString(publicKey.getEncoded()) );

			node = new Node(publicKey, privateKey);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return node;
	}

	public static PublicKey createPublicKeyFromString(String publickey) throws Exception{
//		try {
			byte[] data = Base64.getDecoder().decode((publickey.getBytes()));
			X509EncodedKeySpec spec = new X509EncodedKeySpec(data);

			KeyFactory fact = KeyFactory.getInstance("RSA");
			return fact.generatePublic(spec);
//		} catch (Exception e) {
//			System.out.println("Loi trong node publickey: " + e.getMessage());
//			// TODO: handle exception
//			return null;
//		}
	}

	public static PrivateKey createPrivateKeyFromString(String privatekey) throws Exception{
//		try {
			byte[] data = Base64.getDecoder().decode((privatekey.getBytes()));
			PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(data);

			KeyFactory fact = KeyFactory.getInstance("RSA");
			return fact.generatePrivate(spec);
//		} catch (Exception e) {
//			System.out.println("Loi trong node privatekey: " + e.getMessage());
//			return null;
//		}
	}

}
