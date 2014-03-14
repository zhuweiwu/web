package com.jdbc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jdbc.dao.DaoException;
import com.jdbc.dao.UserDao;
import com.jdbc.domain.User;
import com.zhuwei.utils.JDBCUtils;

public class UserDaoJdbcImpl implements UserDao {

	@Override
	public void addUser(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {			
			conn = JDBCUtils.getConnection();					
			String sql = "insert into user(username,birthday,money) values(?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setDate(2, new java.sql.Date(user.getBirthday().getTime()));
			ps.setFloat(3, user.getMoney());
			ps.executeUpdate();			
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}finally{
			JDBCUtils.free(rs, ps, conn);
		}
		
	}

	@Override
	public User getUser(int userId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		
		try {			
			conn = JDBCUtils.getConnection();
			String sql = "select id,username,birthday,money from user where id = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while(rs.next()){
				user = mappingUser(rs);
			}
			
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}finally{
			JDBCUtils.free(rs, ps, conn);
		}
		return user;
	}

	@Override
	public void update(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {			
			conn = JDBCUtils.getConnection();			
			String sql = "update user set username = ?, birthday = ?, money = ? where id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setDate(2, new java.sql.Date(user.getBirthday().getTime()));
			ps.setFloat(3, user.getMoney());
			ps.setInt(4, user.getId());
			ps.executeUpdate();
		
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}finally{
			JDBCUtils.free(rs, ps, conn);
		} 
	}

	@Override
	public void delete(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {			
			conn = JDBCUtils.getConnection();
			String sql = "delete from user where id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, user.getId());
			ps.executeUpdate();			
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}finally{
			JDBCUtils.free(rs, ps, conn);
		} 
	}

	@Override
	public User findUser(String loginName, String password) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		
		try {			
			conn = JDBCUtils.getConnection();
			String sql = "select id,username,birthday,money from user where username = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginName);
			rs = ps.executeQuery();
			while(rs.next()){
				user = mappingUser(rs);
			}			
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}finally{
			JDBCUtils.free(rs, ps, conn);
		}
		return user;
	}

	private User mappingUser(ResultSet rs) throws SQLException {
		User user;
		user = new User();
		user.setId(rs.getInt("id"));
		user.setUsername(rs.getString("username"));
		user.setMoney(rs.getFloat("money"));
		user.setBirthday(rs.getDate("birthday"));
		return user;
	}

}
