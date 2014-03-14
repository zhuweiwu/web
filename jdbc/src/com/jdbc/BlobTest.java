package com.jdbc;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.zhuwei.utils.JDBCUtils;

public class BlobTest {
	
	public static void main(String args[]) throws SQLException, IOException{
		//create();
		read();
	}
	
	
	//insert images
	static void create() throws SQLException, IOException{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {	
			//1.connect
			conn = JDBCUtils.getConnection();
			
			//2.create sql
			String sql = "insert into images(big_bit) values(?)";
			ps = conn.prepareStatement(sql);
			
			//need reader  /Test/src/images/1.jpg
			File file = new File("images/1.jpg");
			InputStream in = new BufferedInputStream(new FileInputStream(file));
			
			ps.setBinaryStream(1, in, file.length());
			
			//3.execute sql
			int i = ps.executeUpdate();
			
			in.close();
			System.out.println(i);//see the results
			
			
		} finally{
			JDBCUtils.free(rs, ps, conn);
		}
	}//method create()
	
	//read images
	static void read() throws SQLException, IOException{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {			
			conn = JDBCUtils.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("select big_bit from images");
			
			while(rs.next()){
				//Idea 1
				//Blob blob = rs.getBlob(1);
				//InputStream in = blob.getBinaryStream();
				//Idea 2
				InputStream in = rs.getBinaryStream(1);
				
				File file = new File("2.jpg");
				OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
				
				//create buffer
				byte[] buffer = new byte[1024];
				for (int i = 0; (i= in.read(buffer))>0;) {
					out.write(buffer, 0, i);	
				}
				
				out.close();
				in.close();
				System.out.println("FINISH");
			}
			
		} finally{
			JDBCUtils.free(rs, st, conn);
		}
	}//method read()
	

}
