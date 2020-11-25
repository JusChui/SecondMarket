package com.jus.service;

import com.jus.domain.User;

import java.util.Map;

public interface IUserService {
//    List<User> findAll();

    User findUserByTelOrEmail(User user);

    void saveUser(Map<String,Object> user);

    User findUserByTelEmail(Map<String,Object> user);

    User findById(String id);

    void updateUserById(User user);
}
