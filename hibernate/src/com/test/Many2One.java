package com.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.domain.many2one.Department;
import com.hibernate.domain.many2one.Employee;
import com.hibernate.utils.HibernateUtils;

/**
 * Ĭ����������
 * 
 * @author AD
 *
 */
public class Many2One {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		add();
		
		
		//System.out.println(employee.getDepartment().getName());//�����ⲻ��
		
		queryDepartment(1);
	}
	
	static void add(){
		Session s = null;
		Transaction t = null;
		try {
			Department department = new Department();
			department.setName("depart");
			
			Employee employee1 = new Employee();
			employee1.setName("aaa");
			employee1.setDepartment(department);
			
			Employee employee2 = new Employee();
			employee2.setName("bbb");
			employee2.setDepartment(department);
			
			Set<Employee> emps = new HashSet<Employee>();
			emps.add(employee1);
			emps.add(employee2);
			department.setEmps(emps);
			
			s = HibernateUtils.getSession();
			t = s.beginTransaction();
			s.save(department);
			s.save(employee1);
			s.save(employee2);
			
			//���Կ�����������˳��仯�Ľ��
			//�Ȳ�employee�ٲ�department������һ���������
			//��Ϊemployee�����û����������ݣ����ڳ־�̬
			//�����ǲ���deparment�����
			/*
			s.save(employee);
			s.save(department);
			*/
			t.commit();
		} finally{
			if(s!=null) s.close();
		}
		
	}
	
	static Employee query(int empId){
		Session s = null;
		try {
			s = HibernateUtils.getSession();
			Employee employee = (Employee) s.get(Employee.class, empId);
			System.out.println(employee.getDepartment().getName());
			return employee;
		} finally{
			if(s!=null) s.close();
		}
	}
	
	static Department queryDepartment(int departId){
		Session s = null;
		try {
			s = HibernateUtils.getSession();
			Department department = (Department) s.get(Department.class, departId);
			return department;
		} finally{
			if(s!=null) s.close();
		}
	}

}
