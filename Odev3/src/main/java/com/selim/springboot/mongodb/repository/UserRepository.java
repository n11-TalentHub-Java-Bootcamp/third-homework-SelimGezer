package com.selim.springboot.mongodb.repository;

import com.selim.springboot.mongodb.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
}
