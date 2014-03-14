package com.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import com.zhuwei.utils.JDBCUtils;

public class DBMD {

	/**
	 * DatabaseMetaData
	 * @param args
	 * @author AD
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		Connection conn = JDBCUtils.getConnection();
		DatabaseMetaData dbmd = conn.getMetaData();
		System.out.println("Database Name:" + dbmd.getDatabaseProductName());
	}

}
