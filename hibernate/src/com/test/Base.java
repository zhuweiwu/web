package com.test;

import java.util.Date;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.domain.many2one.User;
import com.hibernate.utils.HibernateUtils;

public class Base {
	public static void main(String[] args){
		/*
		Session s = HibernateUtils.getSession();//类似JDBC中的connection
		Transaction t = s.beginTransaction();
		User user = new User();
		user.setBirthday(new Date());
		user.setUsername("aaa");
		s.save(user);
		t.commit();
		s.close();
		System.out.println("end");
		*/
		
		User user = new User();
		user.setBirthday(new Date());
		user.setUsername("aaa");
		addUser(user);		
		System.out.println("Insert Success");
		
		int id = 1;
		User user1 = getUser(id);
		System.out.println("Select Success");
		System.out.println(user1.getUsername());
		
	}
	
	
	
	static User getUser(int id){
		Session s = null;
		try {
			s = HibernateUtils.getSession();
			//User user = (User) s.get(User.class, id);
			
			User user = (User) s.load(User.class, id);
			Hibernate.initialize(user);
			return user;
			
		} finally{
			if(s!=null) s.close();
		}
	}
	
	//版本1
	static void addUser(User user){
		Session s = null;
		Transaction t = null;
		
		try {
			s = HibernateUtils.getSession();
			t = s.beginTransaction();
			s.save(user);
			t.commit();
		}catch(HibernateException e) {
			if(t != null){
				t.rollback();
			}
			throw new RuntimeException(e);
		}finally{
			if(s != null){
				s.close();
			}
		}
	}
	
	//版本2
	static void addUser1(User user){
		Session s = null;
		Transaction t = null;
		
		try {
			s = HibernateUtils.getSession();
			t = s.beginTransaction();
			s.save(user);
			t.commit();
		}finally{
			if(s != null){
				s.close();
			}
		}
	}
}
