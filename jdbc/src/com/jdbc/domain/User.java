package com.jdbc.domain;

import java.util.Date;

public class User {
	private int id;
	private String username;
	private Date birthday;
	private float money;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public float getMoney() {
		return money;
	}
	public void setMoney(float money) {
		this.money = money;
	}
	
	@Override
	public String toString() {
		return "id:" + this.id + " username:" + this.username +
				" birthday:" + this.birthday + " money:" + this.money;
	}
}
