package com.test;

import java.util.Date;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.stat.Statistics;

import com.hibernate.domain.many2one.User;
import com.hibernate.utils.HibernateUtils;

public class CacheTest {
	public static void main(String[] args){
		
		
		addUser1();
		System.out.println("------------------");
		getUser(1);
		
		Statistics st = HibernateUtils.getSessionFactory().getStatistics();
		System.out.println("put:" + st.getSecondLevelCachePutCount());
		System.out.println("hit:" + st.getSecondLevelCacheHitCount());
		System.out.println("miss:" + st.getSecondLevelCacheMissCount());
	}
	
	static User getUser(int id){
		Session s = null;
		User user = null;
		try {
			s = HibernateUtils.getSession();
			user = (User) s.get(User.class, id);
			System.out.println(user.getClass());
			//s.evict(user);
			
			user = (User) s.get(User.class, id);//这个语句不再出现select语句，直接从一级缓存拿
			System.out.println(user.getClass());
			//User user = (User) s.load(User.class, id);
			//Hibernate.initialize(user);
			s.clear();
		} finally{
			if(s!=null) s.close();
		}
		
		try {
			s = HibernateUtils.getSession();
			user = (User) s.get(User.class, id);//从二级缓存拿
			System.out.println(user.getClass());
			//User user = (User) s.load(User.class, id);
			//Hibernate.initialize(user);
				
		} finally{
			if(s!=null) s.close();
		}
		
		return null;
	}
	
	static void addUser1(){
		Session s = null;
		Transaction t = null;
		
		try {
			User user = new User();
			user.setUsername("aaa");
			user.setBirthday(new Date());
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
