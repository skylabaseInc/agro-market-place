package com.skylabase.service;

import com.skylabase.Authorized;
import com.skylabase.model.PrivilegeConstants;
import com.skylabase.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
	 * @param pageable
	 * @return the user if found else null
	 */
	@Authorized(PrivilegeConstants.GET_USERS)
	public Page<User> findByUsername(String username, Pageable pageable);
	
	/**
	 * Get a list of all users from the system.
	 * 
	 * @return list containing all users in the system
	 * @param pageable
	 */
	@Authorized(PrivilegeConstants.GET_USERS)
	public Page<User> findAll(Pageable pageable);
	
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
	 * @param userId the user to be deleted
	 */
	@Authorized(PrivilegeConstants.DELETE_USERS)
	public void delete(long userId);

	/**
	 * Checks if given user exists.
	 * 
	 * @param userId the user been checked for existence
	 * @return true if user exist else false
	 */
	@Authorized(PrivilegeConstants.VIEW_USERS)
	public boolean exists(long userId);
}
