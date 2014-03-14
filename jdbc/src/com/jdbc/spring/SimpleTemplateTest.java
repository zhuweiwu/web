package com.jdbc.spring;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import com.jdbc.domain.User;
import com.zhuwei.utils.JDBCUtils;

public class SimpleTemplateTest {
	
	
	static SimpleJdbcTemplate simple = new SimpleJdbcTemplate(JDBCUtils.getDataSource());
	
	static User findUser2(String username){
		String sql = "select id,username,birthday,money from user where username=?";
		User user = simple.queryForObject(sql, ParameterizedBeanPropertyRowMapper.newInstance(User.class),
				username); 
		return user;
	}
}
