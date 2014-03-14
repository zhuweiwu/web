package com.mybatis.test;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.domain.User;
import com.domain2.Order;
import com.domain2.Person;

public class MybatisTest {
	SqlSessionFactory sqlSessionFactory;
	
	/**
	 * init 
	 * @throws Exception
	 */
	@Before
	public void init() throws Exception{
		String resource = "sqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);	
	}
	
	/**
	 * select a User
	 * @throws Exception
	 */
	@Test
	public void testUser() throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		
		User user = session.selectOne("com.domain.User.selelctUserUseReslutMap","1");
		System.out.println(user);
	}
	
	/**
	 * select a User
	 * return Map
	 * @throws Exception
	 */
	@Test
	public void testUser2() throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		
		Map map= session.selectOne("com.domain.User.selectUserByIdReturnMap","1");
		System.out.println(map);
	}
	
	/**
	 * select all user
	 * return List<User>
	 * @throws Exception
	 */
	@Test
	public void testAlluser() throws Exception{	
		SqlSession session = sqlSessionFactory.openSession();
		
		List<User> list = session.selectList("com.domain.User.selectAllUser");
		
		System.out.println(list);
	}
	
	/**
	 * insert a user
	 */
	@Test
	public void testInsertUser(){
		SqlSession session = sqlSessionFactory.openSession();
		User user = new User();
		user.setId("3");
		user.setUsername("wangwu");
		user.setAddress("cc");
		int i = session.insert("com.domain.User.insertUser", user);
		session.commit();
		System.out.println(i);
	}
	
	@Test
	public void testDeleteUser(){
		SqlSession session = sqlSessionFactory.openSession();
		int i = session.delete("com.domain.User.deleteUserById", "3");
		session.commit();
		System.out.println(i);
	}
	
	@Test
	public void testUpdateUser(){
		SqlSession session = sqlSessionFactory.openSession();
		User user = new User();
		user.setId("3");
		user.setUsername("wangwu");
		user.setAddress("beijing");
		
		int i = session.update("com.domain.User.updateUserById", user);
		session.commit();
		System.out.println(i);
	}
	
	@Test
	public void testDynamicSQL(){
		SqlSession session = sqlSessionFactory.openSession();
		
		User user = new User();
		user.setUsername("wangwu");
		
		List<User> list = session.selectList("com.domain.User.selectUserByCondition2", user);
		
		System.out.println(list);
	}
	
	
	/**
	 * Test Person and Order
	 */
	@Test
	public void testOne2Many(){
		SqlSession session = sqlSessionFactory.openSession();
		
		List<Person> persons = session.selectList("com.domain2.Person.selectPersonById", "1");
		
		for(Person person : persons){
			System.out.println(person.getOrders().size());
		}
	}
	
	@Test
	public void testMany2One(){
		SqlSession session = sqlSessionFactory.openSession();
		Order order = session.selectOne("com.domain2.Order.selectOrderById", "1");
		System.out.println(order.getPerson().getName());
	}
	
	

}
