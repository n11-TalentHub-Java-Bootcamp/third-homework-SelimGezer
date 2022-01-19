package com.selim.springboot.mongodb.service.entityservice;

import com.selim.springboot.mongodb.entity.User;
import com.selim.springboot.mongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserEntityServiceMongo {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        Optional<User> optionalUser = userRepository.findById(id);

        User user = null;
        if (optionalUser.isPresent()){
            user = optionalUser.get();
        }

        return user;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(String id) {
        userRepository.deleteById(id);
    }
}
