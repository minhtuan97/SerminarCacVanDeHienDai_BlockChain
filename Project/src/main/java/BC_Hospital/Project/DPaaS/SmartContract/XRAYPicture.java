package BC_Hospital.Project.DPaaS.SmartContract;

import org.springframework.beans.factory.annotation.Autowired;

import BC_Hospital.Project.repository.OffchainRepository;

public class XRAYPicture extends EmbeddedPermission {

	@Autowired
	private OffchainRepository offchainRepository;
	
	String filePath;
	String hashValue;
	Byte file;
	
	XRAYPicture(String filepath, String hashvalue) {
		this.filePath = filepath;
		this.hashValue = hashvalue;	
	}
	
	Byte[] getFile() {
		return offchainRepository.getFileFromHash(hashValue);
	}
	
	void setFile(String filepath) {
		this.filePath = filepath;
	}
}
