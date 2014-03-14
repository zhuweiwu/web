package com.jdbc.dao;

import com.jdbc.domain.User;

public interface UserDao {
	
	public void addUser(User user);
	
	public User getUser(int userID);
	
	public void update(User user);
	
	public void delete(User user);
	
	public User findUser(String loginName, String password);
	
	
	

}
