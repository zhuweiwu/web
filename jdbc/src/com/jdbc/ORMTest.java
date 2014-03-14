package com.jdbc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.jdbc.domain.User;
import com.zhuwei.utils.JDBCUtils;
/**
 * read data from database and return a User object
 * @author AD
 */
public class ORMTest {

	/**
	 * 
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		User user = (User) getObject("select id as Id, username as Username, birthday as Birthday, money as Money from user where id=1", User.class);
		System.out.println(user);

	}
	
	
	/**
	 * This method does not depend on User.class
	 * @param sql
	 * @param clazz
	 * @return the same type as input object type
	 * @throws SQLException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws Exception
	 */
	static <T> Object getObject(String sql, Class<T> clazz) throws SQLException, 
		IllegalAccessException, IllegalArgumentException, InvocationTargetException, Exception{
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			String[] colNames = new String[count];
			for (int i = 1; i <= count; i++) {
				// System.out.print(rsmd.getColumnClassName(i) + "\t");
				// System.out.print(rsmd.getColumnName(i) + "\t");
				// System.out.println(rsmd.getColumnLabel(i));
				colNames[i - 1] = rsmd.getColumnLabel(i);
			}
			
			Object obj = null;
			Method[] ms = clazz.getMethods();
			if(rs.next()){
				obj = clazz.newInstance();
				for(int i=0; i<colNames.length; i++){
					String colName = colNames[i];
					String methodName = "set" + colName;
					for(Method m : ms){
						if(methodName.equals(m.getName())){
							m.invoke(obj, rs.getObject(colName));
						}
					}
				}
			}
			return obj;
		} finally {
			JDBCUtils.free(rs, ps, conn);
		}
	}
		

}
