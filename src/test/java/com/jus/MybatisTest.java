package com.jus;

import com.jus.dao.IUserDao;
import com.jus.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Auther: JusChui
 * @Date: 2020/11/9 18:00
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootMybatisApplication.class)
public class MybatisTest {

//    @Autowired
//    private IUserDao userDao;

    @Test
    public void test(){
        System.out.println("java");
//        List<User> userList = userDao.findAll();
//        System.out.println(userList);
    }
}
