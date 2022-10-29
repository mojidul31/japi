package com.japi.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class MySqlConnection {
	
	static String url = "jdbc:mysql://localhost:3306/";
	static String dbName = "no-entity";
	static String driver = "com.mysql.jdbc.Driver";
	static String userName = "root";
	static String password = "Admin_123";
	
//	public static Connection getConnection() {
//		try {
//			Class.forName("com.mysql.jdbc.Driver");  
//			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mt-hrm","root","Admin_123");  
//
//			return con;
//		}catch(Exception e)
//		{ 
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	public static Connection createConnection() throws IllegalAccessException, InstantiationException {
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url + dbName, userName, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
	
	public static void closeConnection(Connection connection) {
		try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

}
