package com.jdbc.dao.impl;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.jdbc.dao.UserDao;
import com.jdbc.domain.User;
import com.zhuwei.utils.JDBCUtils;

public class UserDaoSpringImpl implements UserDao {

	private SimpleJdbcTemplate simple = new SimpleJdbcTemplate(JDBCUtils.getDataSource());
	@Override
	public void addUser(User user) {
		String sql = "insert into user(username, money, birthday) " +
				"values(:username, :money, :birthday)";
		SqlParameterSource ps = new BeanPropertySqlParameterSource(user);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.simple.getNamedParameterJdbcOperations().update(sql, ps, keyHolder);
		user.setId(keyHolder.getKey().intValue());
	}

	@Override
	public User getUser(int userID) {
		String sql = "select id,username,money,birthday from user where id=?";		
		return this.simple.queryForObject(sql, ParameterizedBeanPropertyRowMapper.newInstance(User.class), userID);
	
	}

	@Override
	public void update(User user) {
		
		//Idea 1
		/*String sql = "update user set username=?, birthday=?, money=?, whrer id=?";
		this.simple.update(sql, user.getUsername(),user.getBirthday(),user.getMoney(),user.getId());*/
		
		//Idea 2
		String sql2 = "update user set username=:username, birthday=:birthday, money=:money, whrer id=:id";
		this.simple.update(sql2, new BeanPropertySqlParameterSource(user));

	}

	@Override
	public void delete(User user) {
		String sql = "delete from user where id=?";
		this.simple.update(sql, user.getId());
	}

	@Override
	public User findUser(String loginName, String password) {
		String sql = "select id,username,money,birthday from user where username=?";		
		return this.simple.queryForObject(sql, ParameterizedBeanPropertyRowMapper.newInstance(User.class), loginName);
	}

}
