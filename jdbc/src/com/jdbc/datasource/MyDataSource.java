package com.jdbc.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;


/**
 * @author AD
 * 2013-10-23
 */
public class MyDataSource {
	private static String url = "jdbc:mysql://localhost:9999/jdbc";
	private static String user = "root";
	private static String password = "1234";
	
	private static int initCount = 5;
	private static int maxCount = 10;
	private int currentCount = 0;
	
	LinkedList<Connection> connectionsPool = new LinkedList<Connection>();
	
	/**
	 * Constroctor
	 * Add 5 connections in the pool
	 */
	public MyDataSource(){
		try {
			for(int i=0;i<initCount;i++){
				this.connectionsPool.addLast(this.createConnection());
				this.currentCount++;
			}
		} catch (SQLException e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	/**
	 * Get the connection First In First Out
	 * @return connection
	 * @throws SQLException 
	 */
	public Connection getConnection() throws SQLException {
		synchronized (connectionsPool) {
			if(this.connectionsPool.size() > 0){
				//remove and returns the first element from the list
				return this.connectionsPool.removeFirst();
			}
			if(this.currentCount < maxCount){
				this.currentCount++;
				return this.createConnection();
			}
			
			throw new SQLException("Connections are all in use");
		}	
	}
	
	/**
	 * Free the connection means put it in the last in the pool again
	 * @param conn
	 */
	public void free(Connection conn) {
		this.connectionsPool.addLast(conn);
	}
	
	/**
	 * Create a connection
	 * @return
	 * @throws SQLException
	 */
	private Connection createConnection() throws SQLException{
		return DriverManager.getConnection(url, user, password);
	}
}
