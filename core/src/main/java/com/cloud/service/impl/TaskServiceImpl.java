package com.cloud.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cloud.log.LogShow;
import com.cloud.mappers.UserMapper;
import com.cloud.model.Notification;
import com.cloud.model.Task;
import com.cloud.model.User;
import com.cloud.service.TaskService;

public class TaskServiceImpl implements TaskService{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	@Override
	public User findByUsername(String username) {

		String query = "select USERNAME, PASSWORD, ROLE from USERS where USERNAME = ?";

		User user = (User)jdbcTemplate.queryForObject(query, new Object[]{username}, new UserMapper());

		return user;
	}

	@Override
	public User save(User user) {
		String query = "insert into USERS (USERNAME, PASSWORD, ROLE) VALUE (?,?,?)";

		Object[] args = new Object[]{user.getUsername(), user.getPassword(),user.getRoleString()};

		int out = jdbcTemplate.update(query, args);

		return user;

	}

	@Override
	public User update(User user) {
		String query = "update USERS set (PASSWORD = ?, ROLE = ?) where USERNAME = ? ";
		Object[] args = new Object[]{user.getPassword(), user.getRoleString(), user.getUsername()};

		int out = jdbcTemplate.update(query, args);

		return user;
	}

	@Override
	public int delete(String username){

		String query="delete from USERS where USERNAME=?";

		try{
			jdbcTemplate.execute(query);
		}catch(Exception e){
			LogShow.getLogDebug(e.getMessage());
			return 0;
		}
		return 1;
	}

	@Override
	public List<User> listUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User login(User u) throws AuthenticationException {
		String SQL = "select * from USERS where USERNAME = ? and PASSWORD = ?";
		User user = (User)jdbcTemplate.queryForObject(SQL, 
				new Object[]{u.getUsername(), u.getPassword()}, new UserMapper());

		return user;
	}


	@Override
	public com.cloud.model.Task getTask(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<com.cloud.model.Task> getTasks() {
		ArrayList<Task> list = new ArrayList<Task>();
		for(int i = 0; i  <= 10 ; i++) {
			Task t = new  Task(i, "test" +i, new Date(), false);
			list.add(t);
		}

		return list;
	}


	@Override
	public com.cloud.model.Task update(
			com.cloud.model.Task t) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<com.cloud.model.Task> listTaskBy(
			com.cloud.model.Task t) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public User register(User u) {
		// TODO Auto-generated method stub
		return u;
	}


	@Override
	public Task searchTask(Task t) {
		// TODO Auto-generated method stub
		return t;
	}


	@Override
	public Task getCountDownTask(Task t) {
		// TODO Auto-generated method stub
		return t;
	}


	@Override
	public Notification getNotification() {
		// TODO Auto-generated method stub
		return new Notification();
	}



}
