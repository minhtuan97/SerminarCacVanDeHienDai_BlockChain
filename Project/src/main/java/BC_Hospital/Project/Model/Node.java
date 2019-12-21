package BC_Hospital.Project.Model;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

// Lớp Node tương đương Wallet trong Bitcoin
public class Node {
	
	private PrivateKey privateKey;
	
	private PublicKey publicKey;	// Chính là địa chỉ của Node
		
	private ROLE role;	// Vai trò (BACSI: Bác sĩ , BENHNHAN: Bệnh nhân, BENHVIEN: Bệnh viện)
	
	
	public Node(String privatekey, String publickey)
	{
		byte[] data = Base64.getDecoder().decode((publickey.getBytes()));
		 X509EncodedKeySpec spec = new X509EncodedKeySpec(data);
		 
		 try {
			 KeyFactory fact = KeyFactory.getInstance("RSA");
			 this.publicKey= fact.generatePublic(spec);
		} catch (Exception e) {
			System.out.println("Loi trong node publickey"+e.getMessage());
			// TODO: handle exception
		}
		 
		 byte[] data2 = Base64.getDecoder().decode((privatekey.getBytes()));
		 PKCS8EncodedKeySpec spec2 = new PKCS8EncodedKeySpec(data2);
		 
		 try {
			 KeyFactory fact2 = KeyFactory.getInstance("RSA");
			 this.privateKey= fact2.generatePrivate(spec2);
		} catch (Exception e) {
			System.out.println("Loi trong node privatekey"+e.getMessage());
			// TODO: handle exception
		}
		 
	}
	// Các Getter / Getter các thuộc tính private 
	
    public PrivateKey getPrivateKey() {
        return this.privateKey;
    }
    
    
    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }
    
    public PublicKey getPublicKey() {
        return this.publicKey;
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
	public Node () {};
	
	// Constructor 2 Tham số (PrivateKey, PublicKey)
	public Node ( PrivateKey privateKey, PublicKey publicKey) {
		this.privateKey = privateKey;
		this.publicKey = publicKey;
	};

}
