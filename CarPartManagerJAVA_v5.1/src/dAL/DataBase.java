package dAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DataBase {
	private static Connection con;
	
	private DataBase() {
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=CarPartManager_DoAn", 
																									"sa", 
																									"tudoanpassword");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//ket noi database
	public static Connection getConnection() {
		if(con == null)
			new DataBase();
		return con;
	}
}
