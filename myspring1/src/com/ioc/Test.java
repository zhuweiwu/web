package com.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args){
		ApplicationContext context = new ClassPathXmlApplicationContext("com/ioc/beans.xml");
		
		Student student1 = (Student) context.getBean("student");
		Student student2 = (Student) context.getBean("student");
		
		
		//≤‚ ‘bean÷–µƒscope
		System.out.println(student1 + " " + student2);
	}
}
