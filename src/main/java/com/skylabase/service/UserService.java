package com.skylabase.service;

import java.util.List;

import com.skylabase.model.User;

/**
 * Service that provides CRUD operations for {@link User}
 * 
 * @see User
 * 
 * @author ivange
 */
public interface UserService {

	/**
	 * Find a user by Id.
	 * 
	 * @param id the id of the user to get
	 * @return the user object if found else return null
	 */
	public User findById(String id);
	
	/**
	 * Get a list of all users from the system.
	 * 
	 * @return list containing all users in the system
	 */
	public List<User> findAll();
	
	/**
	 * Creates a new user in the system.
	 * 
	 * @param user the user to create
	 * @return the user created
	 */
	public User create(User user);
	
	/**
	 * Updates and existing user.
	 * 
	 * @param user to be updated
	 * @return the updated user
	 */
	public User update(User user);
	
	/**
	 * Deletes a user from the system.
	 * 
	 * @param user the user to be deleted
	 */
	public void delete(User user);

	/**
	 * Checks if given user exists.
	 * 
	 * @param user the user been checked for existence
	 * @return true if user exist else false
	 */
	public boolean exists(User user);

	/**
	 * Delete all users from the system.
	 */
	public void deleteAll();
}
