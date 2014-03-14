package com.collection;

import java.util.Map.Entry;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("com/collection/beans.xml");
		Department department = (Department) context.getBean("department");
		System.out.println(department.getName());
		
		for(String empName : department.getEmpName()){
			System.out.println(empName);
		}
		
		Employee employee1 =  (Employee) context.getBean("employee1");
		System.out.println(employee1.getEmpName());
		
		System.out.println("*******从List取数据********");
		
		for (Employee emp : department.getEmployees()) {
			System.out.println("empName = " + emp.getEmpName());
		}
		
		System.out.println("*******从Set取数据********");
		
		for (Employee emp : department.getEmpSet()) {
			System.out.println("empName = " + emp.getEmpName());
		}
		
		System.out.println("********从Map中获取数据***********");
		
		for (Entry<String, Employee> entry : department.getEmpMap().entrySet()) {
			String id = entry.getKey();
			Employee employee = entry.getValue();
			System.out.println(id + " " + employee.getEmpName());
		}
		
	}

}
