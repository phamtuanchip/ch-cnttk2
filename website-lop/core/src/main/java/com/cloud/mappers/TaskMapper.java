package com.cloud.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cloud.model.Task;

public class TaskMapper implements RowMapper<Task>{
	
	
	 
		
	public static final String ID = "id";
	public static final String Name = "mame";
	public static final String PRI = "priority" ;
	public static final String Date = "date";
	public static final String Remide = "isReminder";
	public static final String Add = "address";
	public static final String Lat = "latitude";
	public static final String Lgn = "longitude";
	public static final String 	Remided = "isRimided";

		
	@Override
	public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
		Task t = new Task();
		t.setId(rs.getLong(ID));
		t.setName(rs.getString(Name));
		t.setPri(rs.getInt(PRI));
		t.setDate(rs.getDate(Date));
		t.setRemide(rs.getBoolean(Remide));
		t.setAdd(rs.getString(Add));
		t.setLat(rs.getString(Lat));
		t.setLgn(rs.getString(Lgn));
		t.setRemided(rs.getBoolean(Remided));
		return t;
	}

}
