package com.struts2.service.impl;

import com.struts2.service.LoginServie;

public class LoginServiceImpl implements LoginServie {

	public boolean isLogin(String username, String password) {
		if("hello".equals(username) && "world".equals(password)){
			return true;
		}else {
			return false;
		}
		
	}

}
