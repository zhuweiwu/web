package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.service.ByeService;
import com.service.UserService;

public class Test {
	public static void main(String[] args){
		
		//1.�õ�spring��applicationContext�Ķ���
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = (UserService) context.getBean("userService");
		userService.sayHello();
		
		ByeService byeService = (ByeService) context.getBean("byeService");
		byeService.sayBye();
		
		
	}
}
