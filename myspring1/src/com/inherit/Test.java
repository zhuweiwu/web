package com.inherit;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Test {
	public static void main(String[] args){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("com/inherit/beans.xml");
		Graduate graduate = (Graduate) context.getBean("graduate");
		
		System.out.println(graduate.getName()+" "+ graduate.getAge()+" "+ graduate.getDegree());
		
	}
}
