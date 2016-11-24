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
@RequestMapping("/users")
public class UserRestController {

	@Autowired
	private UserService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<User> getUsers() {
		return service.findAll();
	}
	
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public User getUser(@PathVariable("id") String id) {
		return service.findById(id);
	}
}
