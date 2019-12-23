package BC_Hospital.Project.AuServices;

import org.springframework.beans.factory.annotation.Autowired;

import BC_Hospital.Project.DPaaS.DataManagement.StringUtils;
import BC_Hospital.Project.repository.BlockOffChainRepository;

public class FileComparision {
	
	@Autowired
	private static BlockOffChainRepository blockOffChainRepository;
	
	public static boolean Compare(String hashValue, byte[] file) {
		
		//byte[] file = blockOffChainRepository.findByhashfile(hashValue).get().getFile();
		String fileHash = StringUtils.applySha256(file); // hàm mã hóa lấy hashcode
		
		return fileHash.equals(hashValue);
	}

}
