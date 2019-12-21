package BC_Hospital.Project.Model;

import javax.persistence.*;

@Entity // Đánh dấu đây là một Entity, chịu sự quản lý của Hibernate
@Table(name = "onchain") //Entity này đại diện cho table BlockOnChain trong db
public class BlockOnChain {
	
	@Id // Đánh dấu biến ở dưới là primary key của table này
    @Column(name = "hash", unique = true)
    private String hash;

    @Column(name = "prevhash", unique = true)
    private String prevhash; 

    @Column(name = "data", unique = true)
    private String data;
    
    @Column(name = "timestamp", unique = true)
    private long timestamp;
    
    //Constructor mặc định
    private BlockOnChain() {}
    
    // Constructor có tham số
    public BlockOnChain(String hash, String prevhash, String data, long timestamp) {
		this.hash = hash;
		this.prevhash = prevhash;
		this.data = data;
		this.timestamp = timestamp;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getPrevhash() {
		return prevhash;
	}

	public void setPrevhash(String prevhash) {
		this.prevhash = prevhash;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
        
}
