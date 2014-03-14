package com.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.zhuwei.utils.JDBCUtils;

public class UpdateResultSetTest {
	public static void main(String args[]) throws SQLException{
		read();
	}
	
	
	static void read() throws SQLException{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {			
			conn = JDBCUtils.getConnection();
			st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery("select * from user");
			
			while(rs.next()){
				System.out.println(rs.getObject("id")+" "
								 + rs.getObject("username")+" "
								 + rs.getObject("birthday")+" "
								 + rs.getObject("money"));
				
				String username = rs.getString("username");
				if("zhuwei".equals(username)){
					rs.updateFloat("money", 1f);
					rs.updateRow();
				}
			}
			
		} finally{
			JDBCUtils.free(rs, st, conn);
		}
	}


}
