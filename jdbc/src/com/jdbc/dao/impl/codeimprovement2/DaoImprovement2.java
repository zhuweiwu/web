package com.jdbc.dao.impl.codeimprovement2;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.jdbc.domain.User;

public class DaoImprovement2{
	
	MyDaoTemplate template = new MyDaoTemplate();
	/**
	 * update
	 * @param user
	 */
	public void update(User user) {
		String sql = "update user set username=?, birthday=?, money=?, where id=?";
		Object[] args = new Object[] {user.getUsername(), user.getBirthday(), 
						user.getMoney(), user.getId()};
		template.update(sql, args);
	}
	
	/**
	 * delete
	 * @param user
	 */
	public void delete(User user){
		String sql = "delete from user where id=?";
		Object[] args = new Object[]{user.getId()};
		template.update(sql, args);
	}
	
	/**
	 * create
	 * @param user
	 */
	public void create(User user){
		String sql = "insert into user(username, birthday, money) values(?, ?, ?)";
		Object[] args = new Object[]{user.getUsername(), user.getBirthday(), user.getMoney()};
		template.update(sql, args);
	}
	
	/**
	 * find a user by username and password
	 * @param username
	 * @param password
	 * @return User(user)
	 */
	public User findUser(String username, String password) {
		String sql = "select id,username,birthday,money from user where username=?";
		Object[] args = new Object[]{username};
		Object user = template.find(sql, args, new RowMapper() {			
			@Override
			public Object mapRow(ResultSet rs) throws SQLException {
				User user;
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setMoney(rs.getFloat("money"));
				user.setBirthday(rs.getDate("birthday"));
				return user;
			}
		});
		return (User) user;
	}
	
	/**
	 * find a user by id
	 * @param id
	 * @return (String)username
	 */
	public String findUsername(int id){
		String sql = "select * from user where id=?";
		Object[] args = new Object[]{id};
		Object username = template.find(sql, args, new RowMapper() {		
			@Override
			public Object mapRow(ResultSet rs) throws SQLException {
				return rs.getString("username");
			}
		});
		return (String) username;
	}
	

	protected Object rowMapping(ResultSet rs) throws SQLException {
		User user;
		user = new User();
		user.setId(rs.getInt("id"));
		user.setUsername(rs.getString("username"));
		user.setMoney(rs.getFloat("money"));
		user.setBirthday(rs.getDate("birthday"));
		return user;
	}
	
	
	
}
