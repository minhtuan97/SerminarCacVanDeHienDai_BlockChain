package BC_Hospital.Project.DPaaS.DataManagement;

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
	public BlockOnChain obtainOnChainData(String hash) {
		BlockOnChain blockOnChain = blockOnChainRepository.findByHash(hash);
		return blockOnChain;

	}	
}
