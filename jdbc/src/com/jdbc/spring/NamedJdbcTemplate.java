package com.jdbc.spring;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.jdbc.domain.User;
import com.zhuwei.utils.JDBCUtils;

public class NamedJdbcTemplate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		User user = new User();
		user.setMoney(10);
		user.setId(2);
		System.out.println(findUser2(user));

	}
	
	static NamedParameterJdbcTemplate named = new NamedParameterJdbcTemplate(
			JDBCUtils.getDataSource());
	
	static User findUser(User user){
		String sql = "select id,username,money,birthday from user "+
				"where money>:m and id<:id";
		Map params = new HashMap();
		//params.put("n", user.getUsername());
		params.put("m", user.getMoney());
		params.put("id", user.getId());
		Object u = named.queryForObject(sql, params, new BeanPropertyRowMapper(User.class));	
		return (User)u;
	}
	
	static User findUser2(User user){
		String sql = "select id,username,money,birthday from user "+
				"where money>:money and id<:id";
		SqlParameterSource ps = new BeanPropertySqlParameterSource(user);
		Object u = named.queryForObject(sql, ps, new BeanPropertyRowMapper(User.class));	
		return (User)u;
	}
	
	static void addUser(User user){
		String sql = "insert into user(username, birthday, money) values(:username, :birthday, :money)";
		
		SqlParameterSource ps = new BeanPropertySqlParameterSource(user);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		named.update(sql, ps,keyHolder);
		int id = keyHolder.getKey().intValue();
		user.setId(id);
		
	}

}
