package com.jus.dao;

import com.jus.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface IUserDao {

//    List<User> findAll();

    /**
     * 根据用户手机号或邮箱查询用户
     * @param user 传入的带有邮箱或手机号信息的User类型数据
     * @return 返回User
     */
    User findUserByTelOrEmail(User user);

    /**
     * 保存用户信息
     * @param user 保存用户信息的map数据类型
     */
    void saveUser(Map<String,Object> user);

    /**
     * 根据用户手机号或邮箱查询用户
     * @param user 传入的带有邮箱或手机号信息的map类型数据
     * @return 返回User
     */
    User findUserByTelEmail(Map<String,Object> user);
}
