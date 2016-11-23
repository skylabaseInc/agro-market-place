package com.skylabase.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.skylabase.model.User;

public interface UserRepository extends MongoRepository<User, String> {

	
}
