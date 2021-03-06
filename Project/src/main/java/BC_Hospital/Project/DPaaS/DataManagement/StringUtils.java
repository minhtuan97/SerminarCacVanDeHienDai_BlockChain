package BC_Hospital.Project.DPaaS.DataManagement;

import java.security.MessageDigest;

import com.google.gson.GsonBuilder;

public class StringUtils {
	// Applies Sha256 to a string and returns the result.
	// Áp dụng Sha256 vào một chuỗi và trả về kết quả 64 ký tự.
	public static String applySha256(String input){
		
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        
			//Applies sha256 to our input, 
			byte[] hash = digest.digest(input.getBytes("UTF-8"));
	        
			StringBuffer hexString = new StringBuffer(); // This will contain hash as hexidecimal
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if(hex.length() == 1) hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	// Áp dụng Sha256 vào một mảng byte[] và trả về kết quả 64 ký tự.
	public static String applySha256(byte[] input){
		
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        
			//Applies sha256 to our input, 
			byte[] hash = digest.digest(input);
	        
			StringBuffer hexString = new StringBuffer(); // This will contain hash as hexidecimal
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if(hex.length() == 1) hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	// Short hand helper to turn Object into a json string
	// Biến Object thành chuỗi json
	public static String getJson(Object o) {
		return new GsonBuilder().setPrettyPrinting().create().toJson(o);
	}
	
	// Returns difficulty string target, to compare to hash. eg difficulty of 5 will return "00000"  
	// Trả về mục tiêu chuỗi khó, để so sánh với hàm băm. ví dụ: độ khó 5 sẽ trả về "00000"
	public static String getDificultyString(int difficulty) {
		return new String(new char[difficulty]).replace('\0', '0');
	}
}
