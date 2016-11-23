package com.skylabase.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skylabase.repository.UserRepository;
import com.skylabase.model.User;

@RestController
public class UserRestController {

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping("/users")
	public List<User> getUsers() {
		return userRepository.findAll();
	}
}
