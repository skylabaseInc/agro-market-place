package com.skylabase.service.impl;

import com.skylabase.model.User;
import com.skylabase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link UserService}
 */
@Service
class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User create(User user) {
		return userRepository.save(user);
	}

	@Override
	public Page<User> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}


	@Override
	public User findById(long id) {
		return userRepository.findById(id);
	}

	@Override
	public Page<User> findByUsername(String username, Pageable pageable) {
		return userRepository.findByUsernameLike(username, pageable);
	}

	@Override
	public User update(User user) {
		return userRepository.save(user);
	}

	@Override
	public void delete(long userId) {
		userRepository.delete(userId);
	}

	@Override
	public boolean exists(long userId) {
		return userRepository.exists(userId);
	}
}

/**
 * Repository used by UserService to access database.
 */
interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Page<User> findByUsernameLike(@Param("username") String username, Pageable pageable);

    Page<User> findAll(Pageable pageable);

    Page<User> findByVoidedIsFalse(Pageable pageable);

    Page<User> findByVoidedIsTrue(Pageable pageable);

    User findById(@Param("userId") long userId);

    User findByEmail(@Param("email") String email);
}

