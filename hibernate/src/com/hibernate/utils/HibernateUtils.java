package com.hibernate.utils;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public final class HibernateUtils {
	
	private static SessionFactory sessionFactory;
	private static ThreadLocal threadLocal = new ThreadLocal();
	
	private HibernateUtils(){}
	
	static{
		Configuration config = new Configuration();
		//读取hibernate.cfg.xml文件, 如果名字不同则需要告诉hibernate具体的filename
		config.configure();
		sessionFactory = config.buildSessionFactory();
	}
	
	
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static Session getThreadLocalSession(){
		Session s = (Session) threadLocal.get();
		if(s == null){
			s = getSession();
			threadLocal.set(s);
		}		
		return s;
	}
	
	public static void closeThreadLocalSession(){
		Session s = (Session) threadLocal.get();
		if(s != null){
			s.close();
			threadLocal.set(null);
		}
	}
	
	
	public static Session getSession(){
		return sessionFactory.openSession();
	}
	
	public static void add(Object entity){
		Session s = null;
		Transaction t = null;
		try {
			s = HibernateUtils.getSession();
			t = s.beginTransaction();
			s.save(entity);
			t.commit();
		} finally{
			if(s != null){
				s.close();
			}
		}
	}
	
	public static void update(Object entity){
		Session s = null;
		Transaction t = null;
		try {
			s = HibernateUtils.getSession();
			t = s.beginTransaction();
			s.update(entity);
			t.commit();
		} finally{
			if(s != null){
				s.close();
			}
		}
	}
	
	public static void delete(Object entity){
		Session s = null;
		Transaction t = null;
		try {
			s = HibernateUtils.getSession();
			t = s.beginTransaction();
			s.delete(entity);
			t.commit();
		} finally{
			if(s != null){
				s.close();
			}
		}
	}
	
	public static Object get(Class clazz, Serializable id){
		Session s = null;
		Transaction t = null;
		try {
			s = HibernateUtils.getSession();
			Object obj = s.get(clazz, id);
			return obj;
		} finally{
			if(s != null){
				s.close();
			}
		}
	}
}
