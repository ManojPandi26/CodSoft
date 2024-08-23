package StudentManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {

	private static String url="jdbc:mysql://localhost:3306/"; // here Your Database Name
	private static String username="";// username of DB
	private static String password="";// password of DB
	
	public static Connection getconnection()throws SQLException{
		return DriverManager.getConnection(url,username,password);
		
	}
}
