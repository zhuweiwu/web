package com.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("com/aop/beans.xml");
		TestServiceImpl1 testImpl1 = (TestServiceImpl1) context.getBean("testServiceImpl1");
		testImpl1.sayHello();
		System.out.println("***************************");
		TestService test = (TestService) context.getBean("proxyFactoryBean");
		test.sayHello();
		System.out.println("");
		((TestService2)test).sayBye();
	}

}
