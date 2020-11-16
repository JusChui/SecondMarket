package com.jus.service.impl;

import com.jus.dao.IUserDao;
import com.jus.domain.User;
import com.jus.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @Auther: JusChui
 * @Date: 2020/11/9 17:38
 * @Description:
 */
@Service
public class UserServiceImpl implements IUserService {

    private IUserDao userDao;

    @Autowired
    public UserServiceImpl(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findUserByTelOrEmail(User user) {
        return userDao.findUserByTelOrEmail(user);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveUser(Map<String, Object> user) {
        try {
            userDao.saveUser(user);
        } catch (Exception e) {
            throw new RuntimeException("程序异常,注册失败！");
        }
    }

    @Override
    public User findUserByTelEmail(Map<String, Object> user) {
        return userDao.findUserByTelEmail(user);
    }

}
