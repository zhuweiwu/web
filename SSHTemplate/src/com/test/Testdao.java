package com.test;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Testdao {
	public static void main(String[] args){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-common.xml");
		
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
	}
}
