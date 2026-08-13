package com.digitalLibraryManagementSystem.util;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

	private static final String url = "jdbc:mysql://localhost:3306/library_db?allowPublicKeyRetrieval=true&useSSL=false";
	private static final String username = "root";
	private static final String password = "priya@1234";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}

}

