package com.jdbc.dao.impl.codeimprovement2;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author AD
 * 2013-10-27
 */

public interface RowMapper {
	
	public Object mapRow(ResultSet rs) throws SQLException;

}
