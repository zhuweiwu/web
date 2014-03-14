package com.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.zhuwei.utils.JDBCUtils;

public class TxTest {
	public static void main(String args[]) throws SQLException{
		test();
	}
	
	static void test() throws SQLException{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			st = conn.createStatement();

			String sql = "update user set money=money-10 where id=1";
			st.executeUpdate(sql);
			
			sql = "select money from user where id=2";
			rs = st.executeQuery(sql);
			float money = 0.0f;
			if(rs.next()){
				money = rs.getFloat("money");
			}
			
			if(money>300){
				throw new RuntimeException("Overflow!!");	
			}
			sql = "update user set money=money+10 where id=2";
			st.executeUpdate(sql);

			conn.commit();	
		}catch(SQLException e){
			if(conn != null) conn.rollback();
			throw e;
		}finally{
			JDBCUtils.free(rs, st, conn);
		}
	}

}
