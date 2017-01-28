package com.skylabase.agromarketplace.service.impl;

import com.skylabase.agromarketplace.model.User;
import com.skylabase.agromarketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@link UserService}
 */
@Service
@Transactional
class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;


	@Override
	public User findById(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public Page<User> listAllByPage(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	public List<User> findAll() {
		final List<User> users = new ArrayList<>();
		for (User user: userRepository.findAll()) {
			users.add(user);
		}
		return users;
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
}

/**
 * Repository used by UserService to access database.
 */
interface UserRepository extends PagingAndSortingRepository<User, Long> {

}

