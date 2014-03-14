package com.hibernate.dao;

import com.hibernate.domain.many2one.User;

public interface UserDao {
	public User findUserById(int id);
	
	public User findUserByName(String username);
	
	public void remove(User user);
	
	public void saveUser(User user);
	
	public void updateUser(User user);
}
