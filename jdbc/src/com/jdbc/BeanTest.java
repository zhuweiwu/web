package com.jdbc;

public class BeanTest {
	
	private String name;
	
	public  BeanTest(String name){
		this.name = name;
	}
	
	
	@Override
	public String toString() {
		
		return this.name;
	}

}
