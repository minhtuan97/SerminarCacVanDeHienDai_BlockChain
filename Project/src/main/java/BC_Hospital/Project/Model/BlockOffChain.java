package BC_Hospital.Project.Model;

import javax.persistence.*;

// Pojo (plain old Java object) là class đại diện cho một Table

@Entity // Đánh dấu đây là một Entity, chịu sự quản lý của Hibernate
@Table(name = "BlockOffChain") //Entity này đại diện cho table BlockOffChain trong db
public class BlockOffChain {
	@Id // Đánh dấu biến ở dưới là primary key của table này
	private String hash;
	
	// Trường data ở dưới đại diện cho cột data của table trong database
	@Column(name = "data", unique = true)
	private byte[] data; 
	
	// Constructor mặc định
	protected  BlockOffChain () {}
	
	// Constructor 2 Tham số
	public BlockOffChain(String hash, byte[] data) {
		this.hash = hash;
		this.data = data;
	}
	
	// Các Setter / Getter

	public void setHash(String hash) {
	    this.hash = hash;
	}
	public String getHash() {
	    return this.hash;
	}
	
	public void setData(byte[] data) {
	    this.data = data;
	}
	public byte[] getData() {
	    return this.data;
	}
}
