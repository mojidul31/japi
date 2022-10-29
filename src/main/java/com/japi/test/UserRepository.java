package com.japi.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.japi.test.UserRepository;

public class UserRepository extends MySqlConnection {

	private static final Logger log = Logger.getLogger(UserRepository.class.getName());
	
	private static UserRepository INSTANCE;
	
	public static UserRepository getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new UserRepository();
		}
		return INSTANCE;
	}
	
	public UserRepository() {
		
	}
	
	public List<User> findAllUsers(){
		List<User> users = new ArrayList<User>();
		try {
			Connection con= createConnection();
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from user_info");  
			while(rs.next()) {
				User user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setEmail(rs.getString(3));
				user.setPhone(rs.getString(4));
				users.add(user);
			}
		   closeConnection(con);
		   return users;
		}catch(Exception e)
		{ 
			//System.out.println(e);
			log.error(e);
		}
		return null;
	}
	
	public String saveUser(User user){
		String msg = "Fail to save data.";
		try {
			//https://alvinalexander.com/java/java-mysql-insert-example-preparedstatement/
			
			Connection con= createConnection();
			// the mysql insert statement
		    String query = "insert into user_info (name, email, phone)"
		        + " values (?, ?, ?)";
		    //create the mysql insert preparedstatement
	        PreparedStatement preparedStmt = con.prepareStatement(query);
	        preparedStmt.setString(1, user.getName());
	        preparedStmt.setString(2, user.getEmail());
	        preparedStmt.setString(3, user.getPhone());
		      
		    // execute the preparedstatement
		    preparedStmt.execute();
		    
		    //connection close
		    closeConnection(con);
		    msg = "Data save successfully.";
		}catch(Exception e)
		{ 
			//e.printStackTrace();
			//System.out.println(e);
			log.error(e);
		}
		return msg;
	}
}
