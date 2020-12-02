package com.jus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: JusChui
 * @Date: 2020/11/27 10:22
 * @Description:
 */
@Controller
@RequestMapping(path = "family")
public class FamilyController {

    @GetMapping(path = "addMember")
    public String getAddMember(){
        return "family/addMember";
    }
}
