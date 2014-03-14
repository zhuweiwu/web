package com.jdbc.dao;

import java.io.InputStream;
import java.util.Properties;

/**
 * DaoFactory make no relationship between interface UserDao and its implemention 
 * @author AD
 * 
 */

public class DaoFactory {
	private static UserDao userDao = null;
	private static DaoFactory instance = new DaoFactory();
	//private static UserDao userDao = null;
	private DaoFactory(){
		try {
			//use the properties file to get the class name
			Properties prop = new Properties();
			
			//InputStream in = new FileInputStream(new File("src/daoconfig.properties"));
			InputStream in = DaoFactory.class.getClassLoader().getResourceAsStream("daoconfig.properties");
			prop.load(in);
			String userDaoClass = prop.getProperty("userDaoClass");
			
			//reflection
			Class clazz = Class.forName(userDaoClass);//load the class in JVM
			userDao = (UserDao) clazz.newInstance();//new an instance
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static DaoFactory getInstance(){
		return instance;
	}
	
	public UserDao getUserDao(){
		return userDao;
	}

}
