package com.cloud.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cloud.model.User;

public class UserMapper implements RowMapper<User>{
	
	public static final String USER_NAME = "username";
	public static final String PASS_WORD = "password";
	public static final String ROLE = "role";

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User u = new User();
		u.setUsername(rs.getString(USER_NAME));
		u.setPassword(rs.getString(PASS_WORD));
		u.setRoleCode(rs.getString(ROLE));
		return u;
		
	}

}
