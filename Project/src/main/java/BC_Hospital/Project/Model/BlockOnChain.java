package BC_Hospital.Project.Model;

import javax.persistence.*;

@Entity // Đánh dấu đây là một Entity, chịu sự quản lý của Hibernate
@Table(name = "BlockOnChain") //Entity này đại diện cho table BlockOnChain trong db
public class BlockOnChain {
	
    @Id // Đánh dấu biến ở dưới là primary key của table này
    @Column(name = "hash", unique = true)
    private String hash;

    @Column(name = "previousHash")
    private String previousHash; 

    @Column(name = "data")
    private String data;
    
    @Column(name = "timeStamp")
    private long timeStamp;
    
    // Constructor mặc định
    private BlockOnChain() {}
    
    // Constructor có tham số
    public BlockOnChain(String hash, String previousHash, String data, long timeStamp) {
    	this.hash = hash;
    	this.previousHash = previousHash;
    	this.data = data;
    	this.timeStamp =timeStamp;
    }
    // Các Setter / Getter
    
    public void getHash(String hash) {
        this.hash = hash;
    }
    public String getHash() {
        return this.hash;
    }
    
    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }
    public String getPreviousHash() {
        return this.previousHash;
    }
    
    public void setData(String data) {
        this.data = data;
    }
    public String getData() {
        return this.data;
    }
    
    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
    public long GetTimeStamp() {
        return this.timeStamp;
    }
    
}
