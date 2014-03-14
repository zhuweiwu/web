package com.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import com.zhuwei.utils.JDBCUtils;

public class TestFunction {
	public static void main(String args[]) throws SQLException{
		for(int i=0;i<10; i++){
			Connection conn = JDBCUtils.getConnection();
			System.out.println(conn.getClass().getName());
			JDBCUtils.free(null, null, conn);
		}
		
	}

}
