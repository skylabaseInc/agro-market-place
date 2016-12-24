package com.skylabase.rest.controllers;

import com.skylabase.exceptions.ItemNotFoundException;
import com.skylabase.model.User;
import com.skylabase.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * RestController that handles request for {@link User} objects
 *
 * @see User
 * @see UserService
 */
@Api(value = "Users Rest Controller")
@RestController
@RequestMapping(value = "/api/v1/users")
public class UsersRestController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Create a new User.", notes = "Returns the created User.")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		user = userService.create(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/")
				.buildAndExpand("")
				.toUri());
		return new ResponseEntity<>(user, headers, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Get all Users or search Users by userName.",
			notes = "Partition results into pages of the given size and return the given page number.")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<User>> getUsers(@RequestParam(value = "name", defaultValue = "", required = false) String name,
											   @RequestParam(value = "pageSize", defaultValue = "10") String pageSize,
											   @RequestParam(value = "pageNumber", defaultValue = "0") String pageNumber) {
		int pgSize = Integer.valueOf(pageSize);
		int pgNum = Integer.valueOf(pageNumber);
		Page<User> users;
		if (!name.isEmpty()) {
			users = userService.findByUsername(name, new PageRequest(pgNum, pgSize));
		} else {
			users = userService.findAll(new PageRequest(pgNum, pgSize));
		}
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	@ApiOperation(value = "Get the user with the given userId")
	public ResponseEntity<User> getUser(@PathVariable(value = "userId") long userId) {
		User user = userService.findById(userId);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
	@ApiOperation(value = "Update the existing user with given userId")
	public ResponseEntity<User> updateUser(@PathVariable(value = "userId") long userId,
										   @RequestBody User user) {
        if (!userService.exists(userId)) {
            throw new ItemNotFoundException("no User with such an id");
        }
		user = userService.update(user);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Delete the User with given userId")
	public ResponseEntity<User> deleteUser(@PathVariable("userId") long userId) {
		userService.delete(userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
