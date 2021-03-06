package BC_Hospital.Project.DPaaS.DataManagement;

import java.sql.Blob;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BC_Hospital.Project.Model.BlockOffChain;
import BC_Hospital.Project.repository.BlockOffChainRepository;


// Thao tác dữ liệu off chain
// Dữ liệu Off Chain được lưu với mã hash của nó và data của nó lưu dưới dạng byte
@Service
public class Offchain {
	
	 @Autowired 
	 private BlockOffChainRepository blockOffChainRepository;
	 
	public void storeOffChainData(String hash, Blob data) {
		blockOffChainRepository.save(new BlockOffChain(hash, data));	
		System.out.println("storeOffChainData sucessfully");
	}
	
	public void storeOffChainData(BlockOffChain blockOffChain) {
		blockOffChainRepository.save(blockOffChain);	
		System.out.println("storeOffChainData sucessfully");
	}
	
	// lấy dữ liệu (data) off chain từ mã hash của nó
	public Optional<BlockOffChain> obtainOffChainData(String hashfile) {		
		Optional<BlockOffChain> blockOffChain = blockOffChainRepository.findByhashfile(hashfile);
		return blockOffChain;
	}
	
	public List<BlockOffChain> finaAll()
	{
		return blockOffChainRepository.findAll();
	}
}
