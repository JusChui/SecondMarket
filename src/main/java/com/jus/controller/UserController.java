package com.jus.controller;

import com.alibaba.fastjson.JSONObject;
import com.jus.dao.IUserDao;
import com.jus.domain.User;
import com.jus.service.IUserService;
import com.jus.uitl.JsonBean;
import com.jus.uitl.MyStringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Auther: JusChui
 * @Date: 2020/11/9 17:39
 * @Description:
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;
    private static final Logger logger = LoggerFactory.getLogger(IUserDao.class);

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/saveUser")
    @ResponseBody
    public void saveUser(@RequestParam Map<String, Object> map, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        map.remove("repassword");
        map.remove("checkCode");
        User user = userService.findUserByTelEmail(map);
        JsonBean jsonBean = new JsonBean();
        if (user == null) {
            map.put("id", UUID.randomUUID().toString().replace("-", ""));
            map.put("password",MyStringUtil.encrypt(map.get("password").toString()));
            userService.saveUser(map);
            jsonBean.setRtCode("0");
            jsonBean.setRtMsg("成功");
        } else {
            jsonBean.setRtCode("-9999");
            jsonBean.setRtMsg("该手机或邮箱已经被注册!");
        }
        String s = JSONObject.toJSON(jsonBean).toString();
        response.getWriter().write(s);
    }

    @RequestMapping(value = "/findUser")
    @ResponseBody
    public User findUser(@RequestBody User data) {
        User user = userService.findUserByTelOrEmail(data);
        if (user == null) {
            return new User();
        }
        user.setPassword(MyStringUtil.decrypt(user.getPassword()));
        return user;
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/register")
    public String getRegister() {
        return "register";
    }

    @GetMapping("/index")
    public String getIndex() {
        return "index/index";
    }
}
