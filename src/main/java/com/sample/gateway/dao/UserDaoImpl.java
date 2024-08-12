package com.sample.gateway.dao;

import com.sample.gateway.entity.User;
import com.sample.gateway.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
