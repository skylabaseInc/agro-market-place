package com.skylabase.agromarketplace.rest.controllers;

import com.skylabase.agromarketplace.model.User;
import com.skylabase.agromarketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * RestController that handles request for {@link User} objects
 *
 * @see User
 * @see UserService
 */
@RestController
@RequestMapping("/api/v1/users")
@Api(value="User")
public class UserRestController {

	@Autowired
	private UserService userService;

	/**
	 * Get a list of all users in the system.
	 *
	 * @return the list of users
	 *
	 * @see UserService#listAllByPage(Pageable)
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get users", notes = "Returns a list of all users.")
	public ResponseEntity<Page<User>> getUsers(Pageable pageable) {
		final Page<User> users = userService.listAllByPage(pageable);
		if (users == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	/**
	 * Get user with given user id.
	 *
	 * @param user the user to get
	 *            the id of the user to return
	 * @return the user of given id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Get a particular user", notes = "Returns a particular user.")
	public ResponseEntity<User> getUser(@PathVariable("id") User user) {
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	/**
	 * Create a new user in the system.
	 *
	 * @param user
	 *            the user to be created
	 * @return an HttpStatus.CREATED if user was successfully created
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Create a new user", notes = "Returns the created user.")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		if (userService.exists(user)) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		userService.create(user);
		final HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/").buildAndExpand("").toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	/**
	 * Updates an existing user.
	 *
	 * @param currentUser the user been updated
	 *            the id of the user been updated
	 * @param updated
	 *            an updated instance to persist
	 * @return the updated user or an HttpStatus.NOT_FOUND if the user been
	 *         updated does not exist
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "Update an existing user", notes = "Returns the created user.")
	public ResponseEntity<User> updateUser(@PathVariable("id") User currentUser, @RequestBody User updated) {
		if (currentUser == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		currentUser.setUsername(updated.getUsername());
		currentUser.setPhoneNumber(updated.getPhoneNumber());
		currentUser.setEmail(updated.getEmail());
		userService.update(currentUser);
		return new ResponseEntity<>(currentUser, HttpStatus.OK);
	}

	/**
	 * Deletes a user from the system.
	 *
	 * @param user the user to be deleted
	 *            the id of the user been deleted
	 * @return if user was not found an HttpStatus.NOT_FOUND is returned else an
	 *         HttpStatus.NO_CONTENT is returned
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Delete a user")
	public ResponseEntity<User> deleteUser(@PathVariable("id") User user) {
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		userService.delete(user);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
