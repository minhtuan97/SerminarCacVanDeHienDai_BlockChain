package BC_Hospital.Project.AuServices;

import org.springframework.beans.factory.annotation.Autowired;

import BC_Hospital.Project.repository.BlockOffChainRepository;

public class FileComparision {
	
	@Autowired
	private BlockOffChainRepository blockOffChainRepository;
	
	boolean Compare(String hashValue) {
		
		byte[] file = blockOffChainRepository.findByHash(filePath).getFile();
		String fileHash = file.toString(); // hàm mã hóa lấy hashcode
		
		return fileHash.equals(hashValue);
	}

}
