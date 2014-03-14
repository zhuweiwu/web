package com.zhuwei.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class JDBCUtilsSingle {
	private String url = "jdbc:mysql://localhost:9999/jdbc";
	private String user = "root";
	private String password = "1234";
	
	private static JDBCUtilsSingle instance = null;
	
	private JDBCUtilsSingle(){		
	}
	
	public static JDBCUtilsSingle getInstance(){
		if(instance == null){
			synchronized (JDBCUtilsSingle.class) {
				if(instance == null){
					instance = new JDBCUtilsSingle();
				}
			}
		}
		return instance;
	}
	
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ExceptionInInitializerError(e);
		}
	}	
	
	public Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url, user, password);
	}
	
	public void free(ResultSet rs, Statement st, Connection conn){
		try{
			if(rs != null){
				rs.close();
			} 
		}catch ( SQLException e) {
			e.printStackTrace();
		}finally{
			try{
				if(st != null){
					st.close();
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}finally{
					if(conn != null){
						try {
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
			}
		}
	}

}
