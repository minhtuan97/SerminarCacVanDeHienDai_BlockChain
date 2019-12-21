package BC_Hospital.Project.Model;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import BC_Hospital.Project.AuServices.KeyGeneration;

// Lớp Node tương đương Wallet trong Bitcoin
public class Node {

	private PrivateKey privateKey;

	private PublicKey publicKey; // Chính là địa chỉ của Node

	private ROLE role; // Vai trò (BACSI: Bác sĩ , BENHNHAN: Bệnh nhân, BENHVIEN: Bệnh viện)

	public Node(String publickey, String privatekey) {
		try {
			this.publicKey = KeyGeneration.createPublicKeyFromString(publickey);
		} catch (Exception e) {
			System.out.println("Loi trong node publickey: " + e.getMessage());
		}
		
		try {
			this.privateKey = KeyGeneration.createPrivateKeyFromString(privatekey);
		} catch (Exception e) {
			System.out.println("Loi trong node privatekey: " + e.getMessage());
		}
	}
	// Các Getter / Getter các thuộc tính private

	public PrivateKey getPrivateKey() {
		return this.privateKey;
	}
	
	public String getPrivateKeyString() {
		return Base64.getEncoder().encodeToString(privateKey.getEncoded());
	}

	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}

	public PublicKey getPublicKey() {
		return this.publicKey;
	}
	
	public String getPublicKeyString() {
		return Base64.getEncoder().encodeToString(publicKey.getEncoded());
	}

	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}

	public ROLE getRole() {
		return this.role;
	}

	public void setRole(ROLE role) {
		this.role = role;
	}

	// Constructor mặc định
	public Node() {
	};

	// Constructor 2 Tham số (PrivateKey, PublicKey)
	public Node( PublicKey publicKey, PrivateKey privateKey) {
		this.privateKey = privateKey;
		this.publicKey = publicKey;
	};

}
