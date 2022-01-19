package com.selim.springboot.mongodb.service;
import com.selim.springboot.mongodb.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(String id) ;

    User save(User user);

    void delete(String id) ;

}
