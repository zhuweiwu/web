package com.jdbc.dao.impl.codeimprovement;

import org.junit.Test;

import com.jdbc.domain.User;

public class TestImpl {
	
	@Test
	public void findUserTest(){
		DaoImprovement dao = new DaoImprovement();
		String username = "zhuwei";
		String password = "1234";
		User user = dao.findUser(username, password);
		System.out.println(user.getUsername());
		
	}

}
