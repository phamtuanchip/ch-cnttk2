package com.cloud.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.AuthenticationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.bson.BSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cloud.log.LogShow;
import com.cloud.mappers.UserMapper;
import com.cloud.model.Notification;
import com.cloud.model.Task;
import com.cloud.model.User;
import com.cloud.service.TaskService;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.json.JsonGeneratorImpl;
import com.fasterxml.jackson.core.util.JsonGeneratorDelegate;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class TaskServiceMongoImpl implements TaskService{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	@Autowired
	public MongoOperations mongoTemplate ; 


	@Override
	public User findByUsername(String username) {

		User user = null;
		if(mongoTemplate.collectionExists(User.class)){
			Query searchUserQuery = new Query(Criteria.where("username").is(username));
			user = mongoTemplate.findOne(searchUserQuery, User.class);
		}  
		return user;
	}

	@Override
	public User save(User user) {
		 
		return register(user);

	}

	@Override
	public User update(User user) {
		// query to search user
				Query searchUserQuery = new Query(Criteria.where("username").is(user.getUsername()));
		// find the saved user again.
				User savedUser = mongoTemplate.findOne(searchUserQuery, User.class);
				System.out.println("2. find - savedUser : " + savedUser);
				
				// update password
				DBObject dbObject = (DBObject) JSON.parse(JSON.serialize(user));
			 
				mongoTemplate.updateFirst(searchUserQuery, Update.fromDBObject(dbObject, "id"),
						User.class);
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
		List<User> listUser = mongoTemplate.findAll(User.class);
		return listUser;
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
		mongoTemplate.save(u);
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

	public MongoOperations getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoOperations mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}



}
