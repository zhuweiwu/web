package com.jdbc.datasource;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

public class MyConnectionHandler implements InvocationHandler {
	private Connection realConnection;
	private Connection warpedConnection;
	private MyDataSource2 dataSource;
	
	private int maxUseCount = 5;
	private int currentUseCount = 0;
	
	/**
	 * Constructor
	 * @param dataSource
	 */
	MyConnectionHandler(MyDataSource2 dataSource) {
		this.dataSource = dataSource;
	}
	
	/**
	 * bind Connection
	 * @param realConnection
	 * @return
	 */
	Connection bind(Connection realConnection){
		this.realConnection = realConnection;
		//Proxy class-->tell the computer to implements Interfaces
		this.warpedConnection = (Connection) Proxy.newProxyInstance(
				this.getClass().getClassLoader(),new Class[]{Connection.class}, this);
		return warpedConnection;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		if("close".equals(method.getName())){
			this.currentUseCount++;
			if(this.currentUseCount < this.maxUseCount){
				this.dataSource.connectionsPool.addLast(this.warpedConnection);
			}else {
				this.realConnection.close();
				this.dataSource.currentCount--;
			}
		}
		return method.invoke(this.realConnection, args);
	}

}
