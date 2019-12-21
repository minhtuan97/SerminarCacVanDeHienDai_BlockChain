package BC_Hospital.Project.DPaaS.SmartContract;



public class XRAYPicture extends EmbeddedPermission {

	
	String filePath;
	String hashValue;
	Byte file;
	
	XRAYPicture(String filepath, String hashvalue) {
		this.filePath = filepath;
		this.hashValue = hashvalue;	
	}
	
	Byte[] getFile() {
		return null;
	}
	
	void setFile(String filepath) {
		this.filePath = filepath;
	}
}
