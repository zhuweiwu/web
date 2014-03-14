package com.test;

import java.util.Date;

import com.hibernate.dao.UserDao;
import com.hibernate.dao.impl.UserDaoHiberateImpl;
import com.hibernate.domain.many2one.User;

public class DaoTest {
	public static void main(String[] args){
		UserDao dao = new UserDaoHiberateImpl();
		User user = new User();
		user.setUsername("aaa");
		user.setBirthday(new Date());
		dao.saveUser(user);
		
		user.setUsername("bbb");
		dao.updateUser(user);
		
		User user2 = dao.findUserByName("bbb");
		
		dao.remove(user2);
		
	}
}
