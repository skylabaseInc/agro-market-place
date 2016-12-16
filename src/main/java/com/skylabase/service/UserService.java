package com.skylabase.service;

import com.skylabase.Authorized;
import com.skylabase.model.PrivilegeConstants;
import com.skylabase.model.User;
import java.util.List;

/**
 * Service that provides CRUD operations for {@link User}
 * 
 * @see User
 */
public interface UserService {

	/**
	 * Find a user by Id.
	 * 
	 * @param id the id of the user to get
	 * @return the user object if found else return null
	 */
	@Authorized(PrivilegeConstants.GET_USERS)
	public User findById(long id);
	
	/**
	 * Find a user by username.
	 * 
	 * @param username the username of the user to find
	 * @return the user if found else null
	 */
	@Authorized(PrivilegeConstants.GET_USERS)
	public User findByUsername(String username);
	
	/**
	 * Get a list of all users from the system.
	 * 
	 * @return list containing all users in the system
	 */
	@Authorized(PrivilegeConstants.GET_USERS)
	public List<User> findAll();
	
	/**
	 * Creates a new user in the system.
	 * 
	 * @param user the user to create
	 * @return the user created
	 */
	@Authorized(PrivilegeConstants.CREATE_USERS)
	public User create(User user);
	
	/**
	 * Updates and existing user.
	 * 
	 * @param user to be updated
	 * @return the updated user
	 */
	@Authorized(PrivilegeConstants.EDIT_USERS)
	public User update(User user);
	
	/**
	 * Deletes a user from the system.
	 * 
	 * @param user the user to be deleted
	 */
	@Authorized(PrivilegeConstants.DELETE_USERS)
	public void delete(User user);

	/**
	 * Checks if given user exists.
	 * 
	 * @param user the user been checked for existence
	 * @return true if user exist else false
	 */
	@Authorized(PrivilegeConstants.VIEW_USERS)
	public boolean exists(User user);

	/**
	 * Delete all users from the system.
	 */
	// TODO should be removed
	public void deleteAll();
}
