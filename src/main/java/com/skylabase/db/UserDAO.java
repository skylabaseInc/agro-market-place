package com.skylabase.db;

import java.util.List;

import com.skylabase.model.User;

public interface UserDAO {

	public User getUser(Long Id);
	
	public List<User> getUsers();
	
	public User saveUser(User user);
	
	public void deleteUser(User user);
}
