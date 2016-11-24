package com.skylabase.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import com.skylabase.model.User;
import com.skylabase.service.UserService;

/**
 * Implementation of {@link UserService}
 * 
 * @author ivange
 */
@Service
class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User findById(String id) {
		return userRepository.findOne(id);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User create(User user) {
		return userRepository.save(user);
	}

	@Override
	public User update(User user) {
		return create(user);
	}

	@Override
	public void delete(User user) {
		userRepository.delete(user);
	}

	@Override
	public boolean userExists(User user) {
		return userRepository.exists(user.getId());
	}

	@Override
	public void deleteAll() {
		userRepository.deleteAll();
	}
}

/**
 * Repository used by UserService to access database.
 * 
 * @author ivange
 */
interface UserRepository extends MongoRepository<User, String> {

}

