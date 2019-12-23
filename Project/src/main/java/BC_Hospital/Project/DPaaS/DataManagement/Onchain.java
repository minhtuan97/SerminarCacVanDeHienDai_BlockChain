package BC_Hospital.Project.DPaaS.DataManagement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	// Tạo sự liên lạc, Hợp đồng
	public void createContract() {}
	
	// Lưu Data vào OnChain : Khởi tạo một Block rồi đưa vào chuỗi
	public void storeOnChainData(String attribute, String ciphertext, BlockOnChain blockOnChain) {
		blockOnChainRepository.save(blockOnChain);
	    System.out.println("storeOnChainData sucessfully"); 
	}
	
	// Lấy Data từ OnChain
	public Optional<BlockOnChain> obtainOnChainData(String hash) {
		return blockOnChainRepository.findByhash(hash);
	
	}	
	
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
