package com.test;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.hibernate.domain.many2one.User;
import com.hibernate.utils.HibernateUtils;

public class QueryTest {
	public static void main(String[] args){
		User user = new User();
		user.setBirthday(new Date());
		user.setUsername("aaa");
		HibernateUtils.add(user);	
		System.out.println("Insert Success");
		
		query("aaa");
	}
	
	static void query(String name){
		Session s = null;
		try {
			s = HibernateUtils.getSession();
			String hql = "from User as user where user.username=?";
			Query query = s.createQuery(hql);
			query.setString(0, name);
			List<User> list = query.list();
			
			for (User user : list) {
				System.out.println(user.getUsername());
			}
			
		} finally {
			if(s!=null){
				s.close();
			}
		}
	}
}
