package com.jdbc.dao.impl.codeimprovement;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.jdbc.domain.User;

public class DaoImprovement extends AbstractDao {
	/**
	 * 
	 * @param user
	 */
	public void update(User user) {
		String sql = "update user set username=?, birthday=?, money=?, where id=?";
		Object[] args = new Object[] {user.getUsername(), user.getBirthday(), 
						user.getMoney(), user.getId()};
		super.update(sql, args);
	}
	
	/**
	 * 
	 * @param user
	 */
	public void delete(User user){
		String sql = "delete from user where id=?";
		Object[] args = new Object[]{user.getId()};
		super.update(sql, args);
	}
	
	/**
	 * 
	 * @param user
	 */
	public void create(User user){
		String sql = "insert into user(username, birthday, money) values(?, ?, ?)";
		Object[] args = new Object[]{user.getUsername(), user.getBirthday(), user.getMoney()};
		super.update(sql, args);
	}
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public User findUser(String username, String password) {
		String sql = "select id,username,birthday,money from user where username=?";
		Object[] args = new Object[]{username};
		Object user = super.find(sql, args);
		return (User) user;
	}
	
	@Override
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
