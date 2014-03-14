package com.aop;


public class TestServiceImpl1 implements TestService, TestService2 {
	
	private String name;
	
	
	
	@Override
	public void sayHello() {
		System.out.println("Hi " + this.name); 
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Override
	public void sayBye() {
		// TODO Auto-generated method stub
		System.out.println("Bye " + this.name);
		int i = 9/0;
	}

}
