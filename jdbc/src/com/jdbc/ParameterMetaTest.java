package com.jdbc;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.zhuwei.utils.JDBCUtils;

public class ParameterMetaTest {
	public static void main(String args[]) throws SQLException{
		String sql = "select * from user where username=? and birthday<? and money>?";
		Object[] params = new Object[]{"zhuwei", new Date(System.currentTimeMillis()),100f};
		read(sql, params);
	}

	/**
	 * more flexible
	 * @param sql
	 * @param params
	 * @throws SQLException
	 */
	static void read(String sql, Object[] params) throws SQLException{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {			
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(sql);
			/*ParameterMetaData pmd = ps.getParameterMetaData();
			int count = pmd.getParameterCount();//get the parameter numbers			
			for (int i = 1; i <= count; i++) {
				ps.setObject(i, params[i-1]);
			}*/
			for(int i = 1; i <= params.length; i++){
				ps.setObject(i, params[i-1]);
			}
			rs = ps.executeQuery();
			
			while(rs.next()){
				System.out.println(rs.getObject("id")+" "
								 + rs.getObject("username")+" "
								 + rs.getObject("birthday")+" "
								 + rs.getObject("money"));
			}
			
		} finally{
			JDBCUtils.free(rs, ps, conn);
		}
	}


}
