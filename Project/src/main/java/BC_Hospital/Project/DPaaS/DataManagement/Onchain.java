package BC_Hospital.Project.DPaaS.DataManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Onchain {
	
	public static void createTable() throws ClassNotFoundException, SQLException {    
	    try {	
		    // Lấy đối tượng Connection kết nối vào database.
		    Connection conn = MySQLConnUtils.getMySQLConnection();
		
		    // Tạo đối tượng câu lệnh truy vấn
		    Statement stmt = conn.createStatement();
	
		    String sqlCreateTableOnChain = "CREATE TABLE ONCHAIN " +
		    								"(hash VARCHAR(64) not NULL, " +
		    								" previousHash VARCHAR(64), " +
		    								" data TEXT, " +
		    								" timeStamp BIGINT, " +
		    								" nonce INT, " +
		    								" PRIMARY KEY ( hash ))";
	
		    stmt.executeUpdate(sqlCreateTableOnChain);
		    System.out.println("Created table in given database...");
		    stmt.close();
    		conn.close();

	    } catch (SQLException e) {

	    	// Handle errors for JDBC

	    	e.printStackTrace();

	    } catch (Exception e) {

	    	// Handle errors for Class.forName

	    	e.printStackTrace();

	    }
		
	    System.out.println("CreateTableOnChain sucessfully"); 
    	    
	}
	
	// Tạo sự liên lạc, Hợp đồng
	public static void createContract() {}
	
	// Lưu Data vào OnChain
	static void storeOnChainData(String attribute, String ciphertext, Block block) {
	    try
	    {
		    // Lấy đối tượng Connection kết nối vào database.
		    Connection conn = MySQLConnUtils.getMySQLConnection();
				
			// the mysql insert statement
			String query = "INSERT INTO ONCHAIN (hash, previousHash, data, timeStamp, nonce)"
			+ " VALUES (?, ?, ?, ?, ?)";
			
			// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString (1, block.hash);
			preparedStmt.setString (2, block.previousHash);
			preparedStmt.setString (3, block.data);
			preparedStmt.setLong   (4, block.timeStamp);
			preparedStmt.setInt    (5, block.nonce);
			
			// execute the preparedstatement
			preparedStmt.execute();
			  
			conn.close();
			
	    } catch (SQLException e) {
	    	// Handle errors for JDBC
	    	e.printStackTrace();
		} catch (Exception e) {
	    	// Handle errors for Class.forName
	    	e.printStackTrace();
		}
	    System.out.println("storeOnChainData sucessfully"); 
	}
	
	// Lấy Data từ OnChain
	public static Block obtainOnChainData(String hash) {
		Block block = null;
	    try
	    {
	    	// Lấy đối tượng Connection kết nối vào database.
	    	Connection conn = MySQLConnUtils.getMySQLConnection();
			
	    	// our SQL SELECT query. 
	    	// if you only need a few columns, specify them by name instead of using "*"
	    	String query = "SELECT * FROM ONCHAIN WHERE hash =" + hash;

	      // create the java statement
	      Statement st = conn.createStatement();
	      
	      // execute the query, and get a java resultset
	      ResultSet rs = st.executeQuery(query);
	      
	      // iterate through the java resultset
	      while (rs.next())
	      {
	    	  String _hash = rs.getString("hash");
	    	  String previousHash = rs.getString("previousHash");
	    	  String data = rs.getString("data");
	    	  long timeStamp = rs.getLong("timeStamp");
	    	  int nonce = rs.getInt("nonce");
	        
	    	  // print the results
	    	  System.out.format("%s, %s, %s, %s, %s\n", _hash, previousHash, data, timeStamp, nonce);
	      }
	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		
		return block;
	}	
}
