package com.skylabase.service;

import java.util.List;

import com.skylabase.model.User;

public interface UserService {

	public User findById(String id);
	
	public List<User> findAll();
	
	public User create(User user);
	
	public User update(User user);
	
	public void delete(User user);
}
