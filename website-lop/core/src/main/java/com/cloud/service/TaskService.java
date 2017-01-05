package com.cloud.service;

import java.util.List;

import javax.naming.AuthenticationException;

import com.cloud.model.Notification;
import com.cloud.model.Task;
import com.cloud.model.User;

public interface TaskService {

	//User API

	public User findByUsername(String username);
	public User save(User user);
	public User update(User user);
	public int delete(String username);
	public List<User> listUsers();	

	public User login(User u) throws AuthenticationException;
	
	public User register(User u);
	// Task API
	public Task getTask(long id) ;
	public List<Task> getTasks() ;
	public Task searchTask(Task t);
	public Task getCountDownTask(Task t);
	
	public Task update(Task t);
	public List<Task> listTaskBy(Task t);
	// Notification API
	public Notification getNotification() ;
	// Subject API
	
	// Blog API
}
