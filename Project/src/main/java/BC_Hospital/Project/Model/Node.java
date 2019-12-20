package BC_Hospital.Project.Model;

import java.security.PrivateKey;
import java.security.PublicKey;

// Lớp Node tương đương Wallet trong Bitcoin
public class Node {
	
	private PrivateKey privateKey;
	
	private PublicKey publicKey;	// Chính là địa chỉ của Node
		
	private ROLE role;	// Vai trò (BACSI: Bác sĩ , BENHNHAN: Bệnh nhân, BENHVIEN: Bệnh viện)
	
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