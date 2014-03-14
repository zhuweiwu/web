package com.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.zhuwei.utils.JDBCUtils;

public class ScrollResultSetTest {
	public static void main(String args[]) throws SQLException{
		scroll();
	}
	
	
	
	static void scroll() throws SQLException{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {			
			conn = JDBCUtils.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("select * from user");
			
			while(rs.next()){
				System.out.println(rs.getObject("id")+" "
								 + rs.getObject("username")+" "
								 + rs.getObject("birthday")+" "
								 + rs.getObject("money"));
			}
			
			if(rs.previous()){
				System.out.println(rs.getObject("id")+" "
						 + rs.getObject("username")+" "
						 + rs.getObject("birthday")+" "
						 + rs.getObject("money"));
			}
			
		} finally{
			JDBCUtils.free(rs, st, conn);
		}
	}

}
