package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.zhuwei.utils.JDBCUtils;


public class JDBCDemo1 {
	public static void main(String args[]) throws Exception{
		template2();
	}
	
	
	
	static void template2() throws Exception{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {			
			conn = JDBCUtils.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("select * from user");
			
			while(rs.next()){
				System.out.println(rs.getObject(1)+" "+ rs.getObject(2)
						+" "+ rs.getObject(3)+" "+ rs.getObject(4));
			}
			
		} finally{
			JDBCUtils.free(rs, st, conn);
		}			
	}
	
	static void template() throws Exception{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String url = "jdbc:mysql://localhost:9999/jdbc";
		String user = "root";
		String password = "1234";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			st = conn.createStatement();
			rs = st.executeQuery("select * from user");
			
			while(rs.next()){
				System.out.println(rs.getObject(1)+" "+ rs.getObject(2)
						+" "+ rs.getObject(3)+" "+ rs.getObject(4));
			}
			
		} finally{
			try{
				if(rs != null){
					rs.close();
				} 
			}finally{
				try{
					if(st != null){
						st.close();
					}
				}finally{
						if(conn != null){
							conn.close();
						}
				}
			}
		}		
	}
	
	static void test() throws SQLException{
		//1.Registe Driver
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		
		//2.Connect
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:9999/jdbc", "root", "1234");
		
		//3.Create query sentences
		Statement st = conn.createStatement();
		
		//4.Execute the sentences
		ResultSet rs = st.executeQuery("select * from user  ");
		
		//5.get the results
		while(rs.next()){
			System.out.println(rs.getObject(1)+" "+ rs.getObject(2)
					+" "+ rs.getObject(3)+" "+ rs.getObject(4));
		}
		
		//release resource
		rs.close();
		st.close();
		conn.close();
		 
	}
	

}
