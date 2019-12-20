package BC_Hospital.Project.AuServices;

import org.springframework.beans.factory.annotation.Autowired;

import BC_Hospital.Project.repository.OffchainRepository;

public class FileComparision {
	
	@Autowired
	private OffchainRepository offchainRepository;
	
	boolean Compare(String filePath, String hashValue) {
		
		Byte[] file = offchainRepository.getFileFromHash(hashValue);
		String fileHash = file.toString(); // hàm mã hóa lấy hashcode
		
		return fileHash.equals(hashValue);
	}

}
