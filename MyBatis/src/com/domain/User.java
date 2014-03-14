package com.domain;

public class User {
	
	private String id;
	private String username;
	private String address;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return	"["+this.id+"---"+this.username+"----"+this.address +"]";
	}
	
	
}
