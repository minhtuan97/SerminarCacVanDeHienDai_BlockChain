package BC_Hospital.Project.DPaaS.DataManagement;

import java.util.Date;


public class Block {
	public String hash; // Hash = Digital Signature : định danh của Block tạo ra từ hàm băm từ dữ liệu của Block
	public String previousHash; // Hash của Block trước của Block trong chuỗi BlockChain
	//private String data; // Dữ liệu của Block : our data will be a simple message.
	//private long timeStamp; // Thời gian sinh ra Block : as number of milliseconds since 1/1/1970.
	//private int nonce; // chỉ lần này
	public String data; // Dữ liệu của Block : our data will be a simple message.
	public long timeStamp; // Thời gian sinh ra Block : as number of milliseconds since 1/1/1970.
	public int nonce; // chỉ lần này
	
	//Block Constructor.  
	public Block(String data,String previousHash ) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		
		this.hash = calculateHash(); //Making sure we do this after we set the other values.
	}
	
	// Tính toán Hash của Block từ nội dung của Block : Calculate new hash based on blocks contents
	public String calculateHash() {
		String calculatedhash = StringUtils.applySha256( 
				previousHash +
				Long.toString(timeStamp) +
				Integer.toString(nonce) + 
				data 
				);
		return calculatedhash;
	}
	
	// Tăng giá trị nonce cho đến khi đạt được mục tiêu băm. Increases nonce value until hash target is reached.
	public void mineBlock(int difficulty) {
		String target = StringUtils.getDificultyString(difficulty); //Create a string with difficulty * "0" 
		while(!hash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
		System.out.println("Block Mined!!! : " + hash);
	}

}
