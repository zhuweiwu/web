package com.jdbc.service;

import com.jdbc.dao.UserDao;
import com.jdbc.dao.impl.UserDaoJdbcImpl;
import com.jdbc.domain.User;

/**
 * 
 * @author AD
 * 
 */
public class UserService {
	private UserDao userDao = new UserDaoJdbcImpl();
	
	public void rigester(User user){
		userDao.addUser(user);
		//sendMail.send(user);
	}

}
