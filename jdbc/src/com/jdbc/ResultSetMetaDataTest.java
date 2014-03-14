package com.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhuwei.utils.JDBCUtils;

/**
 * 
 * @author AD
 *
 */
public class ResultSetMetaDataTest {
	public static void main(String args[]) throws SQLException{
		List<Map<String , Object>> datas = read("select id, username as n from user");
		System.out.println(datas);
	}
	
	/**
	 * 
	 * @param sql
	 * @return List<Map<String, Object>>
	 * @throws SQLException
	 */
	static List<Map<String, Object>> read(String sql) throws SQLException {
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
			List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();

			while (rs.next()) {
				Map<String, Object> data = new HashMap<String, Object>();
				for (int i = 0; i < colNames.length; i++) {
					data.put(colNames[i], rs.getObject(colNames[i]));
				}
				datas.add(data);
			}
			return datas;
		} finally {
			JDBCUtils.free(rs, ps, conn);
		}
	}
}
