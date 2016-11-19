package com.skylabase.service;

import java.util.List;

import com.skylabase.model.User;

public interface UserService {

	public User getUser(Long userId);
	
	public User saveUser(User user);
	
	public void deleteUser(User user);
	
	public List<User> getUsers();
}
