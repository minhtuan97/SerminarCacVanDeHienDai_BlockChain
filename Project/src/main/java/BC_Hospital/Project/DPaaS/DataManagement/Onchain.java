package BC_Hospital.Project.DPaaS.DataManagement;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import BC_Hospital.Project.Model.BlockOnChain;
import BC_Hospital.Project.repository.BlockOnChainRepository;

@Service
public class Onchain {
	
	 @Autowired 
	 private BlockOnChainRepository blockOnChainRepository;

	// Tạo các bảng dữ liệu ở cơ sở dữ liệu
	public void createTable() {    
	    // Cái này không cần nữa vì @Entity nó tự tạo rồi
	}
	
	// Tạo sự liên lạc
	public void createContract() {
		//Đã có từ jpa
	}
	
	// Lưu Data vào OnChain : Khởi tạo một Block rồi đưa vào chuỗi
	//public void storeOnChainData(String attribute, String ciphertext, BlockOnChain blockOnChain) {
	public void storeOnChainData(BlockOnChain blockOnChain) {
		blockOnChainRepository.save(blockOnChain);
	    System.out.println("storeOnChainData sucessfully"); 
	}
	
	//Lưu data Onchain: tạo một Block nếu chưa có hoặc các block trước đã đầy, ngược lại thêm vào block đã có
	public void storeOnChainDataDependLastBlock(JsonObject data) {
		BlockOnChain block;
		BlockOnChain lastBlock = SelectPreHash();

		if (lastBlock != null && lastBlock.getData().length() < 10000 && lastBlock.getHash().length() == 64) {
			lastBlock.addData(data);
			block = lastBlock;
		} else {
			block = new BlockOnChain();
			block.setData(data);
			block.setTimestamp(new Date().getTime());
			if (lastBlock != null) {
				block.setPrevhash(lastBlock.getHash());
			} else {
				block.setPrevhash("null");
			}
			try {
				block.caculateHash();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		blockOnChainRepository.save(block);
	    System.out.println("storeOnChainDataDependLastBlock sucessfully"); 
	}
	//Dạng data String
	public void storeOnChainDataDependLastBlock(String data) {
		JsonObject jData = new JsonParser().parse(data).getAsJsonObject();
		storeOnChainDataDependLastBlock(jData);
	}
	
	// Lấy Data từ OnChain
	public Optional<BlockOnChain> obtainOnChainData(String hash) {
		return blockOnChainRepository.findByhash(hash);
	}	
	
	//Lấy Block liền trước
	public BlockOnChain SelectPreHash() {
		List<BlockOnChain> listonChains= blockOnChainRepository.findTop1ByOrderByTimestampDesc();
		BlockOnChain tempBlockOnChain;
		for(BlockOnChain a: listonChains) {
			tempBlockOnChain= new BlockOnChain(a.getHash(), a.getPrevhash(), a.getData(),a.getTimestamp());
			return tempBlockOnChain;
		}
		return null;
		
	}
}
