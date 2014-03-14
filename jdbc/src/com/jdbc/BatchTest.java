package com.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zhuwei.utils.JDBCUtils;

public class BatchTest {
	
	public static void main(String args[]) throws SQLException{
		createBetch();
	}
	
	//create 
	static void createBetch() throws SQLException{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {			
			conn = JDBCUtils.getConnection();
			
			String sql = "insert into user(username,birthday,money) values(?,?,?)";
			ps = conn.prepareStatement(sql);
			
			for(int i = 0; i < 10; i++){
				ps.setString(1, "batch name" + i);
				ps.setDate(2, new Date(System.currentTimeMillis()));
				ps.setFloat(3, 100f);
				
				ps.addBatch();
			}
			
			ps.executeBatch();
			
			
		} finally{
			JDBCUtils.free(rs, ps, conn);
		}
	}

}
