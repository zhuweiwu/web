package com.jdbc.dao.impl.codeimprovement2;

import org.junit.Test;

import com.jdbc.domain.User;

public class TestImpl {
	
	@Test
	public void findUserTest(){
		DaoImprovement2 dao = new DaoImprovement2();
		String username = "zhuwei";
		String password = "1234";
		User user = dao.findUser(username, password);
		System.out.println(user.getUsername());
		
	}
	
	@Test
	public void findUsernameTest(){
		DaoImprovement2 dao = new DaoImprovement2();
		int id = 3;
		String username = dao.findUsername(id);
		System.out.println(username);
	}

}
