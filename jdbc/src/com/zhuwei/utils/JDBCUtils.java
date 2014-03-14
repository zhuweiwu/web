package com.zhuwei.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

//import com.jdbc.datasource.MyDataSource;
//import com.jdbc.datasource.MyDataSource2;
/**
 * 
 * @author AD
 * 2013-10-23
 */
public final class JDBCUtils {
	
	//private static MyDataSource myDataSource = null;
	//private static MyDataSource2 myDataSource = null;
	private static DataSource myDataSource = null;
	
	/**
	 * Constructor
	 */
	private JDBCUtils(){		
	}
	
	/**
	 * Get the url of MySQL
	 */
	static{
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			//use MyDataSource2
			//myDataSource = new MyDataSource2();
			
			//DBCP
			Properties prop = new Properties();
			InputStream in = JDBCUtils.class.getClassLoader()
					.getResourceAsStream("dbcpconfig.properties");
			prop.load(in);
			myDataSource = BasicDataSourceFactory.createDataSource(prop);
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}	
	
	/**
	 * 
	 * @return DataSource
	 */
	public static DataSource getDataSource(){
		return myDataSource;
	}
	
	/**
	 * Get a connection
	 * @return connection
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
		return myDataSource.getConnection();
	}
	
	/**
	 * 	free the connection and put it back to pool
	 * @param rs
	 * @param st
	 * @param conn
	 */
	public static void free(ResultSet rs, Statement st, Connection conn) {
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
					try {
						if(conn != null){
							conn.close();
							//myDataSource.free(conn);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		}
	}

}
