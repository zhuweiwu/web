package com.jdbc.dao.impl.codeimprovement2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.jdbc.dao.DaoException;
import com.zhuwei.utils.JDBCUtils;

public class MyDaoTemplate {

	/**
	 * 
	 * @param sql
	 * @param args
	 * @return int
	 */
	public int update(String sql, Object[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {			
			conn = JDBCUtils.getConnection();			
			ps = conn.prepareStatement(sql);
			for(int i=0; i<args.length; i++){
				ps.setObject(i+1, args[i]);
			}
			return ps.executeUpdate();
		
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}finally{
			JDBCUtils.free(rs, ps, conn);
		} 
	}
	
	/**
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	public Object find(String sql, Object[] args, RowMapper rowMapper){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {			
			conn = JDBCUtils.getConnection();			
			ps = conn.prepareStatement(sql);
			for(int i=0; i<args.length; i++){
				ps.setObject(i+1, args[i]);
			}
			rs = ps.executeQuery();
			Object obj = null;
			while(rs.next()){
				 obj = rowMapper.mapRow(rs);
			}
			return obj;
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}finally{
			JDBCUtils.free(rs, ps, conn);
		}
	}
	
}
