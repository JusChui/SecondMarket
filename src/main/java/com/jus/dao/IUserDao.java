package com.jus.dao;

import com.jus.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface IUserDao {

//    List<User> findAll();

    User findUserByTelOrEmail(User user);

    void saveUser(Map<String,Object> user);

    User findUserByTelEmail(Map<String,Object> user);
}
