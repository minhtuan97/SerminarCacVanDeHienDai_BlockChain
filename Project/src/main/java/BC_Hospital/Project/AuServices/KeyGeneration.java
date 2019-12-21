package BC_Hospital.Project.AuServices;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;

import BC_Hospital.Project.Model.Node;

public class KeyGeneration {
	
	// Hàm tạo Key (cặp private key và public key)
	public static Node Generate() {
		Node node = null;
		try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256k1");
			// Initialize the key generator and generate a KeyPair
			keyGen.initialize(ecSpec, random);   //256 bytes provides an acceptable security level
        	KeyPair keyPair = keyGen.generateKeyPair();
        	// Set the public and private keys from the keyPair
        	PrivateKey privateKey = keyPair.getPrivate();
        	PublicKey publicKey = keyPair.getPublic();
        	
    		System.out.println("\nPrivate key: " + privateKey + "\nPublic key: " + publicKey );
        	
        	node = new Node(privateKey, publicKey);
	        	
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
		return node;
	}
	
}
