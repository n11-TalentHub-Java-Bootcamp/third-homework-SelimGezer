package com.selim.springboot.mongodb.service;

import com.selim.springboot.mongodb.entity.User;
import com.selim.springboot.mongodb.service.entityservice.UserEntityServiceMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl  implements UserService{

    @Autowired
    private UserEntityServiceMongo userEntityServiceMongo;

    @Override
    public List<User> findAll() {
        return userEntityServiceMongo.findAll();
    }

    @Override
    public User findById(String id) {
        return userEntityServiceMongo.findById(id);
    }

    @Override
    public User save(User user) {
        return userEntityServiceMongo.save(user);
    }

    @Override
    public void delete(String id) {
        userEntityServiceMongo.delete(id);
    }
}
