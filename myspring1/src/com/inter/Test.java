package com.inter;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("com/inter/beans.xml");
		
		ChangeCharacters characters = (ChangeCharacters) context.getBean("changeCharacters");
		
		System.out.println(characters.change());
		
	}
}
