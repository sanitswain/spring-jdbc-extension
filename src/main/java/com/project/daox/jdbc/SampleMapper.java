package com.project.daox.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class SampleMapper implements RowMapper<String> {

	public String mapRow(ResultSet arg0, int arg1) throws SQLException {
		return "dao-extension-example";
	}

}
