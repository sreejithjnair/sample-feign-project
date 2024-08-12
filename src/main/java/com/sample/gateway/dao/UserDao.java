package com.sample.gateway.dao;

import com.sample.gateway.entity.User;

import java.util.Optional;

public interface UserDao {

    Optional<User> getUserById(Long userId);

    User saveUser(User user);
}
