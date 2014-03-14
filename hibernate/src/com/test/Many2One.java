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
 * 默认是懒加载
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
		
		
		//System.out.println(employee.getDepartment().getName());//放在这不行
		
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
			
			//可以看看两个保存顺序变化的结果
			//先插employee再插department会多出来一个更新语句
			//因为employee插入后没有外键的内容，出于持久态
			//在我们插入deparment后更新
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
