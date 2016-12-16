package com.skylabase.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.skylabase.model.User;
import com.skylabase.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@link UserService}
 */
@Service
class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;


	@Override
	public User findById(long id) {
		return userRepository.findOne(id);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List<User> findAll() {
		final Iterable<User> users = userRepository.findAll();
		final List<User> listOfUsers = new ArrayList<>();
		for (User user: users) {
			listOfUsers.add(user);
		}
		return listOfUsers;
	}

	@Override
	public User create(User user) {
		return userRepository.save(user);
	}

	@Override
	public User update(User user) {
		return userRepository.save(user);
	}

	@Override
	public void delete(User user) {
		userRepository.delete(user);
	}

	@Override
	public boolean exists(User user) {
		return userRepository.exists(user.getId());
	}

	@Override
	public void deleteAll() {
		// TODO will remove this in another issue
	}
}

/**
 * Repository used by UserService to access database.
 */
interface UserRepository extends PagingAndSortingRepository<User, Long> {

	User findByUsername(@Param("username") String username);
}

