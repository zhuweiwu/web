package com.jdbc.spring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.jdbc.domain.User;
import com.zhuwei.utils.JDBCUtils;

public class SpringJdbcTest {
	
	public static void main(String args[]){
		User user = findUser2("zhuwei");
		System.out.println(user.getUsername());
		
		System.out.println("users: " + findUser3(3));
		
		System.out.println("Total Number of users: " + getUsersNumber());
		System.out.println("Username: " + getUsername(3));
		
		System.out.println("Data Map: " + getData(3));
	}
	
	/**
	 *  Spring Framework use RowMapper
	 * @param username
	 * @return user
	 */
	static User findUser1(String username){
		JdbcTemplate jdbc = new JdbcTemplate();
		jdbc.setDataSource(JDBCUtils.getDataSource());
		String sql = "select id,username,birthday,money from user where username=?";
		Object[] args = new Object[]{username};
		Object user = jdbc.queryForObject(sql, args, new RowMapper<Object>() {
							@Override
							public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
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
	 * use BeanPropertyRowMapper
	 * @param username
	 * @return user
	 */
	static User findUser2(String username){
		JdbcTemplate jdbc = new JdbcTemplate();
		jdbc.setDataSource(JDBCUtils.getDataSource());
		String sql = "select id,username,birthday,money from user where username=?";
		Object[] args = new Object[]{username};
		Object user = jdbc.queryForObject(sql, args, new BeanPropertyRowMapper<>(User.class) ); 
		return (User) user;
	}
	
	/**
	 * Find users more than one
	 * @param id
	 * @return users list
	 */
	static  List<User> findUser3(int id){
		JdbcTemplate jdbc = new JdbcTemplate();
		jdbc.setDataSource(JDBCUtils.getDataSource());
		String sql = "select id,username,birthday,money from user where id<? ";
		Object[] args = new Object[]{id};
		List<User> users = jdbc.query(sql, args, new BeanPropertyRowMapper<>(User.class) ); 
		return users;
	}
	
	/**
	 * Get the number of users in table
	 * @return number of users
	 */
	static int getUsersNumber(){
		JdbcTemplate jdbc = new JdbcTemplate();
		jdbc.setDataSource(JDBCUtils.getDataSource());
		String sql = "select count(*) from user";
		return jdbc.queryForInt(sql);
	}
	
	
	/**
	 * 
	 * @param id
	 * @return username
	 */
	static String getUsername(int id){
		JdbcTemplate jdbc = new JdbcTemplate();
		jdbc.setDataSource(JDBCUtils.getDataSource());
		String sql = "select username from user where id=? ";
		Object[] args = new Object[]{id};
		Object username = jdbc.queryForObject(sql, args, String.class);
		return (String) username;
	}
	
	/**
	 * 
	 * @param id
	 * @return user map<colName, colValue>
	 */
	static Map getData(int id){
		JdbcTemplate jdbc = new JdbcTemplate();
		jdbc.setDataSource(JDBCUtils.getDataSource());
		String sql = "select * from user where id=? ";
		Object[] args = new Object[]{id};
		
		return jdbc.queryForMap(sql, args);
	}

}
