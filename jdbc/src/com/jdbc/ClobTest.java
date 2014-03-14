package com.jdbc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import com.zhuwei.utils.JDBCUtils;

/**
 * Read big text file from MySQL
 * Write big text files to MySQL
 * 
 * @author AD
 */

public class ClobTest {
	
	public static void main(String args[]) throws SQLException, IOException{
		//create();
		read();
	}

	/**
	 * create method 
	 * @throws SQLException
	 * @throws IOException
	 */
	static void create() throws SQLException, IOException{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {	
			//1.connect
			conn = JDBCUtils.getConnection();
			
			//2.create sql
			String sql = "insert into big_file(big_text) values(?)";
			ps = conn.prepareStatement(sql);
			
			//need reader  /Test/src/com/zhuwei/utils/JDBCUtils.java
			File file = new File("src/com/zhuwei/utils/JDBCUtils.java");
			Reader reader = new BufferedReader(new FileReader(file));
			
			ps.setCharacterStream(1, reader, file.length());
			
			//3.execute sql
			int i = ps.executeUpdate();
			
			reader.close();
			System.out.println(i);//see the results
			
			
		} finally{
			JDBCUtils.free(rs, ps, conn);
		}
	}//method create()
	
	/**
	 * read method
	 * @throws SQLException
	 * @throws IOException
	 */
	static void read() throws SQLException, IOException{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {			
			conn = JDBCUtils.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("select big_text from big_file");
			
			while(rs.next()){
				Clob clob = rs.getClob("big_text");
				Reader reader = clob.getCharacterStream();
				File file = new File("JDBCUtils_bak.java");
				Writer writer = new BufferedWriter(new FileWriter(file));
				
				//create buffer
				char[] buffer = new char[1024];
				for (int i = 0; (i= reader.read(buffer))>0;) {
					writer.write(buffer, 0, i);	
				}
				
				writer.close();
				reader.close();
			}
			
		} finally{
			JDBCUtils.free(rs, st, conn);
		}
	}//method read()
	

}
