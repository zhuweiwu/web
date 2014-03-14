package com.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.zhuwei.utils.JDBCUtils;


/**
 * CRUD(create read update delete)
 * @author AD
 *
 */
public class CRUD {
	public static void main(String args[]) throws SQLException{
		read();
	}
	
	//create 
	static void create() throws SQLException{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {			
			conn = JDBCUtils.getConnection();
			st = conn.createStatement();
			
			String sql = "insert into user(username,birthday,money) values('bbb','1989-05-18',200)";
			int i = st.executeUpdate(sql);
			
			System.out.println(i);
			
			
		} finally{
			JDBCUtils.free(rs, st, conn);
		}
	}
	
	
	//update
	static void update() throws SQLException{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {			
			conn = JDBCUtils.getConnection();
			st = conn.createStatement();
			
			String sql = "update user set money = money + 10";
			int i = st.executeUpdate(sql);
			
			System.out.println(i);
			
			
		} finally{
			JDBCUtils.free(rs, st, conn);
		} 
	}
	
	//read
	static void read() throws SQLException{
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
			
		} finally{
			JDBCUtils.free(rs, st, conn);
		}
	}
	
	//delete
	static void delete() throws SQLException{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {			
			conn = JDBCUtils.getConnection();
			st = conn.createStatement();
			
			String sql = "delete from user where username = 'bbb'";
			int i = st.executeUpdate(sql);
			
			System.out.println(i);
			
			
		} finally{
			JDBCUtils.free(rs, st, conn);
		} 
	}

}
