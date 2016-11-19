package com.skylabase.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skylabase.model.User;
import com.skylabase.service.UserService;

@RestController
public class UserRestController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/users", method=RequestMethod.GET)
	public List<User> getUsers() {
		return userService.getUsers();
	}
	
	@RequestMapping(value = "/users/{userId}", method=RequestMethod.GET)
	public User getUser(@PathVariable("userId") Long userId) {
		return userService.getUser(userId);
	}
}
