package com.skylabase.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.skylabase.model.User;
import com.skylabase.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * RestController that handles request for {@link User} objects
 *
 * @see User
 * @see UserService
 * 
 * @author ivange
 */
@RestController
@RequestMapping("/users")
@Api(value="User")
public class UserRestController {

	@Autowired
	private UserService userService;

	/**
	 * Get a list of all users in the system.
	 * 
	 * @return the list of users
	 * 
	 * @see UserService#findAll()
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get users", notes = "Returns a list of all users.")
	public ResponseEntity<List<User>> getUsers() {
		List<User> users = userService.findAll();

		if (users.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	/**
	 * Get a user with given username or 404 if user is not found.
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get user", notes = "Returns user with give username")
	public ResponseEntity<User> getUserByUsername(@RequestParam(value = "username", required = false) String username) {
		final User result = userService.findByUsername(username);
		if (result == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(result, HttpStatus.OK);
	}

	/**
	 * Get user with given user id.
	 * 
	 * @param id
	 *            the id of the user to return
	 * @return the user of given id
	 * 
	 * @see UserService#findById(String)
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Get a particular user", notes = "Returns a particular user.")
	public ResponseEntity<User> getUser(@PathVariable("id") String id) {
		User user = userService.findById(id);
		if (user == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	/**
	 * Create a new user in the system.
	 * 
	 * @param user
	 *            the user to be created
	 * @param ucBuilder
	 * @return an HttpStatus.CREATED if user was successfully created
	 * 
	 * @see UserService#create(User)
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Create a new user", notes = "Returns the created user.")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		if (userService.exists(user)) {
			return new ResponseEntity<User>(HttpStatus.CONFLICT);
		}

		userService.create(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/").buildAndExpand("").toUri());
		return new ResponseEntity<User>(headers, HttpStatus.CREATED);
	}

	/**
	 * Updates an existing user.
	 * 
	 * @param id
	 *            the id of the user been updated
	 * @param updated
	 *            an updated instance to persist
	 * @return the updated user or an HttpStatus.NOT_FOUND if the user been
	 *         updated does not exist
	 * 
	 * @see UserService#update(User)
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "Update an existing user", notes = "Returns the created user.")
	public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody User updated) {
		User currentUser = userService.findById(id);

		if (currentUser == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		currentUser.setUsername(updated.getUsername());
		currentUser.setPhoneNumber(updated.getPhoneNumber());
		currentUser.setEmail(updated.getEmail());
		currentUser.setLocationId(updated.getLocationId());
		userService.update(currentUser);
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}

	/**
	 * Deletes a user from the system.
	 * 
	 * @param id
	 *            the id of the user been deleted
	 * @return if user was not found an HttpStatus.NOT_FOUND is returned else an
	 *         HttpStatus.NO_CONTENT is returned
	 * 
	 * @see UserService#delete(User)
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Delete a user")
	public ResponseEntity<User> deleteUser(@PathVariable("id") String id) {
		User user = userService.findById(id);
		if (user == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		userService.delete(user);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Deletes all users from the system
	 * 
	 * @return an HttpStatus.NO_CONTENT
	 * 
	 * @see UserService#deleteAll()
	 */
	@RequestMapping(method = RequestMethod.DELETE)
	@ApiOperation(value = "Delete all users")
	public ResponseEntity<User> deleteAllUsers() {
		userService.deleteAll();
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
}
