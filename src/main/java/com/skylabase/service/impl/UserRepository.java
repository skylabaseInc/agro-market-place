package com.skylabase.service.impl;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.skylabase.model.User;

interface UserRepository extends MongoRepository<User, String> {

}
