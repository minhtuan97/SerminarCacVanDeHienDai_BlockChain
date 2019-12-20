package BC_Hospital.Project.DPaaS.DataManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Lớp tiện ích Kết nối cơ sở dữ liệu MySQL
public class MySQLConnUtils {
	// Kết nối csdl MySQL mặc định
	public static Connection getMySQLConnection()
	        throws ClassNotFoundException, SQLException {
	    String hostName = "127.0.0.1";
	    String dbName = "blockchain";
	    String userName = "root";
	    String password = "";
	    return getMySQLConnection(hostName, dbName, userName, password);
	}
	 
	// Kết nói csdl MySQL với các tham số
	public static Connection getMySQLConnection(String hostName, String dbName,
	        String userName, String password) throws SQLException,
	        ClassNotFoundException {
	    // Khai báo class Driver cho DB MySQL
	    // Việc này cần thiết với Java 5
	    // Java6 trở lên tự động tìm kiếm Driver thích hợp.
	    // Nếu bạn dùng Java > 5, thì ko cần dòng này cũng được.
	    //Class.forName("com.mysql.jdbc.Driver");
	 
	    // Cấu trúc URL Connection dành cho MySQL
	    // Ví dụ: jdbc:mysql://localhost:3306/simplehr
	    String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName +"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	 
	    Connection conn = DriverManager.getConnection(connectionURL, userName, password);
	      if (conn != null)
	    	  System.out.println("Kết nối thành công");
	      else
	    	  System.out.println("Kết nối không thành công");
	    return conn;
	}
}
