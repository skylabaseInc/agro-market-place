package com.skylabase.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skylabase.db.UserDAO;
import com.skylabase.model.User;
import com.skylabase.service.UserService;

@Service
class UserServiceImpl implements UserService {

	@Autowired
	UserDAO dao;
	
	@Override
	public User getUser(Long userId) {
		return dao.getUser(userId);
	}

	@Override
	public User saveUser(User user) {
		return dao.saveUser(user);
	}

	@Override
	public void deleteUser(User user) {
		dao.deleteUser(user);
	}

	@Override
	public List<User> getUsers() {
		return dao.getUsers();
	}
}
