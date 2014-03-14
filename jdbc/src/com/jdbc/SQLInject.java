package com.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.zhuwei.utils.JDBCUtils;

public class SQLInject {
	public static void main(String args[]) throws SQLException{
		
		//preparedStatement is faster than statement
		long start2 = System.currentTimeMillis();
		read2("aaa");
		long end2 = System.currentTimeMillis();
		System.out.println((end2-start2));
		
		
		//use statement need more time 
		long start1 = System.currentTimeMillis();
		read("aaa");
		long end1 = System.currentTimeMillis();
		System.out.println((end1-start1));
		

	}
	
	//read
	static void read(String username) throws SQLException{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {			
			conn = JDBCUtils.getConnection();
			st = conn.createStatement();
			String sql = "select * from user where username = '" + username
					+"'";
			System.out.println(sql);
			rs = st.executeQuery(sql);
			
			while(rs.next()){
				System.out.println(rs.getObject("id")+" "
								 + rs.getObject("username")+" "
								 + rs.getObject("birthday")+" "
								 + rs.getObject("money"));
			}
			
		} finally{
			JDBCUtils.free(rs, st, conn);
		}
	}
	
	//read using PreparedStatement
	static void read2(String username) throws SQLException{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {			
			conn = JDBCUtils.getConnection();
			String sql = "select * from user where username = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();//no args
			
			while(rs.next()){
				System.out.println(rs.getInt("id")+" "
								 + rs.getString("username")+" "
								 + rs.getDate("birthday")+" "
								 + rs.getFloat("money"));
			}
			
		} finally{
			JDBCUtils.free(rs, ps, conn);
		}
	}


}
