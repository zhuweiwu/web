package com.struts2.action;

import com.opensymphony.xwork2.ActionSupport;
import com.struts2.service.LoginServie;

public class LoginAction extends ActionSupport {
	private String username;
	private String password;
	private LoginServie loginServie;
	
	public void setLoginServie(LoginServie loginServie) {
		this.loginServie = loginServie;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String execute() throws Exception {
		
		if(loginServie.isLogin(username, password)){
			return SUCCESS;
		}
		
		return INPUT;
	}
	
}
