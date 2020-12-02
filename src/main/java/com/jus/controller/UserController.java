package com.jus.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jus.domain.User;
import com.jus.service.IUserService;
import com.jus.uitl.JsonBean;
import com.jus.uitl.MyStringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/saveUser")
    @ResponseBody
    public void saveUser(@RequestParam Map<String, Object> map, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        map.remove("repassword");
        map.remove("checkCode");
        User user = userService.findUserByTelEmail(map);
        JsonBean jsonBean = new JsonBean();
        if (user == null) {
            map.put("id", UUID.randomUUID().toString().replace("-", ""));
            map.put("password", MyStringUtil.encrypt(map.get("password").toString()));
            map.put("birthday", map.get("birthday").toString().replace("-", ""));
            logger.info("入参校验" + map);
            userService.saveUser(map);
            jsonBean.setRtCode("0");
            jsonBean.setRtMsg("成功");
        } else {
            jsonBean.setRtCode("-9999");
            jsonBean.setRtMsg("该手机号码或邮箱已经被注册!");
        }
        String s = JSONObject.toJSON(jsonBean).toString();
        logger.info("注册返回信息 " + s);
        response.getWriter().write(s);
    }

    @RequestMapping(value = "/findUserById")
    @ResponseBody
    public void findUserById(@RequestParam Map<String, Object> map,
                             HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        JsonBean jsonBean = new JsonBean();
        String cookieId = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("userId")) {
                    cookieId = c.getValue();
                }
            }
        }
        logger.info("findUserById cookieId===>" + cookieId);
        logger.info("查询id====》" + map.get("id").toString());
        if (cookieId == null || "".equals(cookieId)) {
            jsonBean.setRtCode("-10000");
            jsonBean.setRtMsg("未登录");
        } else {
            User user = userService.findById(cookieId);
            if (null == user) {
                jsonBean.setRtCode("-9999");
                jsonBean.setRtMsg("登录信息失效，请重新登录");
            } else {
                jsonBean.setRtCode("0");
                jsonBean.setRtMsg("成功");
                jsonBean.setData(user);
            }
        }
        String s = new ObjectMapper().writeValueAsString(jsonBean);
        logger.info("findUserById返回信息" + s);
        response.getWriter().write(s);
    }

    @RequestMapping(value = "/modify")
    @ResponseBody
    public void update(@RequestParam Map<String, Object> map,
                       HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        JsonBean jsonBean = new JsonBean();
        //获取存储在cookies中的userid
        String idInCookie = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("userId")) {
                    idInCookie = c.getValue();
                }
            }
        }
        User user = null;
        map.remove("repassword");
        System.out.println(map.toString());
        if ("".equals(idInCookie)) {
            jsonBean.setRtCode("-9999");
            jsonBean.setRtMsg("未检测到登录信息,请登录后再修改个人信息");
            logger.info("检测到用户未登录");
        } else {
            logger.info("修改个人信息入参校验" + map.toString());
            user = userService.findById(idInCookie);
            logger.info("根据已登录账号的id查询到的User:" + user.toString());
            if (!"".equals(map.get("nickname").toString()) && map.get("nickname").toString() != null) {
                user.setNickname(map.get("nickname").toString());
            }
            if (!"".equals(map.get("username").toString()) && map.get("username").toString() != null) {
                user.setUsername(map.get("username").toString());
            }
            if (!"".equals(map.get("email").toString()) && map.get("email").toString() != null) {
                user.setEmail(map.get("email").toString());
            }
            if (!"".equals(map.get("telNum").toString()) && map.get("telNum").toString() != null) {
                user.setTelNum(map.get("telNum").toString());
            }
            if (!"".equals(map.get("password").toString()) && map.get("password").toString() != null) {
                user.setPassword(MyStringUtil.encrypt(map.get("password").toString()));
            }
            if (!"".equals(map.get("type").toString()) && map.get("type").toString() != null) {
                user.setType(map.get("type").toString());
            }
            if (!"".equals(map.get("birthday").toString()) && map.get("birthday").toString() != null) {
                user.setBirthday(map.get("birthday").toString().replace("-", ""));
            }
            if (!"".equals(map.get("gender").toString()) && map.get("gender").toString() != null) {
                user.setGender(map.get("gender").toString());
            }
            //如果要修改手机号或邮箱,先检查手机号或邮箱是否被注册
            if ((!"".equals(map.get("email").toString()) && null != (map.get("email").toString())) ||
                    (!"".equals(map.get("telNum").toString()) && null != (map.get("telNum").toString()))) {
                if (null == userService.findUserByTelEmail(map)) {
                    logger.info("修改为" + user.toString());
                    userService.updateUserById(user);
                    jsonBean.setRtCode("0");
                    jsonBean.setRtMsg("更新成功");
                } else {
                    jsonBean.setRtCode("-9999");
                    jsonBean.setRtMsg("修改失败:该手机或邮箱已被注册！");
                }
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        jsonBean.setData(user);
        String json = objectMapper.writeValueAsString(jsonBean);
        logger.info("修改用户资料返回信息" + json);
        response.getWriter().write(json);
    }

    @RequestMapping(value = "/findUser")
    @ResponseBody
    public User login(@RequestBody User data, HttpServletResponse response) {
        User user = userService.findUserByTelOrEmail(data);
        if (user == null) {
            logger.info("根据用户email或手机号未查到用户信息");
            return new User();
        } else {
            logger.info("根据用户email或手机号所查到的用户信息" + user.toString());
            //查询到用户信息，登录时将用户id存到cookies中
            user.setPassword(MyStringUtil.decrypt(user.getPassword()));
            Cookie cookie = new Cookie("userId", user.getId());
            cookie.setPath("/");
            response.addCookie(cookie);
            return user;
        }
    }

    @RequestMapping(path = "logout")
    @ResponseBody
    public void logOut(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        Cookie cookie = new Cookie("userId", "");
//        cookie.setMaxAge(0); //立即删除型
        cookie.setPath("/"); //项目所有目录均有效，这句很关键，否则不敢保证删除
        JsonBean jsonBean = new JsonBean();
        jsonBean.setRtCode("0");
        jsonBean.setRtMsg("退出登录成功");
        response.addCookie(cookie);
        String json = new ObjectMapper().writeValueAsString(jsonBean);
        response.getWriter().write(json);
    }

    @GetMapping("/login")
    public String getLogin() {
        return "index/login";
    }

    @GetMapping("/register")
    public String getRegister() {
        return "index/register";
    }

    @GetMapping("/index")
    public String getIndex() {
        return "index";
    }

    @GetMapping("/update")
    public String getUpdate() {
        return "index/updateUser";
    }

    @GetMapping("/toIndex")
    public String toIndex() {
        return "redirect:/user/index";
    }
}
