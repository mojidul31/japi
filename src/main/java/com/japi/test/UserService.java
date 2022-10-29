package com.japi.test;

import java.util.List;

import org.apache.log4j.Logger;

public class UserService {
	
	private static final Logger log = Logger.getLogger(UserService.class.getName());
	
	private static UserService INSTANCE = new UserService();
	
	public static UserService getInstance() {
		return INSTANCE;
	}
	
	private UserService() {}
	
	public List<User> findUsers() {
		List<User> users = UserRepository.getInstance().findAllUsers();
		
		return users;
	}
	
	public String saveUser(User user) {
		return UserRepository.getInstance().saveUser(user);
	}

}
